package ru.sberbank.edu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.sberbank.edu.dbconnection.H2DbEmbedded;
import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarDbRepositoryImpl;
import ru.sberbank.edu.repository.CarRepository;
import ru.sberbank.edu.service.CarServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

public class CarServiceImplTest {

    private static CarRepository repository;

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
    public void addCarTest() throws SQLException {
        CarServiceImpl service = new CarServiceImpl(repository);
        service.addCar("1", "Toyota");

        Set<Car> carSet = repository.findAll();
        Assertions.assertEquals(1, carSet.size());
        Assertions.assertEquals("1", carSet.iterator().next().getId());
        Assertions.assertEquals("Toyota", carSet.iterator().next().getModel());
    }

    @Test
    public void editModelTest() throws SQLException {
        CarServiceImpl service = new CarServiceImpl(repository);
        service.addCar("1", "Toyota");
        service.editModel("1", "Subaru");

        Set<Car> carSet = repository.findAll();
        Assertions.assertEquals(1, carSet.size());
        Assertions.assertEquals("1", carSet.iterator().next().getId());
        Assertions.assertEquals("Subaru", carSet.iterator().next().getModel());
    }

    @Test
    public void deleteCarTest() throws SQLException {
        CarServiceImpl service = new CarServiceImpl(repository);
        service.addCar("1", "Toyota");
        service.deleteCar("1");

        Assertions.assertEquals(0, repository.findAll().size());
    }

}
