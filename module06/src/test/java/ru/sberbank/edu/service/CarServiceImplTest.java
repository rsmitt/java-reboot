package ru.sberbank.edu.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.sberbank.edu.dbconnection.H2DbEmbedded;
import ru.sberbank.edu.repository.CarDbRepositoryImpl;
import ru.sberbank.edu.repository.CarRepository;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarServiceImplTest {
    CarRepository carRepository;
    CarService carService;
    @BeforeAll
    void beforeAll() {
        try {
            H2DbEmbedded.initDb();
            carRepository = new CarDbRepositoryImpl(H2DbEmbedded.getConnection());
            carService = new CarServiceImpl(carRepository);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void addCar() {
        try {
            carService.addCar("1", "BMW");
            assertEquals("[Car{id='1', model='BMW'}]", carRepository.findAll().toString());
            carService.addCar("1", "AUDI");
            assertEquals("[Car{id='1', model='AUDI'}]", carRepository.findAll().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void editModel() {
        try {
            carService.addCar("1", "BMW");
            carService.editModel("1", "AUDI");
            assertEquals("[Car{id='1', model='AUDI'}]", carRepository.findAll().toString());
            Exception exception = assertThrows(NoSuchElementException.class, () -> carService.editModel("2", "VW"));
            assertEquals("No value present", exception.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteCar() {
        try {
            carService.addCar("1", "BMW");
            assertEquals("[Car{id='1', model='BMW'}]", carRepository.findAll().toString());
            carService.deleteCar("1");
            assertEquals("[]", carRepository.findAll().toString());
            carService.deleteCar("1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}