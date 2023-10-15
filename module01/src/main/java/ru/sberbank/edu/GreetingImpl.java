package ru.sberbank.edu;

public class GreetingImpl implements Greeting{
    /**
     * Метод выводит информацию о разработчике данного класса
     */
    @Override
    public String getBestHobby() {
        String name = "Дмитрий";
        String lastName = "Карпов";
        String post = "Инженер по нагрузочному тестированию";
        String hobby = "Увлекаюсь програмированием на Java, люблю активные виды спорта \n" +
                "(волейбол, настольный тенис, катание на велосипеде) и путешествия";
        return "Имя: " + name + ",\n" +
                "Фамилия: " + lastName + ",\n" +
                "Должность: " + post + ",\n" +
                "Хобби: " + hobby;
    }
}
