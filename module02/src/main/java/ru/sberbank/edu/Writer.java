package ru.sberbank.edu;

public interface Writer {

    /**
     * Метод для записи статистических данных
     *
     * @param statistic хнарит и отдает статистические данные
     * @param source    куда записывать данные
     */
    void save(Statistic statistic, Object source);
}