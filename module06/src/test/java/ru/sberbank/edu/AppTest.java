package ru.sberbank.edu;

import org.h2.tools.Server;
import org.junit.jupiter.api.*;
import ru.sberbank.edu.dbconnection.H2DbEmbedded;
import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarDbRepositoryImpl;
import ru.sberbank.edu.repository.CarRepository;

import java.sql.*;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private Server server;
    private Connection connection;
    private CarRepository carRepository;

    @BeforeEach
    public void testInitDB() throws SQLException {
        server = Server.createTcpServer().start();
        H2DbEmbedded.initDb();
        connection = H2DbEmbedded.getConnection();
        carRepository = new CarDbRepositoryImpl(connection);
    }

    @Test
    @DisplayName("Тест метода createOrUpdate")
    public void testCreateOrUpdate() throws SQLException {
        carRepository.createOrUpdate(new Car("1", "Lada"));
        carRepository.createOrUpdate(new Car("2", "BMW"));
        carRepository.createOrUpdate(new Car("1", "SuperLada"));
        Set<Car> cars = carRepository.findAll();
        Assertions.assertTrue(cars.size() == 2 &&
                cars.contains(new Car("1", "SuperLada")) &&
                cars.contains(new Car("2", "BMW")));
    }

    @Test
    @DisplayName("Тест метода createAll")
    public void testCreateAll() throws SQLException {
        List<Car> cars = List.of(new Car("1", "Lada"),
                new Car("2", "BNW"),
                new Car("3", "Alfa Romeo"));
        carRepository.createAll(cars);
        Set<Car> carSet = carRepository.findAll();
        Assertions.assertTrue(carSet.size() == 3  &&
                carSet.contains(new Car("1", "Lada")) &&
                carSet.contains(new Car("2", "BNW")) &&
                carSet.contains(new Car("3", "Alfa Romeo")));
    }

    @Test
    @DisplayName("Тест метода findAll")
    public void testFindAll() throws SQLException {
        List<Car> cars = List.of(new Car("1", "Lada"),
                new Car("2", "BNW"),
                new Car("3", "Alfa Romeo"));
        carRepository.createAll(cars);
        Set<Car> carSet = new HashSet<>();
        carSet = carRepository.findAll();
        Assertions.assertTrue(carSet.size() == 3 &&
                carSet.contains(new Car("1", "Lada")) &&
                carSet.contains(new Car("2", "BNW")) &&
                carSet.contains(new Car("3", "Alfa Romeo")));
    }

    @Test
    @DisplayName("Тест метода findById")
    public void testFindById() throws SQLException {
        List<Car> cars = List.of(new Car("1", "Lada"),
                new Car("2", "BNW"),
                new Car("3", "Alfa Romeo"));
        carRepository.createAll(cars);
        Assertions.assertTrue(carRepository.findById("1").isPresent() &&
                carRepository.findById("2").isPresent() &&
                carRepository.findById("3").isPresent() &&
                carRepository.findById("4").isEmpty());
    }

    @Test
    @DisplayName("Тест метода deleteById")
    public void testDeleteById() throws SQLException {
        List<Car> cars = List.of(new Car("1", "Lada"),
                new Car("2", "BNW"),
                new Car("3", "Alfa Romeo"));
        carRepository.createAll(cars);
        carRepository.deleteById("2");
        Set<Car> carSet = carRepository.findAll();
        Assertions.assertTrue(carSet.size() == 2 &&
                carSet.contains(new Car("1", "Lada")) &&
                carSet.contains(new Car("3", "Alfa Romeo")) &&
                carRepository.findById("2").isEmpty());
    }

    @Test
    @DisplayName("Тест метода deleteAll")
    public void testDeleteAll() throws SQLException {
        List<Car> cars = List.of(new Car("1", "Lada"),
                new Car("2", "BNW"),
                new Car("3", "Alfa Romeo"));
        carRepository.createAll(cars);
        carRepository.deleteAll();
        Assertions.assertTrue(carRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Тест метода findByModel")
    public void testFindByModel() throws SQLException {
        List<Car> cars = List.of(new Car("1", "Lada"),
                new Car("2", "BMW"),
                new Car("3", "Alfa Romeo"));
        carRepository.createAll(cars);
        Assertions.assertTrue(carRepository.findByModel("Lada").size() == 1 &&
                carRepository.findByModel("Lada").contains(new Car("1", "Lada")) &&
                carRepository.findByModel("BMW").size() == 1 &&
                carRepository.findByModel("BMW").contains(new Car("2", "BMW")) &&
                carRepository.findByModel("Alfa Romeo").size() == 1 &&
                carRepository.findByModel("Alfa Romeo").contains(new Car("3", "Alfa Romeo")));
    }
}
