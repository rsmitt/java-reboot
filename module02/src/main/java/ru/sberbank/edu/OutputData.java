package ru.sberbank.edu;

import java.io.*;
import java.sql.SQLException;


public  abstract class OutputData implements DataResource{
    @Override
    public String readData() throws IOException, SQLException {
        return null;
    }

    @Override
    public void close() throws IOException, SQLException {

    }
    public void writeResultToFile(String filePath, String content) throws IOException {
        validateFile(filePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(content);
        writer.close();
    }
    protected void validateFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist.");
        }

        if (!file.isFile()) {
            throw new IllegalArgumentException("Specified path does not lead to a file.");
        }
    }
}
