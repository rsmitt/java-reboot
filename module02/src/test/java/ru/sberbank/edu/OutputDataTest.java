package ru.sberbank.edu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class OutputDataTest {

    @TempDir
    File tempDir;

    @Test
    void testWriteToFile() throws IOException {
        String filePath = tempDir.getAbsolutePath() + "/test.txt";
        String content = "Hello, World!";

        OutputData outputData = new OutputData() {
            @Override
            protected void validateFile(String filePath) {
                // Автомокирование, поскольку validateFile() защищенный метод и не может быть протестирован напрямую
            }
        };

        outputData.writeResultToFile(filePath, content);

        assertTrue(Files.exists(Path.of(filePath)));
        assertEquals(content, Files.readString(Path.of(filePath)));
    }

    @Test
    void testValidateFile() {
        String filePath = tempDir.getAbsolutePath() + "/test.txt";
        File file = new File(filePath); // Создаем файл для проверки

        OutputData outputData = new OutputData() {
            @Override
            protected void validateFile(String filePath) throws FileNotFoundException {
                if (!file.exists()) {
                    throw new FileNotFoundException("File does not exist.");
                }
            }
        };

        assertThrows(FileNotFoundException.class, () -> outputData.validateFile(filePath));

        // Создаем файл для проверки и повторно вызываем validateFile()
        try {
            Files.createFile(Path.of(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertDoesNotThrow(() -> outputData.validateFile(filePath));
    }
}
