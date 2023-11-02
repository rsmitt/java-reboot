package ru.sberbank.edu;

public interface Reader {

    /**
     * Получает данные и считает статистику по ним
     * @return объект со статистическими данными
     */
    Statistic calcStatistic();

}
