package ru.sberbank.edu;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

        WeatherProvider weatherProvider = new WeatherProvider();
        WeatherCache cache = new WeatherCache(weatherProvider);

        cache.getWeatherInfo("Moscow");
        cache.getWeatherInfo("London");



        System.out.println(cache.getCache());
    }
}
