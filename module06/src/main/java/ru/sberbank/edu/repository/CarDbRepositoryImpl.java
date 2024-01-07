package ru.sberbank.edu.repository;


import ru.sberbank.edu.model.Car;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CarDbRepositoryImpl implements CarRepository {
    private final Connection connection;
    public static final String CREATE_CAR_SQL = "INSERT INTO car (id, model) VALUES (?,?)";
    public static final String UPDATE_CAR_SQL = "UPDATE car SET model = ? WHERE id = ?";
    public static final String SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id = ?";
    public static final String DELETE_CAR_BY_ID = "DELETE FROM car WHERE id = ?";
    public static final String DELETE_ALL_CARS = "DELETE FROM car";
    public static final String FIND_ALL_CARS = "SELECT * FROM car";
    public static final String SELECT_CAR_BY_MODEL = "SELECT * FROM car WHERE model = ?";
    public static final String COUNT_ROWS_BY_ID = "SELECT COUNT(*) FROM car where id = ?";

    private final PreparedStatement createPreStmt;
    private final PreparedStatement updatePreStmt;
    private final PreparedStatement findByIdPreStmt;
    private final PreparedStatement deleteByIdPreStmt;
    private final PreparedStatement findByModelPreStmt;

    public CarDbRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.createPreStmt = connection.prepareStatement(CREATE_CAR_SQL);
        this.updatePreStmt = connection.prepareStatement(UPDATE_CAR_SQL);
        this.findByIdPreStmt = connection.prepareStatement(SELECT_CAR_BY_ID);
        this.deleteByIdPreStmt = connection.prepareStatement(DELETE_CAR_BY_ID);
        this.findByModelPreStmt = connection.prepareStatement(SELECT_CAR_BY_MODEL);
    }

    @Override
    public Car createOrUpdate(Car car) throws SQLException {
        Optional<Car> optCar = findById(car.getId());
        if (optCar.isEmpty()) {
            createPreStmt.setString(1, car.getId());
            createPreStmt.setString(2, car.getModel());
            createPreStmt.executeUpdate();
        } else {
            updatePreStmt.setString(1, car.getModel());
            updatePreStmt.setString(2, car.getId());
            updatePreStmt.executeUpdate();
        }
        return car;
    }

    @Override
    public Set<Car> createAll(Collection<Car> cars) {
       cars
                .forEach(car->{
                    try {
                        createPreStmt.setString(1,car.getId());
                        createPreStmt.setString(2, car.getModel());
                        createPreStmt.executeUpdate();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                });
       return new HashSet<>(cars);
    }

    @Override
    public Set<Car> findAll() {
        Set<Car> cars = new HashSet<>();
        try(Statement statement = connection.createStatement();
        ResultSet resultSet= statement.executeQuery(FIND_ALL_CARS)){
        String id = resultSet.getString("id");
        String model = resultSet.getString("model");
        Car car = new Car(id,model);
        cars.add(car);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cars;
    }

    @Override
    public Optional<Car> findById(String id) throws SQLException {
        // validation
        int rowsCount = countRowsById(id);
        if (rowsCount > 1) {
            throw new RuntimeException("Car with id = " + id + " was found many times (" + rowsCount + ").");
        } else if (rowsCount == 0) {
            return Optional.empty();
        }

        findByIdPreStmt.setString(1, id);
        ResultSet resultSet = findByIdPreStmt.executeQuery();

        resultSet.next();
        Car car = new Car(resultSet.getString(1), resultSet.getString(2));
        return Optional.of(car);
    }

    @Override
    public Boolean deleteById(String id) {
       try {
           deleteByIdPreStmt.setString(1, id);
           deleteByIdPreStmt.executeUpdate();
           return true;
       } catch (SQLException e){
           return false;
       }
    }

    @Override
    public Boolean deleteAll() {
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(DELETE_ALL_CARS);
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    private int countRowsById(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(COUNT_ROWS_BY_ID);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int rowCount = 0;
        while (resultSet.next()) {
            rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }

    @Override
    public Set<Car> findByModel(String model) {
        Set<Car> cars = new HashSet<>();
        try {
            findByModelPreStmt.setString(1, model);
            ResultSet resultSet = findByModelPreStmt.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                Car car = new Car(id, model);
                cars.add(car);
            }
            resultSet.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return cars;
    }



}
