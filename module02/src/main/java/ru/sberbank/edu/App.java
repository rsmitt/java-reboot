package ru.sberbank.edu;

/**
 * <h3>Программа подсчета статистики по строкам в файле</h3>
 * <ul>Принимает аргументы:
 * <li>режим записи (F - файл, D - база данных
 * <li>имя файла для подсчета статистики
 * <li>имя файла для записи результата (по умолчанию result.txt)
 * </ul>
 */
public class App {
    public static void main(String[] args) {
        if (args.length == 0)
            throw new AppException("Параметры командной строки отсутствуют");
        String resultFileName = args.length > 1 ? args[2] : "result.txt";

        StorageFactory.getStorage(args[0]).save(resultFileName, new StatisticImpl(args[1]));

    }
}
