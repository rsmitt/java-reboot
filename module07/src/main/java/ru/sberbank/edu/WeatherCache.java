package ru.sberbank.edu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WeatherCache {

    private final Map<String, WeatherInfo> cache = new HashMap<>();
    private final WeatherProvider weatherProvider;

    public Map<String, WeatherInfo> getCache() {
        return cache;
    }

    public WeatherProvider getWeatherProvider() {
        return weatherProvider;
    }

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
    public WeatherInfo getWeatherInfo(String city) throws IOException {
        synchronized (WeatherCache.class) {
            if (cache.containsKey(city)) {
                if (cache.get(city).isExpired()) {
                    cache.put(city, weatherProvider.get(city));
                }
            } else {
                if (weatherProvider.get(city) != null) {
                    cache.put(city, weatherProvider.get(city));
                }
            }
            return cache.get(city);
        }
    }

    /**
     * Remove weather info from cache.
     **/
    public void removeWeatherInfo(String city) {
        synchronized (WeatherCache.class) {
            cache.remove(city);
        }
    }
}
