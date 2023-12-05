package ru.sberbank.edu;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Класс сохранения данных в файл
 */
public class StorageFileImpl implements Storage {

    /**
     * @param statistic - класс определения статистики, для сохранения в файл
     */
    @Override
    public void save(String storageName, Statistic statistic) {
        StringBuilder sb = new StringBuilder("Статистика файла:");
        sb.append(System.lineSeparator());
        sb.append("Количество строк - ");
        sb.append(statistic.getLineCount());
        sb.append(System.lineSeparator());
        sb.append("Количество пробелов - ");
        sb.append(statistic.getSpaceCount());
        sb.append(System.lineSeparator());
        sb.append("Самая длинная строка - ");
        sb.append(statistic.getLongestLine());
        try(FileOutputStream outputStream = new FileOutputStream(storageName)) {
            outputStream.write(sb.toString().getBytes());
            outputStream.close();
        } catch (IOException e) {
            throw new AppException(e);
        }
        System.out.println("Статистика сохранена в файл");
    }
}
