package ru.sberbank.edu;

import static org.junit.Assert.assertEquals;
import ru.sberbank.edu.model.Car;
import org.h2.tools.Server;
import ru.sberbank.edu.dbconnection.H2DbEmbedded;
import ru.sberbank.edu.repository.CarDbRepositoryImpl;
import ru.sberbank.edu.repository.CarRepository;
import ru.sberbank.edu.service.CarService;
import ru.sberbank.edu.service.CarServiceImpl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void checkAll() throws Exception {
        Server server = Server.createTcpServer().start();
        H2DbEmbedded.initDb();

        try(H2DbEmbedded h2DbEmbedded = new H2DbEmbedded()) {
            CarRepository carRepository = new CarDbRepositoryImpl(H2DbEmbedded.getConnection());
            CarService carService = new CarServiceImpl(carRepository);

            /**
             * Проверка метода addCar и findById
             */
            carService.addCar("777", "Lada");
            carService.addCar("888", "Lexus");
            carService.addCar("222", "BMW");

            assertEquals(carRepository.findById("888").toString(), "Optional[Car{id='888', model='Lexus'}]");
            assertEquals(carRepository.findById("777").toString(), "Optional[Car{id='777', model='Lada'}]");

            /**
             * Проверка методов createAll и findByModel, что найдено 2 машины "BMW" и 1 "Hyundai"
             */
            Collection<Car> cars = new ArrayList<>();
            cars.add(new Car("000", "BMW"));
            cars.add(new Car("111", "Hyundai"));
            carRepository.createAll(cars);

            assertEquals(carRepository.findByModel("BMW").size(), 2);
            assertEquals(carRepository.findByModel("Hyundai").size(), 1);

            /**
             * Проверка метода createOrUpdate, findById и findByModel
             */
            carRepository.createOrUpdate(new Car("888", "Ferrari"));
            carRepository.createOrUpdate(new Car("333", "Toyota"));
            assertEquals(carRepository.findByModel("Lexus"), new HashSet<Car>());
            assertEquals(carRepository.findById("888").toString(), "Optional[Car{id='888', model='Ferrari'}]");
            assertEquals(carRepository.findById("777").toString(), "Optional[Car{id='777', model='Lada'}]");

            /**
             * Проверка метода createOrUpdate, findById и findByModel
             */
            carService.editModel("333", "Opel");
            assertEquals(carRepository.findById("333").toString(), "Optional[Car{id='333', model='Opel'}]");

            /**
             * Проверка метода deleteCar и deleteById
             */
            carService.deleteCar("888");
            assertEquals(carRepository.findById("888").toString(), Optional.empty().toString());
            carRepository.deleteById("777");
            assertEquals(carRepository.findById("888").toString(), Optional.empty().toString());

            /**
             * Проверка метода deleteAll
             */
            carRepository.deleteAll();
            assertEquals(carRepository.findAll(), new HashSet<Car>());

            // Test check start
            String readAllCarsSql = "SELECT * FROM car";
            Statement statement = H2DbEmbedded.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(readAllCarsSql);

            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String model = resultSet.getString(2);
                System.out.println("id=" + id + "; model=" + model);
            }
            // Test end
        }
        server.stop();
    }
}