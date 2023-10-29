package ru.sberbank.edu;

/***
 * Чтение файла переданного на вход
 * Запись статистики переданного файла в файл со статистикой
 */
public class App02
{
    public static void main( String[] args )
    {
        if ( args.length != 1 ) {
            return;
        }
        String filename = args[0];

        FileManager fileManager = new FileManager(filename);

        fileManager.save(fileManager.getLineCount(),fileManager.getSpaceCount(),fileManager.getLongestLine());

    }
}
