package ru.sberbank.edu;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import ru.sberbank.edu.StatisticFile;


public class StatisticFileTest {
    @Test
    public void WhenGetLongestLine() throws IOException {
        FileWriter writer = new FileWriter("file.txt");
        writer.write("""
                Вы помните,
                Вы всё, конечно, помните,
                Как я стоял,
                Приблизившись к стене,
                Взволнованно ходили вы по комнате
                И что-то резкое
                В лицо бросали мне.""");
        writer.close();
        File file = new File("file.txt");
        StatisticFile statisticFile = new StatisticFile();
        String ExpectedResult = "Взволнованно ходили вы по комнате";
        assertEquals(ExpectedResult, statisticFile.getLongestLine(file));
        file.delete();
    }

    @Test
    public void WhenGetLineCountThen7() throws IOException {
        FileWriter writer = new FileWriter("file.txt");
        writer.write("""
                Вы помните,
                Вы всё, конечно, помните,
                Как я стоял,
                Приблизившись к стене,
                Взволнованно ходили вы по комнате
                И что-то резкое
                В лицо бросали мне.""");
        writer.close();
        File file = new File("file.txt");
        StatisticFile statisticFile = new StatisticFile();
        int ExpectedResult = 7;
        assertEquals(ExpectedResult, statisticFile.getLineCount(file));
        file.delete();
    }

    @Test
    public void WhenGetSpaceCountThen17() throws IOException {
        FileWriter writer = new FileWriter("file.txt");
        writer.write("""
                Вы помните,
                Вы всё, конечно, помните,
                Как я стоял,
                Приблизившись к стене,
                Взволнованно ходили вы по комнате
                И что-то резкое
                В лицо бросали мне.""");
        writer.close();
        File file = new File("file.txt");
        StatisticFile statisticFile = new StatisticFile();
        int ExpectedResult = 17;
        assertEquals(ExpectedResult, statisticFile.getSpaceCount(file));
        file.delete();
    }
}
