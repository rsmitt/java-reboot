package ru.sberbank.edu;

/**
 * Интерфейс для сохранения статистики файла
 */
public interface Storage {
    /**
     * @param storageName имя хранилища (файла или БД )
     * @param statistic   класс определения статистики
     */
    void save(String storageName, Statistic statistic);
}
