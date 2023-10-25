package ru.sberbank.edu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StatisticFile implements Statistic{
    @Override
    public int getLineCount(File file) {
        return 0;
    }

    @Override
    public int getSpaceCount(File file) {
        return 0;
    }

    @Override
    public String getLongestLine(File file) {
        return null;
    }

    @Override
    public void save(int lineCount, int spaceCount, String line) throws IOException {
        FileWriter writerResult = new FileWriter("ResultFile.txt");
        writerResult.write("Количество строк в файле: " + lineCount + "\n" +
                "Количество пробелов в файле: " + spaceCount + "\n" +
                "Самая длинная строка: " + line);
        writerResult.close();

    }
}
