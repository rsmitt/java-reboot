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

    private static final String INPUT_FILE_PATH = "src/test/resources/inputFile.txt";
    private static final String INPUT_EMPTY_FILE_PATH = "src/test/resources/inputEmptyFile.txt";
    private static final String INPUT_SPACE_FILE_PATH = "src/test/resources/inputSpaceFile.txt";
    private static final String INPUT_INCORRECT_FILE_PATH = "src/test/resources/inputIncorrectFile.txt";
    private static final String OUTPUT_FILE_PATH = "src/test/resources/outputFile.txt";

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
        Reader reader = new FileReaderImpl();
        statistic = reader.calcStatistic(INPUT_FILE_PATH);
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
        Reader reader = new FileReaderImpl();
        statistic = reader.calcStatistic(INPUT_EMPTY_FILE_PATH);
        Assertions.assertAll(
                () -> Assertions.assertEquals(0, statistic.getLineCount()),
                () -> Assertions.assertEquals(0, statistic.getSpaceCount()),
                () -> Assertions.assertNull(statistic.getLongestLine())
        );
    }

    @Test
    public void fileReaderImplOnlySpaceTest() {
        Statistic statistic;
        Reader reader = new FileReaderImpl();
        statistic = reader.calcStatistic(INPUT_SPACE_FILE_PATH);
        Assertions.assertAll(
                () -> Assertions.assertEquals(3, statistic.getLineCount()),
                () -> Assertions.assertEquals(3, statistic.getSpaceCount()),
                () -> Assertions.assertEquals(" ", statistic.getLongestLine())
        );
    }

    @Test
    public void fileReaderImplIncorrectPathTest() {
        Reader reader = new FileReaderImpl();
        try {
            reader.calcStatistic(INPUT_INCORRECT_FILE_PATH);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println("fileReaderImplIncorrectPathTest return exception");
        }
    }

    @Test
    public void fileWriterImplTest() throws IOException {
        Statistic statistic = new StatisticImpl(1, 2, "Longest line");
        Writer writer = new FileWriterImpl();
        writer.save(statistic, OUTPUT_FILE_PATH);

        String line;
        int i = 0;
        String[] expected = {"File statistic", "Line count: 1", "Space count: 2", "The longest line: Longest line"};
        BufferedReader reader = new BufferedReader(new FileReader(OUTPUT_FILE_PATH));
        while ((line = reader.readLine()) != null) {
            Assertions.assertEquals(expected[i++], line);
        }
    }

}
