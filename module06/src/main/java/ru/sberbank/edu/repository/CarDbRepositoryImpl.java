package ru.sberbank.edu.repository;

import ru.sberbank.edu.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CarDbRepositoryImpl implements CarRepository {
    private final Connection connection;
    private static final String CREATE_CAR_SQL = "INSERT INTO car (id, model) VALUES (?,?)";
    private static final String UPDATE_CAR_SQL = "UPDATE car SET model = ? WHERE id = ?";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id = ?";

    private final PreparedStatement createPreStmt;
    private final PreparedStatement updatePreStmt;
    private final PreparedStatement findByIdPreStmt;

    public CarDbRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.createPreStmt = connection.prepareStatement(CREATE_CAR_SQL);
        this.updatePreStmt = connection.prepareStatement(UPDATE_CAR_SQL);
        this.findByIdPreStmt = connection.prepareStatement(SELECT_CAR_BY_ID);
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
    public Set<Car> createAll(Collection<Car> cars) throws SQLException {
        Set<Car> setOfCars = new HashSet<>();
        String CREATE_ALL_CARS = "INSERT INTO car (id, model) VALUES (?,?)";
//        String CREATE_ALL_CARS = "UPDATE car SET model = ? WHERE id = ?";
        PreparedStatement createAllCars = connection.prepareStatement(CREATE_ALL_CARS);
        for (Car car:cars) {
            createAllCars.setString(1, car.getId());
            createAllCars.setString(2, car.getModel());
            createAllCars.executeUpdate();
            setOfCars.add(car);
        }
        return setOfCars;
    }

    @Override
    public Set<Car> findAll() throws SQLException {
        Set<Car> cars = new HashSet<>();
        String SELECT_ALL_CARS = "SELECT * FROM car";
        PreparedStatement findAllCars = connection.prepareStatement(SELECT_ALL_CARS);
        ResultSet resultSet = findAllCars.executeQuery();
        while (resultSet.next()) {
            cars.add(new Car(resultSet.getString(1), resultSet.getString(2)));
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
    public Boolean deleteById(String id) throws SQLException {
        if (countRowsById(id) == 0) {
            return false;
        }
        else {
            String DELETE_BY_ID = "DELETE FROM car WHERE id = ?";
            PreparedStatement deleteById = connection.prepareStatement(DELETE_BY_ID);
            deleteById.setString(1, id);
            deleteById.executeUpdate();
            return true;
        }
    }

    @Override
    public Boolean deleteAll() throws SQLException {
        String DELETE_ALL = "DELETE FROM car";
        String SELECT_ANYTHING = "SELECT * FROM car";
        PreparedStatement deleteAll = connection.prepareStatement(DELETE_ALL);
        deleteAll.executeUpdate();
        PreparedStatement selectAny = connection.prepareStatement(SELECT_ANYTHING);
        ResultSet resultSet = selectAny.executeQuery();
        return !resultSet.next();
    }

    private int countRowsById(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM car where id = ?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int rowCount = 0;
        while (resultSet.next()) {
            rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }

    @Override
    public Set<Car> findByModel(String model) throws SQLException {
        Set<Car> cars = new HashSet<>();
        String SELECT_CAR_BY_MODEL = "SELECT * FROM car WHERE model = ?";
        PreparedStatement findByModelPreStmt = connection.prepareStatement(SELECT_CAR_BY_MODEL);
        findByModelPreStmt.setString(1, model);
        ResultSet resultSet = findByModelPreStmt.executeQuery();
        while (resultSet.next()) {
            cars.add(new Car(resultSet.getString(1), resultSet.getString(2)));
        }
        return cars;
    }
}