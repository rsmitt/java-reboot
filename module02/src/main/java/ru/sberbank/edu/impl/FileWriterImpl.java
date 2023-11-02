package ru.sberbank.edu.impl;

import ru.sberbank.edu.Statistic;
import ru.sberbank.edu.Writer;

import java.io.*;

public class FileWriterImpl implements Writer {

    private final String filePath;

    public FileWriterImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(Statistic statistic) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("File statistic\n" +
                    "Line count: " + statistic.getLineCount() + "\n" +
                    "Space count: " + statistic.getSpaceCount() + "\n" +
                    "The longest line: " + statistic.getLongestLine());
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while work with file " + filePath + "\n", e);
        }
    }

}
