package ru.sberbank.edu.iofile;


import java.util.List;

// интерфейс можно менять
public interface Statistic {

    int getLineCount(List<String> list);
    int getSpaceCount(List<String> list);
    String getLongestLine(List<String> list);
    void save(int lineCount, int spaceCount, String line);

}
