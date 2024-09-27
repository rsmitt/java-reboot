package ru.sberbank.edu;

public class StatisticCollectorToDB extends StatisticCollector {
    StatisticCollectorToDB(String file) {
        super(file);
    }

    @Override
    public void save(int lineCount, int spaceCount, String line) {

// DB recording...........

    }
}