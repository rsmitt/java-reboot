package ru.sberbank.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataResourceProcessorTest {
    @Mock
    private DataResource dataResourceMock;
    @InjectMocks
    private DataResourceProcessor processor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessResource() throws IOException, SQLException {
        // Создаем экземпляр DataResourceProcessor для тестирования метода processResource()
        processor = new DataResourceProcessor(dataResourceMock);

        // Задаем ожидания для мока DataResource
        String[] lines = {"hello", "world", "java"};
        Mockito.when(dataResourceMock.readData()).thenReturn(lines[0], lines[1], lines[2], null);

        // Вызываем метод processResource()
        String result = processor.processResource();

        // Проверяем, что методы мока были вызваны правильное количество раз
        Mockito.verify(dataResourceMock, Mockito.times(lines.length + 1)).readData();
        Mockito.verify(dataResourceMock, Mockito.times(1)).close();

        // Проверяем, что результат соответствует ожидаемому значению
        String expected = "Line count: " + lines.length + ", longest line: " + lines[0] + ", total spaces: 0";
        assertEquals(expected, result);
    }


    @Test
    public void testCountSpaces() {
        // Создаем экземпляр DataResourceProcessor для тестирования метода countSpaces()
        processor = new DataResourceProcessor(dataResourceMock);

        // Вызываем метод countSpaces()
        int result = processor.countSpaces("hello world");

        // Проверяем, что результат равен ожидаемому значению
        int expected = 1;
        assertEquals(expected, result);
    }
}
