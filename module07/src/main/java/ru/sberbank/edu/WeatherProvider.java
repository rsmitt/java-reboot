package ru.sberbank.edu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class WeatherProvider {

    //    private RestTemplate restTemplate = null;

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    public WeatherInfo get(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=023750cbc3864418bd55bcbbbcc779b8";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        String ans = response.getBody();
        System.out.println(ans);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Wheater wheater = gson.fromJson(response.getBody().toString(), Wheater.class);
        System.out.println(wheater.getWeather().toString());
        System.out.println(wheater);


        if (response.getStatusCode().value() == 200) {
            WeatherInfo weatherInfo = new WeatherInfo();



            return weatherInfo;
        } else {
            return null;
        }
    }
}
