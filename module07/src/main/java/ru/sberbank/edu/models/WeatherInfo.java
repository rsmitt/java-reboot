package ru.sberbank.edu.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class WeatherInfo {
    private static final Long TIME_TO_EXPIRE = 1L;

    @JsonProperty("name")
    private String city;

    /**
     * Short weather description
     * Like 'sunny', 'clouds', 'raining', etc
     */
    private String shortDescription;
    /**
     * Weather description.
     * Like 'broken clouds', 'heavy raining', etc
     */
    private String description;

    /**
     * Temperature.
     */
    private double temperature;

    /**
     * Temperature that fells like.
     */
    private double feelsLikeTemperature;

    /**
     * Wind speed.
     */
    private Double windSpeed;

    /**
     * Pressure.
     */
    private double pressure;

    /**
     * Expiry time of weather info.
     * If current time is above expiry time then current weather info is not actual!
     */
    private LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(TIME_TO_EXPIRE);

    /**
     * Parse response and set values 'shortDescription' and 'description' into POJO
     * @param weather
     */
    @SuppressWarnings("unchecked")
    @JsonProperty("weather")
    private void unpackNestedWeather(Map<String,Object>[] weather) {
        Map<String,Object> tempWeather = weather[0];
        this.shortDescription = (String)tempWeather.get("main");
        this.description = (String)tempWeather.get("description");
    }

    /**
     * Parse response and set values 'temperature', 'pressure' and 'feelsLikeTemperature' into POJO
     * @param main
     */
    @SuppressWarnings("unchecked")
    @JsonProperty("main")
    private void unpackNestedMain(Map<String,Object> main) {
        this.temperature = (double)main.get("temp");
        this.feelsLikeTemperature = (double) main.get("feels_like");
        this.pressure = (int) main.get("pressure");
    }

    /**
     * Parse response and set value 'windSpeed' into POJO
     * @param wind
     */
    @SuppressWarnings("unchecked")
    @JsonProperty("wind")
    private void unpackNestedWind(Map<String,Object> wind) {
        this.windSpeed = Double.parseDouble(wind.get("speed").toString());
    }
}
