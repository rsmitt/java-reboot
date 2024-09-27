package ru.sberbank.edu;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс сохранения информации в файл
 * @author  Чернов Алексей
 */
public class StatisticCollectorToFile extends StatisticCollector {

    /**
     * Имя файла для записи результата
     */
    String resultFileName = "result.txt";

    /**
     * Фабричный метод с передачей костомного имени файла с результатом
     */
    public StatisticCollectorToFile(String file, String result) {
        super(file);
        resultFileName = result;
    }
    /**
     * Реализация фабричного метода идентичная классу-родителю
     */
    public StatisticCollectorToFile(String file) {
        super(file);
    }

    /**
     * Метод записи полученных данных в файл
     */
    @Override
    public void save(int lineCount, int spaceCount, String line) throws IOException {

        FileWriter fileWriter = new FileWriter(resultFileName);
        fileWriter.write("Counted lines: " + lineCount + "\n");
        fileWriter.write("Counted spaces: " + spaceCount + "\n");
        fileWriter.write("The longest line: \"" + line + "\"");

        fileWriter.close();

    }

}