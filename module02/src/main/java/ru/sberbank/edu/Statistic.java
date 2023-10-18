package ru.sberbank.edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// интерфейс можно менять
public interface Statistic {

    int getLineCount(File file) throws IOException;
    int getSpaceCount(File file) throws IOException;
    String getLongestLine(File file) throws IOException;


    void save(int lineCount, int spaceCount, String line) throws IOException;

}
