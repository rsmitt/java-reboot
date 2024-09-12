package ru.sberbank.edu.provider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.edu.models.WeatherInfo;

import java.time.LocalDateTime;

public class WeatherProvider  {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_KEY = "403cf62be23fdaba3e10e6cd77fc1631";

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    public synchronized WeatherInfo get(String city)  {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            WeatherInfo weatherInfo = new WeatherInfo();
            weatherInfo.setCity(String.valueOf(root.path("name")).replaceAll("\"", ""));
            weatherInfo.setShortDescription(String.valueOf(root.path("weather").get(0).path("main")).replaceAll("\"", ""));
            weatherInfo.setDescription(String.valueOf(root.path("weather").get(0).path("description")).replaceAll("\"", ""));
            weatherInfo.setTemperature(Double.parseDouble(String.valueOf(root.path("main").path("temp"))));
            weatherInfo.setFeelsLikeTemperature(Double.parseDouble(String.valueOf(root.path("main").path("feels_like"))));
            weatherInfo.setWindSpeed(Double.parseDouble(String.valueOf(root.path("wind").path("speed"))));
            weatherInfo.setPressure(Double.parseDouble(String.valueOf(root.path("main").path("pressure"))));
            weatherInfo.setExpiryTime(LocalDateTime.now().plusMinutes(5));
            return weatherInfo;
        } catch (Exception exception ) {
            return null;
        }
    }
}
