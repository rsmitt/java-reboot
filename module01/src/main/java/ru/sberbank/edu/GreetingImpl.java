package ru.sberbank.edu;

/**
 * Класс, реализующий интерфейс Greeting
 * @author Нофал Кайс
 * @version 1.0
 */

public class GreetingImpl implements Greeting{

    /**
     * Переопределение метода getBestHobby из интерфейса Greeting
     * @return возвращает строку
     */
    @Override
    public String getBestHobby() {
        return "Family, sport (martial art and crossfit), history, book reading";
    }
}
