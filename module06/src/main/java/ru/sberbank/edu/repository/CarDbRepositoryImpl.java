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
    private static final String SELECT_CARS_SQL = "SELECT * FROM car";
    private static final String DELETE_CARS_SQL = "DELETE FROM car";
    private static final String DELETE_CAR_BY_ID = "DELETE FROM car WHERE ID = ?";
    private static final String FIND_CARS_BY_MODEL= "SELECT * FROM car WHERE model = ?";

    private final PreparedStatement createPreStmt;
    private final PreparedStatement updatePreStmt;
    private final PreparedStatement findByIdPreStmt;
    private final PreparedStatement selectPreStmt;
    private final PreparedStatement deletePreStmt;
    private final PreparedStatement deleteByIdPreStmt;
    private final PreparedStatement findByModelPreStmt;

    public CarDbRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.createPreStmt = connection.prepareStatement(CREATE_CAR_SQL);
        this.updatePreStmt = connection.prepareStatement(UPDATE_CAR_SQL);
        this.findByIdPreStmt = connection.prepareStatement(SELECT_CAR_BY_ID);
        this.selectPreStmt = connection.prepareStatement(SELECT_CARS_SQL);
        this.deletePreStmt = connection.prepareStatement(DELETE_CARS_SQL);
        this.deleteByIdPreStmt = connection.prepareStatement(DELETE_CAR_BY_ID);
        this.findByModelPreStmt = connection.prepareStatement(FIND_CARS_BY_MODEL);
    }

    /**
     * Create car in databese if it doesn't exist, or update if exist
     * @param car
     * @return car that was added or updated
     * @throws SQLException
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
     * Create collection cars in database
     * @param cars
     * @return Collection cars that was added
     */
    @Override
    public Set<Car> createAll(Collection<Car> cars) {
        Set<Car> carsSet = new HashSet<>(cars);
        if (carsSet.size() != cars.size()) {
            throw new IllegalArgumentException("Collection of cars has duplicates by ID ");
        }
        carsSet.forEach(car -> {
            try {
                createOrUpdate(car);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return carsSet;
    }

    /**
     * Find all cars in database
     * @return Collection all cars from database
     * @throws SQLException
     */
    @Override
    public Set<Car> findAll() {
        try {
            ResultSet resultSet = selectPreStmt.executeQuery();
            Set<Car> cars = new HashSet<>();
            while (resultSet.next()) {
                cars.add(new Car(resultSet.getString(1), resultSet.getString(2)));
            }
            return cars;
        } catch (SQLException e) {
            System.out.println("Method findAll threw an exception");
        }
        return null;
    }

    /**
     * Find car by id from database
     * @param id - car id
     * @return Optional<Car>
     * @throws SQLException
     */
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

    /**
     * Delete car by id from database
     * @param id - car id
     * @return true - if car was deleted or false - if car doesn't exist in database
     * @throws SQLException
     */
    @Override
    public Boolean deleteById(String id) {
        try {
            deleteByIdPreStmt.setString(1, id);
            int affectedRows = deleteByIdPreStmt.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            System.out.println("Method deleteById threw an exception");
        }
        return null;
    }

    /**
     * Delete all cars from database
     * @return true - if request update rows count > 0, else false
     * @throws SQLException
     */
    @Override
    public Boolean deleteAll() {
        try {
            int affectedRows = deletePreStmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Method deleteAll threw an exception");
        }
        return null;
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

    /**
     * Find car by model in database
     * @param model - car model
     * @return collection of cars that have the same car model
     * @throws SQLException
     */
    @Override
    public Set<Car> findByModel(String model) {
        try {
            Set<Car> cars = new HashSet<>();
            findByModelPreStmt.setString(1, model);
            ResultSet resultSet = findByModelPreStmt.executeQuery();
            while (resultSet.next()) {
                cars.add(new Car(resultSet.getString(1), resultSet.getString(2)));
            }
            return cars;
        } catch (SQLException e) {
            System.out.println("Method findByModel threw an exception");
        }
        return null;
    }
}
