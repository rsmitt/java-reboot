package ru.sberbank.edu.caches;

import ru.sberbank.edu.models.WeatherInfo;
import ru.sberbank.edu.provider.WeatherProvider;

import java.util.HashMap;
import java.util.Map;

public class WeatherCache {

    private final Map<String, WeatherInfo> cache = new HashMap<>();
    private final WeatherProvider weatherProvider;

    /**
     * Constructor.
     *
     * @param weatherProvider - weather provider
     */
    public WeatherCache(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    /**
     * Get ACTUAL weather info for current city or null if current city not found.
     * If cache doesn't contain weather info OR contains NOT ACTUAL info then we should download info
     * If you download weather info then you should set expiry time now() plus 5 minutes.
     * If you can't download weather info then remove weather info for current city from cache.
     *
     * @param city - city
     * @return actual weather info
     */
    public synchronized WeatherInfo getWeatherInfo(String city) {
        // should be implemented
        WeatherInfo cacheCity = cache.get(city);
        if((cacheCity != null) && ( java.time.LocalDateTime.now().isAfter(cacheCity.getExpiryTime()))) {
            return cache.get(city);
        }else
            removeWeatherInfo(city);
            cache.put(city,weatherProvider.get(city));
            return weatherProvider.get(city);
    }

    /**
     * Remove weather info from cache.
     **/
    public void removeWeatherInfo(String city) {
        cache.remove(city);
    }
}
