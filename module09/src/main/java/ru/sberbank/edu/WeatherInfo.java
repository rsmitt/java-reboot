package ru.sberbank.edu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo implements Serializable {


    public WeatherInfo() {
    }

    @JsonProperty("name")
    private String city;

    /**
     * Weather description.
     * Like 'broken clouds', 'heavy raining', etc
     */
    private String description;
    /**
     * Short weather description
     * Like 'sunny', 'clouds', 'raining', etc
     */
    private String shortDescription;
    @JsonProperty("weather")
    private void setShortDescription(List<Weather> weather) {
        Weather temp = weather.get(0);
        shortDescription = temp.getMain();
        description = temp.getDescription();
    }

    /**
     * Temperature that fells like.
     */
    private double feelsLikeTemperature;
    /**
     * Temperature.
     */
    private double temperature;
    @JsonProperty("main")
    private void setTemperature(Map<String, String> main) {
        // Температура в Цельсиях:
        temperature = Math.round((Double.parseDouble(main.get("temp"))-273)*100.0) / 100.0;
        // "Ощущаемая как" температура в Цельсиях:
        feelsLikeTemperature = Math.round((Double.parseDouble(main.get("feels_like"))-273)*100.0) / 100.0;
        // Атмосферное давление в мм рт ст:
        pressure = Math.round((Double.parseDouble(main.get("pressure")) * 7.50062)/10.0);
    }

    /**
     * Wind speed.
     */
    private double windSpeed;
    @JsonProperty("wind")
    private void setWindSpeed(Map<String, String> wind) {
        windSpeed = Double.parseDouble(wind.get("speed"));
    }

    /**
     * Pressure.
     */
    private double pressure;

    /**
     * Expiry time of weather info.
     * If current time is above expiry time then current weather info is not actual!
     */
    private LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "city='" + city + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", temperature=" + temperature +
                ", feelsLikeTemperature=" + feelsLikeTemperature +
                ", windSpeed=" + windSpeed +
                ", pressure=" + pressure +
                ", expiryTime=" + expiryTime +
                '}';
    }
}