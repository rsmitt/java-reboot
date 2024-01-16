package ru.sberbank.edu;

import java.io.*;

public class FileResource extends OutputData implements DataResource {
    private final BufferedReader reader;

    private final String filePath;

    public FileResource(String filePath) throws IOException {
        reader = new BufferedReader(new FileReader(filePath));
        this.filePath = filePath;
    }

    @Override
    public String readData() throws IOException {
        validateFile(filePath);
        return reader.readLine();
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}