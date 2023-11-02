package ru.sberbank.edu;

public interface Statistic {

    /**
     * Возвращает значение суммы строк
     * @return сумма строк
     */
    int getLineCount();

    /**
     * Возвращает значение суммы пробелов
     * @return сумма пробелов
     */
    int getSpaceCount();

    /**
     * Возвращает самую длинную строку
     * @return самая длинная строка
     */
    String getLongestLine();

}
