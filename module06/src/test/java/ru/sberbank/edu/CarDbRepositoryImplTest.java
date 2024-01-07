package ru.sberbank.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarDbRepositoryImpl;

import java.sql.*;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.sberbank.edu.repository.CarDbRepositoryImpl.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarDbRepositoryImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement createPreStmt;
    @Mock
    private PreparedStatement statement;
    @Mock
    private PreparedStatement deleteByIdPreStmt;
    @Mock
    private PreparedStatement findByModelPreStmt;

    @Mock
    private ResultSet resultSet;

    private CarDbRepositoryImpl carDbRepositoryImpl;

    @BeforeEach
    void setUp() throws SQLException {
        when(connection.prepareStatement(CREATE_CAR_SQL)).thenReturn(createPreStmt);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(connection.prepareStatement(DELETE_CAR_BY_ID)).thenReturn(deleteByIdPreStmt);
        when(connection.prepareStatement(SELECT_CAR_BY_MODEL)).thenReturn(findByModelPreStmt);
        carDbRepositoryImpl = new CarDbRepositoryImpl(connection);
    }
    @Test
    void testCreateAll() throws SQLException {
        Car car = new Car("1", "ModelX");
        Set<Car> cars = Collections.singleton(car);
        Set<Car> result = carDbRepositoryImpl.createAll(cars);
        verify(createPreStmt, times(1)).executeUpdate();
        assertEquals(cars, result);
    }
    @Test
    void testFindAll() throws SQLException {
        // Arrange
        Car car = new Car("1", "ModelX");
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("id")).thenReturn(car.getId());
        when(resultSet.getString("model")).thenReturn(car.getModel());
        Set<Car> expectedCars = Collections.singleton(car);


        Set<Car> result = carDbRepositoryImpl.findAll();


        verify(statement, times(1)).executeQuery(CarDbRepositoryImpl.FIND_ALL_CARS);
        assertEquals(expectedCars, result);
    }
    @Test
    public void testDeleteByIdSuccess() throws SQLException {
        String id = "someId";
        when(deleteByIdPreStmt.executeUpdate()).thenReturn(1); //
        boolean result = carDbRepositoryImpl.deleteById(id);
        assertTrue(result);
        verify(deleteByIdPreStmt).setString(1, id); //
        verify(deleteByIdPreStmt).executeUpdate();
    }
    @Test
    public void testDeleteAllSuccess() throws SQLException {
        Statement statement = mock(Statement.class);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeUpdate(DELETE_ALL_CARS)).thenReturn(1);
        boolean result = carDbRepositoryImpl.deleteAll();
        assertTrue(result);
        verify(statement).executeUpdate(anyString());
    }
    @Test
    public void whenFindByModel_thenReturnsSetOfCars() throws SQLException {
        // Arrange
        String model = "TestModel";
        when(findByModelPreStmt.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false); // Возвращаем два автомобиля и затем завершаем итерацию
        when(resultSet.getString("id")).thenReturn("1", "2");

        // Act
        Set<Car> result = carDbRepositoryImpl.findByModel(model);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(car -> car.getId().equals("1")));
        assertTrue(result.stream().anyMatch(car -> car.getId().equals("2")));
        verify(findByModelPreStmt, times(1)).setString(1, model); // Проверяем, что setString вызывался один раз с корректными параметрами
        verify(findByModelPreStmt, times(1)).executeQuery(); // Проверяем, что executeQuery вызывался ровно один раз
        verify(resultSet, atLeastOnce()).close(); // Проверяем, что resultSet был закрыт
    }

    @Test
    public void whenFindByModelAndSQLException_thenThrowsRuntimeException() throws SQLException {
        // Arrange
        String model = "TestModel";
        when(findByModelPreStmt.executeQuery()).thenThrow(new SQLException());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> carDbRepositoryImpl.findByModel(model));
    }


}