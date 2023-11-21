package ru.sberbank.edu;

import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.tools.Server;
import org.junit.jupiter.api.AfterEach;
import org.testng.annotations.*;

import static org.junit.Assert.*;

import ru.sberbank.edu.dbconnection.H2DbEmbedded;
import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarDbRepositoryImpl;
import ru.sberbank.edu.repository.CarRepository;
import ru.sberbank.edu.service.CarService;
import ru.sberbank.edu.service.CarServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class CarBootstrapTest {
    private Server server;
    private static H2DbEmbedded h2DbEmbedded;

    @BeforeClass
    public void initDBForTests() throws SQLException {
        String[] args = new String[]{};
        server = Server.createTcpServer(args).start();
        h2DbEmbedded = new H2DbEmbedded();
        H2DbEmbedded.initDb();
    }

    @AfterEach
    void deleteTableCar() throws Exception {
        String readAllCarsSql = "DELETE FROM car";
        Statement statement = H2DbEmbedded.getConnection().createStatement();
        statement.executeQuery(readAllCarsSql);
    }

    @AfterClass
    public void closeDB() throws Exception {
        h2DbEmbedded.close();
        server.stop();
    }

    @Test
    public void WhenAdd() throws Exception{
        List<String> result = new ArrayList<String>();
        String[] expectedResult = new String[]{"777", "Lada"};
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
        assertArrayEquals(expectedResult, result.toArray());
        System.out.println(H2DbEmbedded.getConnection());
    }


    @Test
    public void WhenDeleteById() throws Exception{
        List<String> result = new ArrayList<String>();
        String[] expectedResult = new String[]{"777", "Lada", "35", "Volvo", "55", "KIA", "65", "HONDA"};
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
        assertArrayEquals(expectedResult, result.toArray());
    }

    @Test
    public void WhenEditModel() throws Exception{
        List<String> result = new ArrayList<String>();
        String[] expectedResult = new String[]{"777", "Lada", "35", "Volvo", "55", "KIA", "65", "HONDA", "45", "Haval"};;
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
        assertArrayEquals(expectedResult, result.toArray());
    }

    @Test
    public void WhenCreateAll() throws Exception{
        List<String> result = new ArrayList();
        List<Car> forAdd = new ArrayList();
        String[] expectedResult = new String[]{"777", "Lada", "35", "Volvo","45", "BMW", "55", "KIA", "65", "HONDA"};
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
        assertArrayEquals(expectedResult, result.toArray());
    }

    @Test
    public void WhenDeleteAll() throws Exception{
        List<String> result = new ArrayList();
        List<Car> forAdd = new ArrayList();
        String[] expectedResult = new String[]{};
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
        assertArrayEquals(expectedResult, result.toArray());
    }

    @Test
    public void WhenFindByModel() throws Exception{
        Set<Car> result = new HashSet<>();
        List<Car> forAdd = new ArrayList();
        String expectedResult = "Car{id='45', model='BMW'}";
        CarRepository carRepository = new CarDbRepositoryImpl(H2DbEmbedded.getConnection());
        forAdd.add(new Car("777", "Lada"));
        forAdd.add(new Car("35", "Volvo"));
        forAdd.add(new Car("45", "BMW"));
        forAdd.add(new Car("55", "KIA"));
        forAdd.add(new Car("65", "HONDA"));
        carRepository.createAll(forAdd);
        result = carRepository.findByModel("BMW");
        assertEquals(expectedResult, result.toArray()[0].toString());
    }
}
