package ru.sberbank.edu;

public interface WeatherCacheInterface {
    WeatherInfo getWeatherInfo(String city);
    void removeWeatherInfo(String city);
}