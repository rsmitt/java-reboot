package ru.sberbank.edu;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

        StatisticsImpl statisticsImplForFile = new StatisticsImplForFile("input.txt");
        statisticsImplForFile.save();

        StatisticsImpl statisticsImplForDB = new StatisticsImplForDB("input.txt");
        statisticsImplForDB.save();
    }
}
