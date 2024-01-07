package ru.sberbank.edu;


import org.springframework.web.client.RestTemplate;

public class WeatherProvider {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={API_KEY}";
    private static final String API_KEY = "your_api_key_here";

    public WeatherInfo get(String city) {
        return restTemplate.getForObject(API_URL, WeatherInfo.class, city, API_KEY);
    }
}
