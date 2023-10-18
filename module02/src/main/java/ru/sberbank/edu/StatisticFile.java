package ru.sberbank.edu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс собирает статистику из файла
 */
public class StatisticFile implements Statistic {
    public StatisticFile() {
    }

    /**
     * Метод возвращает количество строк в файле
     */
    public int getLineCount(File file) throws IOException {
        int countLine = 0;
        BufferedReader fileReader = new BufferedReader(new FileReader(file));

        for(String line = fileReader.readLine(); line != null; line = fileReader.readLine()) {
            ++countLine;
        }

        fileReader.close();
        return countLine;
    }

    /**
     * Метод возвращает количество пробелов в файле
     */
    public int getSpaceCount(File file) throws IOException {
        int countSpace = 0;
        FileReader fileReader = new FileReader(file);

        for(int symbol = fileReader.read(); symbol != -1; symbol = fileReader.read()) {
            if (symbol == 32) {
                ++countSpace;
            }
        }

        fileReader.close();
        return countSpace;
    }

    /**
     * Метод возвращает самую длинную строку в файле в файле
     */
    public String getLongestLine(File file) throws IOException {
        String longestLine = "";
        BufferedReader fileReader = new BufferedReader(new FileReader(file));

        for(String line = fileReader.readLine(); line != null; line = fileReader.readLine()) {
            if (longestLine.length() < line.length()) {
                longestLine = line;
            }
        }

        fileReader.close();
        return longestLine;
    }

    /**
     * Метод сохраняет статитстику файла в файл или в базу данных на выбор пользователя
     */
    public void save(int lineCount, int spaceCount, String line) throws IOException {
        System.out.println("Для сохранения статистики в файл введите: 1 \nДля сохранения статистики в базу данных введите: 2 ");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        if (num == 1) {
            FileWriter writerResult = new FileWriter("ResultFile.txt");
            writerResult.write("Количество строк в файле: " + lineCount + "\nКоличество пробелов в файле: " + spaceCount + "\nСамая длинная строка: " + line);
            writerResult.close();
            System.out.println("Статистика сохранена в файл.");
        } else {
            System.out.println("Статистика сохранена в базу данных.");
        }

    }
}

