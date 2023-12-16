package ru.sberbank.edu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
public class WeatherProviderComponent implements WeatherProvider {

    @Autowired
    private RestTemplate restTemplate;

   @Autowired
   String apiKey;

    @Override
    public WeatherInfo get(String city) throws JsonProcessingException {
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
