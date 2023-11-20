package ru.sberbank.edu.repository;


import ru.sberbank.edu.model.Car;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Set<Car> result = new HashSet<>();
        for (Car car : cars) {
            result.add(createOrUpdate(car));
        }
        return result;
    }

    @Override
    public Set<Car> findAll() throws SQLException {
        Set<Car> result = new HashSet<>();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM car";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        Car car = new Car(resultSet.getString(1), resultSet.getString(2));
        result.add(car);
        return result;
    }

    @Override
    public Optional<Car> findById(String id) throws SQLException {
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
        Statement statement = connection.createStatement();
        String sql = String.format("DELETE FROM car WHERE id = %s", id);
        return statement.execute(sql);
    }

    @Override
    public Boolean deleteAll() throws SQLException {
        Statement statement = connection.createStatement();;
        return statement.execute("DELETE FROM car");
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
        Set<Car> result = new HashSet<>();
        Statement statement = connection.createStatement();
        String sql = String.format("SELECT * FROM car WHERE model = '%s'", model);
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        Car car = new Car(resultSet.getString(1), resultSet.getString(2));
        result.add(car);
        return result;
    }
}
