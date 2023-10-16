package ru.sberbank.edu.iofile;


import ru.sberbank.edu.exeption.*;


import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class StatisticImpl implements Statistic {
    @Override
    public int getLineCount(List<String> list) {
        return list.size();
    }

    @Override
    public int getSpaceCount(List<String> list) {
        int countSpace = 0;
        for (String s : list) {
            countSpace += s.length() - s.replaceAll(" ", "").length();
        }
        return countSpace;
    }

    @Override
    public String getLongestLine(List<String> list) {
        String maxLine = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).length() > maxLine.length()) {
                maxLine = list.get(i);
            }
        }
        return maxLine;
    }

    /***
     * a new file is being created "newFile.txt "
     */
    @Override
    public void save(int lineCount, int spaceCount, String line) {
        File file = new File("newFile.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("number of rows: " + lineCount + "\n");
            writer.write("number of spaces: " + spaceCount + "\n");
            writer.write("the longest line: " + line);

        } catch (Exception e) {
            throw new CreateFileException();
        }
    }
}
