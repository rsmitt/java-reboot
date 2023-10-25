package ru.sberbank.edu;

import java.io.IOException;
import java.util.List;

public interface Statistic {

    int getLineCount() throws IOException;
    int getSpaceCount() throws IOException;
    List<List<String>> getLongestLine() throws IOException;
    void save() throws IOException;

}
