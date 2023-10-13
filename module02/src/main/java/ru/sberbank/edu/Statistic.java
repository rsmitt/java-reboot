package ru.sberbank.edu;

// интерфейс можно менять
public interface Statistic {

    int getLineCount();
    int getSpaceCount();
    String getLongestLine();
    void save(int lineCount, int spaceCount, String line);

}
