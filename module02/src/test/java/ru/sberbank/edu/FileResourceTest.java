package ru.sberbank.edu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileResourceTest {

    @Mock
    private FileResource fileResource;


    @Test
    void testReadData() throws IOException {

        String expectedData = "Hello, world!";


        when(fileResource.readData()).thenReturn(expectedData);

        String actualData = fileResource.readData();

        assertEquals(expectedData, actualData);


    }
}
