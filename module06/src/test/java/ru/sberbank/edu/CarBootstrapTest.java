package ru.sberbank.edu;

import org.h2.tools.Server;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import static org.junit.Assert.*;

import ru.sberbank.edu.dbconnection.H2DbEmbedded;
import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarDbRepositoryImpl;
import ru.sberbank.edu.repository.CarRepository;
import ru.sberbank.edu.service.CarService;
import ru.sberbank.edu.service.CarServiceImpl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CarBootstrapTest {
    @Test
    public void WhenAdd() throws Exception{
        String[] args = new String[]{};
        List<String> result = new ArrayList<String>();
        String[] expectedResult = new String[]{"777", "Lada"};
        Server server = Server.createTcpServer(args).start();
        H2DbEmbedded.initDb();
        try(H2DbEmbedded h2DbEmbedded = new H2DbEmbedded()) {
            CarRepository carRepository = new CarDbRepositoryImpl(H2DbEmbedded.getConnection());
            CarService carService = new CarServiceImpl(carRepository);
            carService.addCar("777", "Lada");
            String readAllCarsSql = "SELECT * FROM car";
            Statement statement = H2DbEmbedded.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(readAllCarsSql);
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
                result.add(resultSet.getString(2));
            }
        }
        server.stop();
        assertArrayEquals(expectedResult, result.toArray());
        System.out.println(H2DbEmbedded.getConnection());
    }


    @Test
    public void WhenDeleteById() throws Exception{
        String[] args = new String[]{};
        List<String> result = new ArrayList<String>();
        String[] expectedResult = new String[]{"777", "Lada", "35", "Volvo", "55", "KIA", "65", "HONDA"};
        Server server = Server.createTcpServer(args).start();
        H2DbEmbedded.initDb();
        try(H2DbEmbedded h2DbEmbedded = new H2DbEmbedded()) {
            CarRepository carRepository = new CarDbRepositoryImpl(H2DbEmbedded.getConnection());
            CarService carService = new CarServiceImpl(carRepository);
            carService.addCar("777", "Lada");
            carService.addCar("35", "Volvo");
            carService.addCar("45", "BMW");
            carService.addCar("55", "KIA");
            carService.addCar("65", "HONDA");
            carService.deleteCar("45");
            String readAllCarsSql = "SELECT * FROM car";
            Statement statement = H2DbEmbedded.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(readAllCarsSql);
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
                result.add(resultSet.getString(2));
            }
        }
        server.stop();
        assertArrayEquals(expectedResult, result.toArray());
    }

    @Test
    public void WhenEditModel() throws Exception{
        String[] args = new String[]{};
        List<String> result = new ArrayList<String>();
        String[] expectedResult = new String[]{"777", "Lada", "35", "Volvo", "45",  "Haval", "55", "KIA", "65", "HONDA"};
        Server server = Server.createTcpServer(args).start();
        H2DbEmbedded.initDb();
        try(H2DbEmbedded h2DbEmbedded = new H2DbEmbedded()) {
            CarRepository carRepository = new CarDbRepositoryImpl(H2DbEmbedded.getConnection());
            CarService carService = new CarServiceImpl(carRepository);
            carService.addCar("777", "Lada");
            carService.addCar("35", "Volvo");
            carService.addCar("45", "BMW");
            carService.addCar("55", "KIA");
            carService.addCar("65", "HONDA");
            carService.editModel("45",  "Haval");
            String readAllCarsSql = "SELECT * FROM car";
            Statement statement = H2DbEmbedded.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(readAllCarsSql);
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
                result.add(resultSet.getString(2));
            }
        }
        server.stop();
        assertArrayEquals(expectedResult, result.toArray());
    }

    @AfterTest
    public void postSignUp()
    {
        server.stop();
    }

    @Test
    public void WhenCreateAll() throws Exception{
        String[] args = new String[]{};
        List<String> result = new ArrayList();
        List<Car> forAdd = new ArrayList();
        String[] expectedResult = new String[]{"777", "Lada", "35", "Volvo","45", "BMW", "55", "KIA", "65", "HONDA"};
        Server server = Server.createTcpServer(args).start();
        H2DbEmbedded.initDb();
        try(H2DbEmbedded h2DbEmbedded = new H2DbEmbedded()) {
            CarRepository carRepository = new CarDbRepositoryImpl(H2DbEmbedded.getConnection());
            forAdd.add(new Car("777", "Lada"));
            forAdd.add(new Car("35", "Volvo"));
            forAdd.add(new Car("45", "BMW"));
            forAdd.add(new Car("55", "KIA"));
            forAdd.add(new Car("65", "HONDA"));
            carRepository.createAll(forAdd);
            String readAllCarsSql = "SELECT * FROM car";
            Statement statement = H2DbEmbedded.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(readAllCarsSql);
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
                result.add(resultSet.getString(2));
            }
        }
        server.stop();
        assertArrayEquals(expectedResult, result.toArray());
    }

    @Test
    public void WhenDeleteAll() throws Exception{
        String[] args = new String[]{};
        List<String> result = new ArrayList();
        List<Car> forAdd = new ArrayList();
        String[] expectedResult = new String[]{};
        Server server = Server.createTcpServer(args).start();
        H2DbEmbedded.initDb();
        try(H2DbEmbedded h2DbEmbedded = new H2DbEmbedded()) {
            CarRepository carRepository = new CarDbRepositoryImpl(H2DbEmbedded.getConnection());
            forAdd.add(new Car("777", "Lada"));
            forAdd.add(new Car("35", "Volvo"));
            forAdd.add(new Car("45", "BMW"));
            forAdd.add(new Car("55", "KIA"));
            forAdd.add(new Car("65", "HONDA"));
            carRepository.createAll(forAdd);
            carRepository.deleteAll();
            String readAllCarsSql = "SELECT * FROM car";
            Statement statement = H2DbEmbedded.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(readAllCarsSql);
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
                result.add(resultSet.getString(2));
            }
        }
        server.stop();
        assertArrayEquals(expectedResult, result.toArray());
    }

    @Test
    public void WhenFindByModel() throws Exception{
        String[] args = new String[]{};
        Set<Car> result = new HashSet<>();
        List<Car> forAdd = new ArrayList();
        String expectedResult = "Car{id='45', model='BMW'}";
        Server server = Server.createTcpServer(args).start();
        H2DbEmbedded.initDb();
        try(H2DbEmbedded h2DbEmbedded = new H2DbEmbedded()) {
            CarRepository carRepository = new CarDbRepositoryImpl(H2DbEmbedded.getConnection());
            forAdd.add(new Car("777", "Lada"));
            forAdd.add(new Car("35", "Volvo"));
            forAdd.add(new Car("45", "BMW"));
            forAdd.add(new Car("55", "KIA"));
            forAdd.add(new Car("65", "HONDA"));
            carRepository.createAll(forAdd);
            result = carRepository.findByModel("BMW");

        }
        server.stop();
        assertEquals(expectedResult, result.toArray()[0].toString());
    }
}
