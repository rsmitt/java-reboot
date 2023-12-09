package ru.sberbank.edu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.edu.model.MainObj;
import ru.sberbank.edu.model.WeaterObj;

import java.time.LocalDateTime;

/**
 * Weather provider
 */
public class WeatherProvider {

    private RestTemplate restTemplate;
    private String apiKey;

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public WeatherInfo get(String city) {
        String fooResourceUrl
                = "http://api.openweathermap.org/data/2.5/weather?q=" + city + apiKey;
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
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
            weatherInfo.setExpiryTime(LocalDateTime.now());
            return weatherInfo;
        } else {
            return null;
        }
    }
}
