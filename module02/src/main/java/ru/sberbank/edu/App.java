package ru.sberbank.edu;

import ru.sberbank.edu.impl.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Statistic statistic;
        Reader reader;
        Writer writer;

        reader = new FileReaderImpl();
        writer = new FileWriterImpl();
        statistic = reader.calcStatistic("module02/src/test/resources/inputFile.txt");
        writer.save(statistic, "module02/src/test/resources/outputFile.txt");

        reader = new DBReaderImpl();
        writer = new DBWriterImpl();
        statistic = reader.calcStatistic("DB connect read");
        writer.save(statistic, "DB connect write");
    }
}
