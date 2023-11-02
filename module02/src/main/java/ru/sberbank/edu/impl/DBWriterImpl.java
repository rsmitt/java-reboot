package ru.sberbank.edu.impl;

import ru.sberbank.edu.Statistic;
import ru.sberbank.edu.Writer;

public class DBWriterImpl implements Writer {

    private final String source;
    private final String login;
    private final String password;

    public DBWriterImpl(String source, String login, String password) {
        this.source = source;
        this.login = login;
        this.password = password;
    }

    @Override
    public void write(Statistic statistic) {
        System.out.println("File statistic\n" +
                "Line count: " + statistic.getLineCount() + "\n" +
                "Space count: " + statistic.getSpaceCount() + "\n" +
                "The longest line: " + statistic.getLongestLine());
    }
}
