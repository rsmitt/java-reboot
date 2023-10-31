import org.junit.Assert;
import org.junit.Test;
import ru.sberbank.edu.StatisticCollectorToFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FillingTest {

    @Test
    public void positiveTest() throws IOException {

        StatisticCollectorToFile collector = new StatisticCollectorToFile("src/test/resources/test_data");

        Assert.assertEquals(collector.getSpaceCount(), 24);
        Assert.assertEquals(collector.getLineCount(), 11);
        Assert.assertEquals(collector.getLongestLine(), " d gdfg dfgdf g  fsd");

    }

    @Test
    public void emptyTest() throws IOException {

        StatisticCollectorToFile collector = new StatisticCollectorToFile("src/test/resources/test_empty_data");

        Assert.assertEquals(collector.getSpaceCount(), 0);
        Assert.assertEquals(collector.getLineCount(), 0);
        Assert.assertEquals(collector.getLongestLine(), "");

    }

    @Test
    public void blankTest() throws IOException {

        StatisticCollectorToFile collector = new StatisticCollectorToFile("src/test/resources/test_blank_data");

        Assert.assertEquals(collector.getSpaceCount(), 0);
        Assert.assertEquals(collector.getLineCount(), 14);
        Assert.assertEquals(collector.getLongestLine(), "");

    }

    @Test(expected = java.io.FileNotFoundException.class)
    public void fileNotFoundTest() throws IOException {

        StatisticCollectorToFile collector = new StatisticCollectorToFile("src/test/resources/aaa");

        Assert.assertEquals(collector.getSpaceCount(), 0);

    }

    @Test
    public void savingTestPositive() throws IOException {

        StatisticCollectorToFile collectorPositive = new StatisticCollectorToFile("src/test/resources/test_data", "positive.txt");
        collectorPositive.save(collectorPositive.getLineCount(), collectorPositive.getSpaceCount(), collectorPositive.getLongestLine());

        BufferedReader reader = new BufferedReader(new FileReader("positive.txt"));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        String ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();

        Assert.assertEquals("""
                Counted lines: 11
                Counted spaces: 24
                The longest line: " d gdfg dfgdf g  fsd\"""", content);

    }

    @Test
    public void savingTestEmpty() throws IOException {

        StatisticCollectorToFile collectorEmpty = new StatisticCollectorToFile("src/test/resources/test_empty_data", "empty.txt");
        collectorEmpty.save(collectorEmpty.getLineCount(), collectorEmpty.getSpaceCount(), collectorEmpty.getLongestLine());

        BufferedReader reader = new BufferedReader(new FileReader("empty.txt"));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        String ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();

        Assert.assertEquals("""
                Counted lines: 0
                Counted spaces: 0
                The longest line: "\"""", content);

    }

    @Test
    public void savingTestBlank() throws IOException {

        StatisticCollectorToFile collectorBlank = new StatisticCollectorToFile("src/test/resources/test_blank_data", "blank.txt");
        collectorBlank.save(collectorBlank.getLineCount(), collectorBlank.getSpaceCount(), collectorBlank.getLongestLine());

        BufferedReader reader = new BufferedReader(new FileReader("blank.txt"));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        String ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();

        Assert.assertEquals("""
                Counted lines: 14
                Counted spaces: 0
                The longest line: "\"""", content);

    }

}
