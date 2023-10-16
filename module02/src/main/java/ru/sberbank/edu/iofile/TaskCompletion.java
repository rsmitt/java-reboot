package ru.sberbank.edu.iofile;

import java.io.File;
import java.util.Scanner;

public  class TaskCompletion {
    public TaskCompletion() {
        File file = new File("information.txt");
        ReaderFile readerFile = new ReaderFile();
        readerFile.readFile(file);
        StatisticImpl statistic = new StatisticImpl();
        //todo может предложить пользователю способ сохранения?
        System.out.println("Сохранить в БД или создать новый файл и записать в него?");
        System.out.println("Введите базу данных для сохранения, иначе введите \"0\"");
        Scanner in = new Scanner(System.in);
        String value = in.nextLine();
        if (value.equals("0")) {
            statistic.save(statistic.getLineCount(readerFile.list), statistic.getSpaceCount(readerFile.list),
                    statistic.getLongestLine(readerFile.list));
        }  //todo сохраняю в базу данных полученную от пользователя...

    }
}