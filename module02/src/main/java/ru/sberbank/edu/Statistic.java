package ru.sberbank.edu;

public interface Statistic {

    /**
     * Отдает сумму строк в тексте
     * @return сумма строк
     */
    int getLineCount();

    /**
     * Отдает сумму пробелов в тексте
     * @return сумма пробелов
     */
    int getSpaceCount();

    /**
     * Отдает самую длинную строку из текста
     * @return строка
     */
    String getLongestLine();

}
