package ru.sberbank.edu;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Weather provider
 */
public interface WeatherProvider {

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    public WeatherInfo get(String city) throws JsonProcessingException;
}
