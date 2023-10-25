package ru.sberbank.edu;

import java.io.File;
import java.io.IOException;

// интерфейс можно менять
public interface Statistic {

    int getLineCount(File file);
    int getSpaceCount(File file);
    String getLongestLine(File file);


    void save(int lineCount, int spaceCount, String line) throws IOException;

}
