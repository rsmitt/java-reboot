package ru.sberbank.edu;

public interface WeatherCache {

    WeatherInfo getWeatherInfo(String city);

    void removeWeatherInfo(String city);
}
