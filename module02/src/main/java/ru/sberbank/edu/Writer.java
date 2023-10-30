package ru.sberbank.edu;

public interface Writer {

    /**
     * Записывает статистические данные
     * @param statistic объект со статистикой
     */
    void write(Statistic statistic);
}