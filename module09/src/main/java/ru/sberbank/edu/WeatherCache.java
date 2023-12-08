package ru.sberbank.edu;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

/**
 * Weather cache.
 */
public class WeatherCache {

    private Object monitor = new Object();
    private final Map<String, WeatherInfo> cache = new HashMap<>();
    private WeatherProvider weatherProvider;

    /**
     * Constructor.
     *
     * @param weatherProvider - weather provider
     */
    public WeatherCache() {
    }

    @Autowired
    public void setWeatherProvider(WeatherProvider weatherProvider) {
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
        synchronized (monitor) {
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
    }

    /**
     * Remove weather info from cache.
     **/
    public void removeWeatherInfo(String city) {
        synchronized (monitor) {
            cache.remove(city);
        }
    }
}