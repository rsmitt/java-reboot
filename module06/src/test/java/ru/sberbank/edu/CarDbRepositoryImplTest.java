package ru.sberbank.edu;

import org.junit.jupiter.api.*;
import ru.sberbank.edu.dbconnection.H2DbEmbedded;
import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarDbRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class CarDbRepositoryImplTest {

    private static CarDbRepositoryImpl repository;

    @BeforeAll
    public static void init() throws SQLException {
        H2DbEmbedded.initDb();
        Connection connection = H2DbEmbedded.getConnection();
        repository = new CarDbRepositoryImpl(connection);
    }

    @AfterEach
    public void cleanUp() throws SQLException {
        H2DbEmbedded.cleanDb();
    }

    @Test
    public void createOrUpdateTest() throws SQLException {
        Car car = new Car("1", "Tesla");
        Assertions.assertEquals(car, repository.createOrUpdate(car));
        Set<Car> cars = repository.findAll();
        Assertions.assertEquals(1, cars.size());
        Assertions.assertEquals(car, cars.iterator().next());

        car = new Car("1", "Toyota");
        Assertions.assertEquals(car, repository.createOrUpdate(car));
        cars = repository.findAll();
        Assertions.assertEquals(1, cars.size());
        Assertions.assertEquals(car, cars.iterator().next());
    }

    @Test
    public void createAllTest() throws SQLException {
        Collection<Car> carsList = new ArrayList<>();
        carsList.add(new Car("1", "Tesla"));
        carsList.add(new Car("2", "Toyota"));
        carsList.add(new Car("3", "Subaru"));

        Set<Car> carsSet = repository.createAll(carsList);
        Assertions.assertEquals(3, carsSet.size());
        Assertions.assertEquals(new HashSet<>(carsList), carsSet);

        Set<Car> carsSetFromDb = repository.findAll();
        Assertions.assertEquals(3, carsSetFromDb.size());
        Assertions.assertEquals(carsSet, carsSetFromDb);
    }

    @Test
    public void findAllTest() throws SQLException {
        Collection<Car> carsList = new ArrayList<>();
        carsList.add(new Car("1", "Tesla"));
        carsList.add(new Car("2", "Toyota"));
        carsList.add(new Car("3", "Subaru"));

        repository.createAll(carsList);
        Set<Car> carsSetFromDb = repository.findAll();
        Assertions.assertEquals(3, carsSetFromDb.size());
        Assertions.assertEquals(new HashSet<>(carsList), carsSetFromDb);

    }

    @Test
    public void findByIdTest() throws SQLException {
        Collection<Car> carsList = new ArrayList<>();
        carsList.add(new Car("1", "Tesla"));
        carsList.add(new Car("2", "Toyota"));
        carsList.add(new Car("3", "Subaru"));
        repository.createAll(carsList);

        Optional<Car> car = repository.findById("2");
        Assertions.assertEquals("2", car.get().getId());
        Assertions.assertEquals("Toyota", car.get().getModel());
    }

    @Test
    public void deleteByIdTest() throws SQLException {
        Collection<Car> carsList = new ArrayList<>();
        carsList.add(new Car("1", "Tesla"));
        carsList.add(new Car("2", "Toyota"));
        carsList.add(new Car("3", "Subaru"));
        repository.createAll(carsList);

        Assertions.assertTrue(repository.deleteById("3"));
        Set<Car> carsSetFromDb = repository.findAll();
        Assertions.assertEquals(2, carsSetFromDb.size());
        Set<Car> carsExpected = new HashSet<>();
        carsExpected.add(new Car("1", "Tesla"));
        carsExpected.add(new Car("2", "Toyota"));
        Assertions.assertEquals(carsExpected, carsSetFromDb);
    }

    @Test
    public void deleteAllTest() throws SQLException {
        Collection<Car> carsList = new ArrayList<>();
        carsList.add(new Car("1", "Tesla"));
        carsList.add(new Car("2", "Toyota"));
        carsList.add(new Car("3", "Subaru"));
        repository.createAll(carsList);

        Assertions.assertTrue(repository.deleteAll());
        Set<Car> carsSetFromDb = repository.findAll();
        Assertions.assertEquals(0, carsSetFromDb.size());
    }

    @Test
    public void findByModelTest() throws SQLException {
        Collection<Car> carsList = new ArrayList<>();
        carsList.add(new Car("1", "Tesla"));
        carsList.add(new Car("2", "Toyota"));
        carsList.add(new Car("3", "Subaru"));
        carsList.add(new Car("4", "Toyota"));
        repository.createAll(carsList);

        Set<Car> carSet = repository.findByModel("Toyota");
        Set<Car> carSetExpected = new HashSet<>();
        carSetExpected.add(new Car("2", "Toyota"));
        carSetExpected.add(new Car("4", "Toyota"));
        Assertions.assertEquals(carSetExpected, carSet);
    }

}
