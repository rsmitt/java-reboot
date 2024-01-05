package ru.sberbank.edu;

public class SaveBD implements Save {
    @Override
    public void save(int lineCount, int spaceCount, String line) {
        System.out.println("Статистика сохранена в базу данных.");
    }
}
