package ru.sberbank.edu;

public interface WeatherProvider {
    WeatherInfo get(String city);
}
