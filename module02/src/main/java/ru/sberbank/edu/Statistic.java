package ru.sberbank.edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Statistic {

    int getLineCount(File file) throws IOException;
    int getSpaceCount(File file) throws IOException;
    String getLongestLine(File file) throws IOException;


    void save(Save save, int lineCount, int spaceCount, String line) throws IOException;

}
