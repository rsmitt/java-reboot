package ru.sberbank.edu.repository;


import ru.sberbank.edu.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Репозиторий
 */
public class CarDbRepositoryImpl implements CarRepository {
    private static final String CREATE_CAR_SQL = "INSERT INTO car (id, model) VALUES (?,?)";
    private static final String UPDATE_CAR_SQL = "UPDATE car SET model = ? WHERE id = ?";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id = ?";
    private static final String SELECT_CAR_BY_MODEL = "SELECT * FROM car WHERE model = ?";
    private static final String SELECT_ALL = "SELECT * FROM car";
    private static final String DELETE_CAR_BY_ID = "DELETE FROM car WHERE id = ?";
    private static final String DELETE_ALL = "DELETE FROM car";
    private final Connection connection;
    private final PreparedStatement createPreStmt;
    private final PreparedStatement updatePreStmt;
    private final PreparedStatement findByIdPreStmt;
    private final PreparedStatement findByModelPreStmt;
    private final PreparedStatement findAllPreStmt;
    private final PreparedStatement deleteByIdPreStmt;
    private final PreparedStatement deleteAllPreStmt;

    /**
     * Конструктор
     *
     * @param connection соединение с БД
     * @throws SQLException особая ситуация
     */
    public CarDbRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.createPreStmt = connection.prepareStatement(CREATE_CAR_SQL);
        this.updatePreStmt = connection.prepareStatement(UPDATE_CAR_SQL);
        this.findByIdPreStmt = connection.prepareStatement(SELECT_CAR_BY_ID);
        this.findByModelPreStmt = connection.prepareStatement(SELECT_CAR_BY_MODEL);
        this.findAllPreStmt = connection.prepareStatement(SELECT_ALL);
        this.deleteByIdPreStmt = connection.prepareStatement(DELETE_CAR_BY_ID);
        this.deleteAllPreStmt = connection.prepareStatement(DELETE_ALL);
    }

    /**
     * Создание или модификация таблицы моделей машин Car
     *
     * @param car данные моделей машин
     * @return объект машина
     * @throws SQLException особая ситуация
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
     * Создание записей в таблице моделей машин по коллекции данных
     *
     * @param cars коллекция моделей машин
     * @return Набор моделей машин
     */
    @Override
    public Set<Car> createAll(Collection<Car> cars) {
        Set<Car> result = new LinkedHashSet<>();
        cars.forEach(car -> {
            try {
                result.add(createOrUpdate(car));
            } catch (SQLException e) {
                throw new RuntimeException(String.format("Error with create new car( %s, %s). SQL State: %s ", car.getId(), car.getModel(), e.getSQLState()));
            }
        });
        return result;
    }

    /**
     * Выбор всех моделей из таблицы
     *
     * @return Набор моделей машин
     */
    @Override
    public Set<Car> findAll() {
        Set<Car> result = new LinkedHashSet<>();
        try (ResultSet resultSet = findAllPreStmt.executeQuery()) {
            while (resultSet.next()) {
                result.add(new Car(resultSet.getString(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Error with findAll. SQL State: %s ", e.getSQLState()));
        }
        return result;
    }

    /**
     * Поиск модели по id
     *
     * @param id идентификатор модели
     * @return данные по модели
     * @throws SQLException особая ситуация
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
     * Удаление по id
     *
     * @param id идентификатор модели
     * @return  true - если успешно
     */
    @Override
    public Boolean deleteById(String id) {
        try {
            deleteByIdPreStmt.setString(1, id);
             return deleteByIdPreStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Error with deleteById %s. SQL State: %s ", id, e.getSQLState()));
        }
    }

    /**
     * Удаление всех записей
     * @return true - если успешно
     */
    @Override
    public Boolean deleteAll() {
        try {
            return deleteAllPreStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Error with deleteAll. SQL State: %s ", e.getSQLState()));
        }
    }

    /**
     * Подсчет количества записей по id
     * @param id идентификатор
     * @return количество записей
     * @throws SQLException
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
     * Поиск по модели
     * @param model модель
     * @return набор найденных данных
     */
    @Override
    public Set<Car> findByModel(String model) {
        Set<Car> carSet = new LinkedHashSet<>();
        try {
            findByModelPreStmt.setString(1, model);
            try (ResultSet resultSet = findByModelPreStmt.executeQuery()) {
                while (resultSet.next()) {
                    carSet.add(new Car(resultSet.getString(1), resultSet.getString(2)));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Error with findByModel %s. SQL State: %s ", model, e.getSQLState()));
        }
        return carSet;
    }
}
