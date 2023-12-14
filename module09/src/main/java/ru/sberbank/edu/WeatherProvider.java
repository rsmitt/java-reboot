package ru.sberbank.edu;

/**
 * Weather provider
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class WeatherProvider {
    private final RestTemplate restTemplate;

    @Value("${app.apiurl}")
    private String apiUrl;

    @Value("${app.apikey}")
    private String apiKey;

    @Autowired
    public WeatherProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public WeatherInfo get(String city) {
        return restTemplate.getForObject(apiUrl, WeatherInfo.class, city, apiKey);
    }
}
