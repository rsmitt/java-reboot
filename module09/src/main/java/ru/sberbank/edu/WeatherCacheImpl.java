package ru.sberbank.edu;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Weather cache.
 */
public class WeatherCacheImpl implements WeatherCache{

    private final Map<String, WeatherInfo> cache = new HashMap<>();

    private OpenWeatherProvider openWeatherProvider;

    @Autowired
    public void setWeatherProvider(OpenWeatherProvider openWeatherProvider) {
        this.openWeatherProvider = openWeatherProvider;
    }

    /**
     * Default constructor.
     */
    public WeatherCacheImpl() {
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
        boolean isExpired = false;
        WeatherInfo info ;

        synchronized (cache){
            info = cache.get(city);
            if (info != null) {
                isExpired = info.expiryTime().isBefore(LocalDateTime.now());
            }

            if (info == null || isExpired){
                removeWeatherInfo(city);
                try {
                    info = openWeatherProvider.get(city);
                    cache.put(city, info);
                } catch (Exception ignored){}
            }
        }

        return info;
    }

    /**
     * Remove weather info from cache.
     **/
    public void removeWeatherInfo(String city) {
        synchronized (cache){
            cache.remove(city);
        }
    }
}