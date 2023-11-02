package ru.sberbank.edu.impl;

import ru.sberbank.edu.Reader;
import ru.sberbank.edu.Statistic;


public class DBReaderImpl implements Reader {

    private final String source;
    private final String login;
    private final String password;

    public DBReaderImpl(String source, String login, String password) {
        this.source = source;
        this.login = login;
        this.password = password;
    }

    @Override
    public Statistic calcStatistic() {
        return new StatisticImpl(1, 2, "Longest line");
    }
}
