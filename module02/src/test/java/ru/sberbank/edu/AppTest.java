package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AppTest {
    @Test
    @DisplayName("")
    public void test() throws IOException {

        StatisticsImpl statisticsForFile = new StatisticsImplForFile("input_test.txt");
        StatisticsImpl statisticsForDB = new StatisticsImplForDB("input_test.txt");

       Assertions.assertTrue(statisticsForFile.getLineCount() >= 0 &&
               statisticsForFile.getSpaceCount() >= 0 &&
               statisticsForFile.getFile_path().getClass().getTypeName().equals("java.lang.String") &&
               statisticsForDB.getLineCount() >= 0 &&
               statisticsForDB.getSpaceCount() >= 0 &&
               statisticsForDB.getFile_path().getClass().getTypeName().equals("java.lang.String")
       );
    }
}
