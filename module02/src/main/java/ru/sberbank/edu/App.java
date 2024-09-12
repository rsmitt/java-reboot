package ru.sberbank.edu;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {

        StatisticImpl main = new StatisticImpl();
        main.start();
    }
}