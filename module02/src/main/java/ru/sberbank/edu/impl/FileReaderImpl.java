package ru.sberbank.edu.impl;

import ru.sberbank.edu.Reader;
import ru.sberbank.edu.Statistic;

import java.io.*;

public class FileReaderImpl implements Reader {

    private final String filePath;

    public FileReaderImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Statistic calcStatistic() {
        int countLine = 0;
        int countSpace = 0;
        int size = 0;
        String longestLine = null;
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                ++countLine;
                countSpace = countSpace + line.length() - line.replaceAll(" ", "").length();
                if (line.length() > size) {
                    longestLine = line;
                    size = line.length();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while work with file " + filePath + "\n", e);
        }

        return new StatisticImpl(countLine, countSpace, longestLine);
    }

}
