package ru.sberbank.edu;

import java.time.LocalDateTime;
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
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        WeatherInfo wi = null;
        if (!cache.containsKey(city)) {
            WeatherProvider weatherProvider = new WeatherProvider();
            wi = weatherProvider.get(city);
            if (wi == null) {
                System.out.println("INCORRECT REQUEST!");
            }
            else {
                cache.put(city, wi);
                System.out.println("PUT NEW NOTE TO CACHE");
            }
        } else {
            for (Map.Entry<String, WeatherInfo> item : cache.entrySet()) {
                if (item.getKey().equalsIgnoreCase(city)) {
                    wi = item.getValue();
                    if (localDateTime.compareTo(wi.getExpiryTime())>0) {
                        WeatherProvider weatherProvider = new WeatherProvider();
                        removeWeatherInfo(city);
                        System.out.println("UPDATE NOTE IN CACHE");
                        wi = weatherProvider.get(city);
                        cache.put(city, wi);
                    }
                    else {
                        System.out.println("GET NOTE FROM CACHE");
                    }
                }
            }
        }
        System.out.println(wi);
        return wi;
    }
    /**
     * Remove weather info from cache.
     **/
    public synchronized void removeWeatherInfo(String city) {
        cache.remove(city);
    }
}