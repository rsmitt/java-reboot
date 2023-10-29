package ru.sberbank.edu;

import java.io.IOException;
import java.sql.SQLException;

public class DataResourceProcessor {
    private final DataResource resource;

    public DataResourceProcessor(DataResource resource) {
        this.resource = resource;
    }

    public String processResource() throws IOException, SQLException {
        int lineCount = 0;
        String longestLine = "";
        int totalSpaces = 0;

        String line;
        while ((line = resource.readData()) != null) {
            lineCount++;
            if (line.length() > longestLine.length()) {
                longestLine = line;
            }
            totalSpaces += countSpaces(line);
        }

        resource.close();
        return String.format("Line count: %s, longest line: %s, total spaces: %s",lineCount,longestLine,totalSpaces);

    }

    int countSpaces(String line) {
        int totalSpaces = 0;
        for (char c : line.toCharArray()) {
            if (c == ' ') {
                totalSpaces++;
            }
        }
        return totalSpaces;
    }
}
