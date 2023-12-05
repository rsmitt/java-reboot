package ru.sberbank.edu.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.sberbank.edu.dbconnection.H2DbEmbedded;
import ru.sberbank.edu.model.Car;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarDbRepositoryImplTest {
    CarRepository carRepository;

    @BeforeAll
    void beforeAll() {
        try {
            H2DbEmbedded.initDb();
            carRepository = new CarDbRepositoryImpl(H2DbEmbedded.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createOrUpdate() {
        Car testCar1 = new Car("1", "BMW");
        Car testCar2 = new Car("1", "AUDI");
        try {
            Car car = carRepository.createOrUpdate(testCar1);
            assertEquals(car, testCar1);
            car = carRepository.findById(testCar1.getId()).get();
            assertEquals(car, testCar1);
            car = carRepository.createOrUpdate(testCar2);
            assertEquals(car, testCar2);
            car = carRepository.findById(testCar1.getId()).get();
            assertEquals(car, testCar2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createAll() {
        createData();
        Set<Car> carSet = carRepository.findAll();
        assertEquals("[Car{id='1', model='BMW'}, Car{id='2', model='VW'}, Car{id='3', model='ВАЗ'}]", carSet.toString());
    }

    @Test
    void findAll() {
        createData();
        Set<Car> carSet = carRepository.findAll();
        assertEquals("[Car{id='1', model='BMW'}, Car{id='2', model='VW'}, Car{id='3', model='ВАЗ'}]", carSet.toString());
    }

    @Test
    void findById() {
        createData();
        try {
            Optional<Car> carOptional = carRepository.findById("3");
            assertTrue(carOptional.isPresent());
            assertEquals("Car{id='3', model='ВАЗ'}", carOptional.get().toString());
            carOptional = carRepository.findById("0");
            assertFalse(carOptional.isPresent());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteById() {
        createData();
        Boolean result = carRepository.deleteById("2");
        assertEquals(true, result);
        Set<Car> carSet = carRepository.findAll();
        assertEquals("[Car{id='1', model='BMW'}, Car{id='3', model='ВАЗ'}]", carSet.toString());
        result = carRepository.deleteById("2");
        assertEquals(false, result);
    }

    @Test
    void deleteAll() {
        createData();
        Boolean result = carRepository.deleteAll();
        assertEquals(true, result);
        Set<Car> carSet = carRepository.findAll();
        assertEquals("[]", carSet.toString());
    }

    @Test
    void findByModel() {
        createData();
        Set<Car> carSet = carRepository.findByModel("BMW");
        assertEquals("[Car{id='1', model='BMW'}]", carSet.toString());
        carSet = carRepository.findByModel("ГАЗ");
        assertEquals("[]", carSet.toString());
    }

    private void createData() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("1", "BMW"));
        cars.add(new Car("2", "VW"));
        cars.add(new Car("3", "ВАЗ"));
        carRepository.createAll(cars);
    }

}