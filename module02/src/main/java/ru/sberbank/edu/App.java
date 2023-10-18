package ru.sberbank.edu;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        String inputFilePath = "module02/src/input.txt";
        String outputFilePath = "module02/src/output.txt";

        try {
            // Создаем экземпляр FileResource с указанием пути к файлу входных данных
            DataResource fileResource = new FileResource(inputFilePath);

            // Создаем экземпляр DataResourceProcessor с передачей ему FileResource
            DataResourceProcessor processor = new DataResourceProcessor(fileResource);

            // Получаем статистику из ресурса
            String statistics = processor.processResource();

            // Записываем статистику в файл output.txt
            fileResource.writeResultToFile(outputFilePath, statistics);

            // Закрываем ресурс
            fileResource.close();

            System.out.println("Статистика записана в файл " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }

}
