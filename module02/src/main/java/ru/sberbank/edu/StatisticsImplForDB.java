package ru.sberbank.edu;

public class StatisticsImplForDB extends StatisticsImpl{

    public StatisticsImplForDB(String file_path) {
        super(file_path);
    }
    @Override
    public void save() {
        System.out.println("Здесь произошла запись данных в БД");
    }
}
