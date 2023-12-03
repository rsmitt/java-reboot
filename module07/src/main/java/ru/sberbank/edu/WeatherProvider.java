package ru.sberbank.edu;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;

public class WeatherProvider {
    private RestTemplate restTemplate;
    private String apiKey = "357f2bae55c721ffd8378d1fcc472b56";

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public WeatherProvider(RestTemplate restTemplate) {
        setRestTemplate(restTemplate);
    }

    public WeatherProvider() {
        this(new RestTemplate());
    }

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    public WeatherInfo get(String city) throws IOException {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
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
        } catch (HttpClientErrorException.NotFound exception) {
            return null;
        }
    }
}
