package ru.sberbank.edu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class App 
{
    public static void main( String[] args ) throws IOException {
        FileWriter writer = new FileWriter("file.txt");
        writer.write("Вы помните,\n" +
                "Вы всё, конечно, помните,\n" +
                "Как я стоял,\n" +
                "Приблизившись к стене,\n" +
                "Взволнованно ходили вы по комнате\n" +
                "И что-то резкое\n" +
                "В лицо бросали мне.");
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
