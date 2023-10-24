package ru.sberbank.edu;

/**
 * Класс сохранения данных в базу данных
 */
public class StorageDbImpl implements Storage {

    /**
     * @param storageName - имя базы данных (схемы, таблицы ...)
     * @param statistic   - класс определения статистики, для сохранения в файл
     */
    @Override
    public void save(String storageName, Statistic statistic) {
        System.out.println("Статистика сохранена в БД");
        System.out.println(statistic.toString());
    }

}
