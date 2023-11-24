package ru.sberbank.edu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;


public class WeatherProvider {

    private RestTemplate restTemplate = null;

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    public WeatherInfo get(String city) {
        restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=023750cbc3864418bd55bcbbbcc779b8";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        String ans = response.getBody();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        WeaterObj wheaterObj = gson.fromJson(response.getBody(), WeaterObj.class);
        if (response.getStatusCode().value() == 200) {
            WeatherInfo weatherInfo = new WeatherInfo();
            weatherInfo.setCity(city);
            weatherInfo.setTemperature(wheaterObj.getMain().get("temp"));
            weatherInfo.setFeelsLikeTemperature(wheaterObj.getMain().get("feels_like"));
            weatherInfo.setPressure(wheaterObj.getMain().get("pressure"));
            weatherInfo.setWindSpeed(wheaterObj.getWind().get("speed"));
            MainObj shortDescription = (MainObj) wheaterObj.getWeather()[0];
            weatherInfo.setShortDescription(shortDescription.getMain());
            weatherInfo.setDescription(shortDescription.getDescription());
            LocalDateTime dateTime = LocalDateTime.now();
            weatherInfo.setExpiryTime(dateTime);
            return weatherInfo;
        } else {
            return null;
        }
    }
}
