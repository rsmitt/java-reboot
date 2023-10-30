package ru.sberbank.edu.impl;

import ru.sberbank.edu.Statistic;
import ru.sberbank.edu.Writer;

import java.io.*;

public class FileWriterImpl implements Writer {


    @Override
    public void save(Statistic statistic, Object source) {
        BufferedWriter writer;

        try {
            writer = openFile((String) source);
            writer.write("File statistic\n" +
                    "Line count: " + statistic.getLineCount() + "\n" +
                    "Space count: " + statistic.getSpaceCount() + "\n" +
                    "The longest line: " + statistic.getLongestLine());
            closeFile(writer);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while work with file " + source.toString() + "\n", e);
        }
    }

    private BufferedWriter openFile(String path) throws IOException {
        return new BufferedWriter(new FileWriter(path));
    }

    private void closeFile(BufferedWriter writer) throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}
