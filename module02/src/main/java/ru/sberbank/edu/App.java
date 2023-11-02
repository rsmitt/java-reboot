package ru.sberbank.edu;

import ru.sberbank.edu.impl.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Reader reader = new FileReaderImpl("module02/src/test/resources/inputFile.txt");
        Writer writer = new FileWriterImpl("module02/src/test/resources/outputFile.txt");
        Statistic statistic = reader.calcStatistic();
        writer.write(statistic);

        reader = new DBReaderImpl("jdbc:mysql://localhost:3306/myDb", "user1", "pass");
        writer = new DBWriterImpl("jdbc:mysql://localhost:3306/myDb", "user1", "pass");
        statistic = reader.calcStatistic();
        writer.write(statistic);
    }
}
