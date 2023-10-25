package ru.sberbank.edu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
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
        Save saveFile = new SaveFile();
        Save saveDB = new SaveBD();
        statisticFile.save(saveFile, statisticFile.getLineCount(file),
                statisticFile.getSpaceCount(file), statisticFile.getLongestLine(file));
        statisticFile.save(saveDB, statisticFile.getLineCount(file),
                statisticFile.getSpaceCount(file), statisticFile.getLongestLine(file));
    }
}
