package ru.sberbank.edu;

import java.io.*;
/**
 * @author  Чернов Алексей
 */

abstract class StatisticCollector implements Statistic {

    /**
     * Переменная с адресом файла-источника для стастистики
     */
    private final File statisticSource;

    StatisticCollector(String file) {
        statisticSource = new File(file);
    }

    /**
     * Метод получения количества линий в файле
     */
    @Override
    public int getLineCount() throws IOException {

        FileReader fileReader = new FileReader(statisticSource);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int lineCounter = 0;

        String line = bufferedReader.readLine();

        while (line != null) {
            lineCounter += 1;
            line = bufferedReader.readLine();
        }

        fileReader.close();
        return lineCounter;
    }

    /**
     * Метод получения количества пробелов в файле
     */
    @Override
    public int getSpaceCount() throws IOException {

        FileReader fileReader = new FileReader(statisticSource);

        int spaceCounter = 0;

        int symbol = fileReader.read();

        while (symbol != -1) {

            if ((char) symbol == ' ') {
                spaceCounter += 1;
            }

            symbol = fileReader.read();
        }

        fileReader.close();
        return spaceCounter;
    }

    /**
     * Метод получения самой длинной строки в файле
     */
    @Override
    public String getLongestLine() throws IOException {

        FileReader fileReader = new FileReader(statisticSource);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String theLongestString = "";
        String line = bufferedReader.readLine();

        while (line != null) {

            if (line.length() > theLongestString.length()) {
                theLongestString = line;
            }

            line = bufferedReader.readLine();

        }

        fileReader.close();
        return theLongestString;
    }

}