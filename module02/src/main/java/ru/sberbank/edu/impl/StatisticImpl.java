package ru.sberbank.edu.impl;

import ru.sberbank.edu.Statistic;

public class StatisticImpl implements Statistic {

    private final int lineCount;
    private final int spaceCount;
    private final String longestLine;

    public StatisticImpl(int lineCount, int spaceCount, String longestLine) {
        this.lineCount = lineCount;
        this.spaceCount = spaceCount;
        this.longestLine = longestLine;
    }

    @Override
    public int getLineCount() {
        return lineCount;
    }

    @Override
    public int getSpaceCount() {
        return spaceCount;
    }

    @Override
    public String getLongestLine() {
        return longestLine;
    }

}
