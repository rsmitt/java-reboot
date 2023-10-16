package ru.sberbank.edu;

public class GreetingImpl implements Greeting{

    private String hobby;
    private Student student;

    public GreetingImpl(String hobby, Student student) {
        this.hobby = hobby;
        this.student = student;
    }

    /***
     * метод возвращает хобби студента
     * @return
     */
    @Override
    public String getBestHobby() {

        return hobby;
    }

    /***
     * возвращает объект с характеристиками студента
     * @return
     */
    public Student getStudent() {
        return student;
    }

    /***
     * Возвращает приветствие
     * @return
     */
    @Override
    public String toString() {
        return "Привет! Я" + " " + student + " " +
                "и мое хобби: " + hobby + ".";
    }
}
