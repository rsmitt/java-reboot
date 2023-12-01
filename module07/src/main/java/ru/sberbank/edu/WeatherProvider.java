package ru.sberbank.edu;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class WeatherProvider {

    private RestTemplate restTemplate = new RestTemplate();

/**
    Download ACTUAL weather info from internet.
    You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
    You should use Spring Rest Template for calling requests

    @param city - city
    @return weather info or null
*/

    public WeatherInfo get(String city) {

        String API_KEY = "6f33a8ebf2e021a69aa23863586644a6";
        String URL = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY;

        try {
            WeatherInfo weatherInfo = restTemplate.getForObject(URL, WeatherInfo.class);
            return weatherInfo;
        }
        catch (HttpClientErrorException ex) {
            return null;
        }

    }
}