package ru.sberbank.edu;

import java.io.IOException;


public interface Statistic {

    int getLineCount() throws IOException;


    int getSpaceCount() throws IOException;

    void save(int lineCount, int spaceCount, String line) throws IOException;

    String getLongestLine() throws IOException;
}