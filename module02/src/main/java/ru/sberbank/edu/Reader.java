package ru.sberbank.edu;

public interface Reader {

    /**
     * Метод для получения данных и статистики по ним
     *
     * @param source откуда получить данные
     * @return хнарит и отдает статистические данные
     */
    Statistic calcStatistic(Object source);

}
