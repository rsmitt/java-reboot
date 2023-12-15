package ru.sberbank.edu;

import java.io.Serializable;

/**
 * Вспомогательный класс для парсинга ответа от API
 *
 */
public class Weather implements Serializable {
    public Weather() {
    }

    private String main;
    private String description;

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }
}