package ru.sberbank.edu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        FileWriter writer = new FileWriter("file.txt");
        writer.write("""
                Вы помните,
                Вы всё, конечно, помните,
                Как я стоял,
                Приблизившись к стене,
                Взволнованно ходили вы по комнате
                И что-то резкое
                В лицо бросали мне.""");
        writer.close();
        File file = new File("file.txt");
        StatisticFile statisticFile = new StatisticFile();
        statisticFile.save(statisticFile.getLineCount(file),
                statisticFile.getSpaceCount(file), statisticFile.getLongestLine(file));
    }
}

// Сделать интерфейс save от него наследовать классы SaveBD SaveFail реализующие метод Save.
//Класс StatisticFile наследуется от интрфейсов Statistic и от интеофейса Save
// В методе Save класса StatisticFile на вход передается объект интерфейс save
//В классе App вызывается метод Save класса StatisticFile ему на вход передаются обекты интерфейса save
// реализуемые классами SaveBD SaveFail
