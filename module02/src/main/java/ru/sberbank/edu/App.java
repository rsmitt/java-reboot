package ru.sberbank.edu;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        StatisticCollectorToFile result = new StatisticCollectorToFile("module02/src/main/resources/dev_data", "AppResult.txt");
        result.save(result.getLineCount(), result.getSpaceCount(), result.getLongestLine());
    }
}
