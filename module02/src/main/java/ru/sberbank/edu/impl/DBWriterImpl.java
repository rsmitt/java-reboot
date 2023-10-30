package ru.sberbank.edu.impl;

import ru.sberbank.edu.Statistic;
import ru.sberbank.edu.Writer;

public class DBWriterImpl implements Writer {
    @Override
    public void save(Statistic statistic, Object source) {
        System.out.println("File statistic\n" +
                "Line count: " + statistic.getLineCount() + "\n" +
                "Space count: " + statistic.getSpaceCount() + "\n" +
                "The longest line: " + statistic.getLongestLine());
    }
}
