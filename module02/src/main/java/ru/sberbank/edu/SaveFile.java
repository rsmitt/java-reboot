package ru.sberbank.edu;

import java.io.FileWriter;
import java.io.IOException;

public class SaveFile implements Save{
    @Override
    public void save(int lineCount, int spaceCount, String line) {
        try {
            FileWriter writerResult = new FileWriter("ResultFile.txt");
            writerResult.write("Количество строк в файле: " + lineCount + "\nКоличество пробелов в файле: " + spaceCount + "\nСамая длинная строка: " + line);
            writerResult.close();
            System.out.println("Статистика сохранена в файл.");
        }
        catch (IOException e) {
            System.out.println("Что-то пошло не так");
        }

    }
}
