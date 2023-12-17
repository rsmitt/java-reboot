package ru.sberbank.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sberbank.edu.WeatherInfo;
import ru.sberbank.edu.WeatherProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class WeatherCacheComponent implements WeatherCache{

    private final Map<String, WeatherInfo> cache = new HashMap<>();

    @Autowired
    private final WeatherProvider weatherProvider;

    private static final Object monitor = new Object();

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
    public WeatherCacheComponent(WeatherProvider weatherProvider) {
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
    @Override
    public WeatherInfo getWeatherInfo(String city) throws IOException {
        synchronized (monitor) {
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
    @Override
    public void removeWeatherInfo(String city) {
        synchronized (monitor) {
            cache.remove(city);
        }
    }
}
