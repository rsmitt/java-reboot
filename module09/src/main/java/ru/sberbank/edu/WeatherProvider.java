package ru.sberbank.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherProvider implements WeatherProviderInterface{

    @Value("${appkey}")
    private String appKey;

    private RestTemplate restTemplate;

    @Autowired
    public WeatherProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    @Override
    public WeatherInfo get(String city) {

        String URL = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + this.appKey;

        try {
            WeatherInfo weatherInfo = restTemplate.getForObject(URL, WeatherInfo.class);
            return weatherInfo;
        }
        catch (HttpClientErrorException ex) {
            return null;
        }
    }
}