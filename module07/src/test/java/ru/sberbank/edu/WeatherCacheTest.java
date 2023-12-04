package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WeatherCacheTest {

    @Test
    void getWeatherInfo() {
        List<String> cities = new ArrayList<>(Arrays.asList("Москва", "Норильск", "Сочи"));
        Map<String, WeatherInfo> weatherMap = new HashMap<>();
        WeatherCache cache = new WeatherCache(new WeatherProvider());
        for (String city : cities) {
            Runnable task = () -> {
                WeatherInfo info = cache.getWeatherInfo(city);
                assertEquals(city, info.getCity());
                weatherMap.put(city, info);
            };
            task.run();
        }
        for (String city : cities) {
            Runnable task = () -> {
                WeatherInfo info = cache.getWeatherInfo(city);
                assertEquals(city, info.getCity());
                assertEquals(weatherMap.get(city).getExpiryTime(), info.getExpiryTime());
            };
            task.run();
        }
        WeatherInfo info = cache.getWeatherInfo("asasas");
        assertNull(info);
    }

    @Test
    void removeWeatherInfo() {
        List<String> cities = new ArrayList<>(Arrays.asList("Москва", "Норильск", "Сочи"));
        Map<String, WeatherInfo> weatherMap = new HashMap<>();
        WeatherCache cache = new WeatherCache(new WeatherProvider());
        for (String city : cities) {
            Runnable task = () -> {
                WeatherInfo info = cache.getWeatherInfo(city);
                assertEquals(city, info.getCity());
                weatherMap.put(city, info);
            };
            task.run();
        }

        for (Map.Entry<String, WeatherInfo> entry : weatherMap.entrySet()) {
            cache.removeWeatherInfo(entry.getKey());
        }
        for (String city : cities) {
            Runnable task = () -> {
                WeatherInfo info = cache.getWeatherInfo(city);
                assertEquals(city, info.getCity());
                assertNotEquals(weatherMap.get(city).getExpiryTime(), info.getExpiryTime());
            };
            task.run();
        }
    }
}