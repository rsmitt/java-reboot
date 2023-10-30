package ru.sberbank.edu.impl;

import ru.sberbank.edu.Reader;
import ru.sberbank.edu.Statistic;

import java.io.*;

public class FileReaderImpl implements Reader {


    @Override
    public Statistic calcStatistic(Object source) {
        int countLine = 0;
        int countSpace = 0;
        int size = 0;
        String longestLine = null;
        String line;
        BufferedReader reader;

        try {
            reader = openFile((String) source);
            while ((line = reader.readLine()) != null) {
                ++countLine;
                countSpace = countSpace + line.length() - line.replaceAll(" ", "").length();
                if (line.length() > size) {
                    longestLine = line;
                    size = line.length();
                }
            }
            closeFile(reader);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while work with file " + source.toString() + "\n", e);
        }

        return new StatisticImpl(countLine, countSpace, longestLine);
    }

    private BufferedReader openFile(String path) throws FileNotFoundException {
        return new BufferedReader(new FileReader(path));
    }

    private void closeFile(BufferedReader reader) throws IOException {
        if (reader != null) {
            reader.close();
        }
    }


}
