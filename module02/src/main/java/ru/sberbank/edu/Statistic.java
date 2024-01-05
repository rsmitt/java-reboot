package ru.sberbank.edu;

import java.io.IOException;

// интерфейс можно менять
public interface Statistic {
    int getSpaceCount();
    int getLinesCount();
    String getLongestLine();
    void save(int lineCount, int spaceCount, String line) throws IOException;
    void setLineCount();

    void setSpaceCount();

    void setLongestLine(String longestLine);
}