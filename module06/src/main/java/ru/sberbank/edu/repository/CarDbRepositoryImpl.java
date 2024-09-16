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
    private static final String SELECT_ALL = "SELECT * FROM car";
    private static final String DELETE_CAR_BY_ID = "DELETE FROM car WHERE id = ?";
    private static final String DELETE_ALL = "DELETE FROM car";
    private static final String SELECT_CAR_BY_MODEL = "SELECT * FROM car WHERE model = ?";

    private final PreparedStatement createPreStmt;
    private final PreparedStatement updatePreStmt;
    private final PreparedStatement findByIdPreStmt;
    private final PreparedStatement findAllPreStmt;
    private final PreparedStatement deleteByIdPreStmt;
    private final PreparedStatement deleteAllPreStmt;
    private final PreparedStatement findByModelPreStmt;

    public CarDbRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.createPreStmt = connection.prepareStatement(CREATE_CAR_SQL);
        this.updatePreStmt = connection.prepareStatement(UPDATE_CAR_SQL);
        this.findByIdPreStmt = connection.prepareStatement(SELECT_CAR_BY_ID);
        this.findAllPreStmt = connection.prepareStatement(SELECT_ALL);
        this.deleteByIdPreStmt = connection.prepareStatement(DELETE_CAR_BY_ID);
        this.deleteAllPreStmt = connection.prepareStatement(DELETE_ALL);
        this.findByModelPreStmt = connection.prepareStatement(SELECT_CAR_BY_MODEL);
    }

    /**
     * Метод обновляет или добавляет авто в таблицу CAR
     * принимает на вход объект типа car
     */
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

    /**
     * Метод обновляет или добавляет авто в таблицу CAR
     * принимает на вход коллекцию типа car
     * возвращает коллекцию типа car добавленных авто
     */
    @Override
    public Set<Car> createAll(Collection<Car> cars) throws SQLException {
        Set<Car> result = new HashSet<>();
        for (Car car : cars) {
            result.add(createOrUpdate(car));
        }
        return result;
    }

    /**
     * Метод возвращает коллекцию типа car всех авто
     */
    @Override
    public Set<Car> findAll() throws SQLException {
        Set<Car> result = new HashSet<>();
        ResultSet resultSet = findAllPreStmt.executeQuery();
        resultSet.next();
        Car car = new Car(resultSet.getString(1), resultSet.getString(2));
        result.add(car);
        return result;
    }

    /**
     * Метод находит по id авто в таблицу CAR
     * принимает на вход строковое значение id
     * возвращает объект типа car
     */
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

    /**
     * Метод удаляет по id авто в таблицу CAR
     * принимает на вход строковое значение id
     * возвращает значение Boolean
     */
    @Override
    public Boolean deleteById(String id) throws SQLException {
        deleteByIdPreStmt.setString(1, id);
        return deleteByIdPreStmt.execute();
    }

    /**
     * Метод удаляет все значения в таблице CAR
     * возвращает значение Boolean
     */
    @Override
    public Boolean deleteAll() throws SQLException {
        return deleteAllPreStmt.execute();
    }

    /**
     * Метод проверяет сколько в таблице строк с одинаковым id
     * принимает на вход строковое значение id
     * возвращает значение int количество найденых строк
     */
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

    /**
     * Метод удаляет по значению model авто в таблицу CAR
     * принимает на вход строковое значение model
     * возвращает значение коллекцию найденых объектов типа car
     */
    @Override
    public Set<Car> findByModel(String model) throws SQLException {
        Set<Car> result = new HashSet<>();
        findByModelPreStmt.setString(1, model);
        ResultSet resultSet = findByModelPreStmt.executeQuery();
        resultSet.next();
        Car car = new Car(resultSet.getString(1), resultSet.getString(2));
        result.add(car);
        return result;
    }
}
