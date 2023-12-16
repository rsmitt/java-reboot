package ru.sberbank.edu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

/**
 * Weather provider
 */
@Component
public class WeatherProviderImpl implements WeatherProvider {
    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&lang=ru&units=metric&appid=%s";
    @Value("${appKey}")
    private String appKey;

    @Autowired
    private RestTemplate restTemplate;

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
        try {
            return responseToWeatherInfoMapper(restTemplate.getForEntity(URI.create(String.format(WEATHER_URL, city, appKey)), String.class));
        } catch (HttpClientErrorException exception) {
            return null;
        }
    }

    /**
     * Разбор response json и меппинг в WeatherInfo
     *
     * @param response
     * @return WeatherInfo
     */
    private WeatherInfo responseToWeatherInfoMapper(ResponseEntity<String> response) {
        WeatherInfo weatherInfo = new WeatherInfo();
        ObjectMapper mapper = new ObjectMapper();
        try {
            HashMap<String, Object> weatherMap = mapper.readValue(response.getBody(), new TypeReference<HashMap>() {
            });
            weatherInfo.setCity(weatherMap.get("name").toString());
            HashMap<String, String> weather = ((List<HashMap<String, String>>) weatherMap.get("weather")).get(0);
            weatherInfo.setShortDescription(weather.get("main"));
            weatherInfo.setDescription(weather.get("description"));
            HashMap<String, Object> main = (HashMap<String, Object>) weatherMap.get("main");
            weatherInfo.setTemperature((Double) main.get("temp"));
            weatherInfo.setFeelsLikeTemperature((Double) main.get("feels_like"));
            weatherInfo.setPressure((Integer) main.get("pressure"));
            HashMap<String, Object> wind = (HashMap<String, Object>) weatherMap.get("wind");
            weatherInfo.setWindSpeed((double) wind.get("speed"));
            weatherInfo.setExpiryTime();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return weatherInfo;
    }
}
