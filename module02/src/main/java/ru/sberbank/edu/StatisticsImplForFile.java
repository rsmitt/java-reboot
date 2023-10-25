package ru.sberbank.edu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StatisticsImplForFile extends StatisticsImpl {

    public StatisticsImplForFile(String file_path) {
        super(file_path);
    }

    @Override
    public void save() throws IOException {
        Map<String, Object> stats_output = new HashMap<>();
        stats_output.put("Всего строк в исходном файле", super.getLineCount());
        stats_output.put("Всего пробелов в исходном файле", super.getSpaceCount());
        stats_output.put("Самая длинная строка/строки в исходном файле", super.getSpaceCount());
        String string_for_writing = stats_output.toString();
        try (FileWriter fileWriter = new FileWriter("output.txt")) {
            fileWriter.write(string_for_writing);
            fileWriter.flush();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
