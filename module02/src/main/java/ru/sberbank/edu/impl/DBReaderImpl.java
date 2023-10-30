package ru.sberbank.edu.impl;

import ru.sberbank.edu.Reader;
import ru.sberbank.edu.Statistic;


public class DBReaderImpl implements Reader {
    @Override
    public Statistic calcStatistic(Object source) {
        return new StatisticImpl(1, 2, "Longest line");
    }
}
