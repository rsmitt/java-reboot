package ru.sberbank.edu;

import java.io.IOException;
import java.sql.SQLException;

public interface DataResource {
    String readData() throws IOException, SQLException;
    void close() throws IOException, SQLException;
    void writeResultToFile(String outputFilePath, String statistics) throws IOException;

}
