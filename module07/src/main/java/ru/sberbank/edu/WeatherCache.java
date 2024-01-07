package ru.sberbank.edu;

import java.time.LocalDateTime;
import java.util.HashMap;


public class WeatherCache {

    private static final int EXPIRY_DURATION_MINUTES = 5;
    private final WeatherProvider weatherProvider;
    private final HashMap<String, WeatherInfo> cache = new HashMap<>();

    public WeatherCache(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    public synchronized WeatherInfo getWeatherInfo(String city) {
        WeatherInfo info = cache.get(city);
        if (info == null || info.getExpiryTime().isBefore(LocalDateTime.now())) {
            info = weatherProvider.get(city);
            info.setExpiryTime(LocalDateTime.now().plusMinutes(EXPIRY_DURATION_MINUTES));
            cache.put(city, info);
        }
        return info;
    }

    public synchronized void removeWeatherInfo(String city) {
        cache.remove(city);
    }
}
