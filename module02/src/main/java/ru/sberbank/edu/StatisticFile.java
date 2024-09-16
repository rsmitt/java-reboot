package ru.sberbank.edu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс собирает статистику из файла
 */
public class StatisticFile implements Statistic{
    public StatisticFile() {
    }

    /**
     * Метод возвращает количество строк в файле
     */
    public int getLineCount(File file) throws IOException {
        int countLine = 0;
        try(BufferedReader fileReader = new BufferedReader(new FileReader(file)))
        {
            for(String line = fileReader.readLine(); line != null; line = fileReader.readLine()) {
                ++countLine;
            }
        }
        return countLine;
    }

    /**
     * Метод возвращает количество пробелов в файле
     */
    public int getSpaceCount(File file) throws IOException {
        int countSpace = 0;
        try(FileReader fileReader = new FileReader(file))
        {
            for(int symbol = fileReader.read(); symbol != -1; symbol = fileReader.read()) {
                if (symbol == 32) {
                    ++countSpace;
                }
            }
        }
        return countSpace;
    }

    /**
     * Метод возвращает самую длинную строку в файле в файле
     */
    public String getLongestLine(File file) throws IOException {
        String longestLine = "";
        try(BufferedReader fileReader = new BufferedReader(new FileReader(file));)
        {
            for(String line = fileReader.readLine(); line != null; line = fileReader.readLine()) {
                if (longestLine.length() < line.length()) {
                    longestLine = line;
                }
            }
        }
        return longestLine;
    }

    /**
     * Метод сохраняет статитстику файла в файл или в базу данных на выбор пользователя
     */
    public void save(Save save, int lineCount, int spaceCount, String line) {
        save.save(lineCount, spaceCount, line);
    }
}

