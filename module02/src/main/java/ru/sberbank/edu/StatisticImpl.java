package ru.sberbank.edu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Класс определения статистики
 */
public class StatisticImpl implements Statistic {
    private int lineCount;
    private int spaceCount;
    private String longestLine;

    /**
     * Конструктор
     *
     * @param fileName - файл для анализа
     */
    public StatisticImpl(String fileName) {
        try {
            calcStatistic(new String(Files.readAllBytes(Paths.get(fileName))));
        } catch (IOException e) {
            throw new AppException(e);
        }
    }

    /**
     * Определение статистики файла
     *
     * @param content - содержимое файла
     */
    private void calcStatistic(String content) {
        List<String> stringLines = Arrays.asList(content.split(System.lineSeparator()));
        lineCount = stringLines.size();
        spaceCount = 0;
        longestLine = "";
        stringLines.forEach(s -> {
            spaceCount += s.chars().filter(c -> c == ' ').count();
            longestLine = longestLine.length() < s.length() ? s : longestLine;
        });
    }

    /**
     * Геттер
     *
     * @return количество строк
     */
    @Override
    public int getLineCount() {
        return lineCount;
    }

    /**
     * Геттер
     *
     * @return количество пробелов
     */
    @Override
    public int getSpaceCount() {
        return spaceCount;
    }

    /**
     * Геттер
     *
     * @return самая большая строка
     */
    @Override
    public String getLongestLine() {
        return longestLine;
    }

    /**
     * Переопределение стандартного метода
     *
     * @return строка с данными класса
     */
    @Override
    public String toString() {
        return "StatisticImpl{" + "lineCount=" + lineCount +
                ", spaceCount=" + spaceCount +
                ", longestLine='" + longestLine + '\'' +
                '}';
    }
}
