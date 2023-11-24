package ru.sberbank.edu;

import java.time.ZoneId;
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
    public WeatherInfo getWeatherInfo(String city) {
        WeatherInfo newWeatherInfo;
        WeatherInfo weatherInfo = cache.get(city);
        if (weatherInfo != null && (System.currentTimeMillis()
            - weatherInfo.getExpiryTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() < 300000)) {
            return weatherInfo;
        } else {
            newWeatherInfo = weatherProvider.get(city);
            cache.put(city, newWeatherInfo);
            return newWeatherInfo;
        }
    }

    /**
     * Remove weather info from cache.
     **/
    public void removeWeatherInfo(String city) {
        cache.remove(city);
    }
}
