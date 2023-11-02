package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sberbank.edu.impl.FileReaderImpl;
import ru.sberbank.edu.impl.FileWriterImpl;
import ru.sberbank.edu.impl.StatisticImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StatisticImplTest {

    @Test
    public void statisticImplTest() {
        Statistic statistic = new StatisticImpl(1 ,2, "Longest line");
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, statistic.getLineCount()),
                () -> Assertions.assertEquals(2, statistic.getSpaceCount()),
                () -> Assertions.assertEquals("Longest line", statistic.getLongestLine())
        );
    }

    @Test
    public void fileReaderImplWithTextTest() {
        Statistic statistic;
        Reader reader = new FileReaderImpl("src/test/resources/inputFile.txt");
        statistic = reader.calcStatistic();
        Assertions.assertAll(
                () -> Assertions.assertEquals(6, statistic.getLineCount()),
                () -> Assertions.assertEquals(89, statistic.getSpaceCount()),
                () -> Assertions.assertEquals(
                        "поправляла несколько раз складки своего платья и, когда рассказ производил впечатление, оглядывалась на Анну Павловну ",
                        statistic.getLongestLine())
        );
    }

    @Test
    public void fileReaderImplNoTextTest() {
        Statistic statistic;
        Reader reader = new FileReaderImpl("src/test/resources/inputEmptyFile.txt");
        statistic = reader.calcStatistic();
        Assertions.assertAll(
                () -> Assertions.assertEquals(0, statistic.getLineCount()),
                () -> Assertions.assertEquals(0, statistic.getSpaceCount()),
                () -> Assertions.assertNull(statistic.getLongestLine())
        );
    }

    @Test
    public void fileReaderImplOnlySpaceTest() {
        Statistic statistic;
        Reader reader = new FileReaderImpl("src/test/resources/inputSpaceFile.txt");
        statistic = reader.calcStatistic();
        Assertions.assertAll(
                () -> Assertions.assertEquals(3, statistic.getLineCount()),
                () -> Assertions.assertEquals(3, statistic.getSpaceCount()),
                () -> Assertions.assertEquals(" ", statistic.getLongestLine())
        );
    }

    @Test
    public void fileReaderImplIncorrectPathTest() {
        Reader reader = new FileReaderImpl("src/test/resources/inputIncorrectFile.txt");
        try {
            reader.calcStatistic();
            Assertions.fail();
        } catch (Exception e) {
            System.out.println("fileReaderImplIncorrectPathTest return exception");
        }
    }

    @Test
    public void fileWriterImplTest() throws IOException {
        Statistic statistic = new StatisticImpl(1, 2, "Longest line");
        Writer writer = new FileWriterImpl("src/test/resources/outputFile.txt");
        writer.write(statistic);

        String line;
        int i = 0;
        String[] expected = {"File statistic", "Line count: 1", "Space count: 2", "The longest line: Longest line"};
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/outputFile.txt"));
        while ((line = reader.readLine()) != null) {
            Assertions.assertEquals(expected[i++], line);
        }
    }

}
