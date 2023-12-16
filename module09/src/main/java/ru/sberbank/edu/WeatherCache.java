package ru.sberbank.edu;

/**
 * Weather cache.
 */
public interface WeatherCache {

    /**
     * Get ACTUAL weather info for current city or null if current city not found.
     * If cache doesn't contain weather info OR contains NOT ACTUAL info then we should download info
     * If you download weather info then you should set expiry time now() plus 5 minutes.
     * If you can't download weather info then remove weather info for current city from cache.
     *
     * @param city - city
     * @return actual weather info
     */
    public WeatherInfo getWeatherInfo(String city);

    /**
     * Remove weather info from cache.
     **/
    public void removeWeatherInfo(String city);
}