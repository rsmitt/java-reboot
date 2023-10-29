package ru.sberbank.edu.impl;

import ru.sberbank.edu.Greeting;

public class GreetingImpl implements Greeting {

    /***
     * Метод получения хобби
     * @return хобби
     */
    @Override
    public String getBestHobby() {
        return "Basketball";
    }
}
