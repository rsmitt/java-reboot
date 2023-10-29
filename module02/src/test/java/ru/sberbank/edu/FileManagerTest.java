package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class FileManagerTest {

    @Test
    void getLineCount() {

        FileManager fileManagerMock = Mockito.spy(FileManager.class);
        List<String> testList = Arrays.asList("Buenos Aires", "Córdoba", "La Plata");
        Mockito.when(fileManagerMock.getContentList()).thenReturn(testList);
        Assertions.assertEquals(fileManagerMock.getLineCount(),3);

    }

    @Test
    void getSpaceCount() {
        FileManager fileManagerMock = Mockito.spy(FileManager.class);
        List<String> testList = Arrays.asList("Buenos Aires", "Córdoba", "La Plata");
        Mockito.when(fileManagerMock.getContentList()).thenReturn(testList);
        Assertions.assertEquals(fileManagerMock.getSpaceCount(),2);

    }

    @Test
    void getLongestLine() {
        FileManager fileManagerMock = Mockito.spy(FileManager.class);
        List<String> testList = Arrays.asList("Buenos Aires", "Córdoba", "La Plata");
        Mockito.when(fileManagerMock.getContentList()).thenReturn(testList);
        Assertions.assertEquals(fileManagerMock.getLongestLine(),"Buenos Aires");
    }
}