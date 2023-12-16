package ru.sberbank.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Weather cache.
 */
@Component("weatherCache")
public class WeatherCacheImpl implements WeatherCache {

    private final Map<String, WeatherInfo> cache = new HashMap<>();

    @Autowired
    private WeatherProvider weatherProvider;

    /**
     * Get ACTUAL weather info for current city or null if current city not found.
     * If cache doesn't contain weather info OR contains NOT ACTUAL info then we should download info
     * If you download weather info then you should set expiry time now() plus 5 minutes.
     * If you can't download weather info then remove weather info for current city from cache.
     *
     * @param city - city
     * @return actual weather info
     */
    @Override
    public WeatherInfo getWeatherInfo(String city) {
        WeatherInfo result = cache.get(city);
        if (result == null || result.getExpiryTime().isBefore(LocalDateTime.now())) {
            result = weatherProvider.get(city);
            cache.put(city, result);
        }
        return result;
    }

    /**
     * Remove weather info from cache.
     **/
    @Override
    public void removeWeatherInfo(String city) {
        cache.remove(city);
    }
}