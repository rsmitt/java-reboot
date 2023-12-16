package ru.sberbank.edu;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Weather info.
 */
public class WeatherInfo {

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
    private double windSpeed;

    /**
     * Pressure.
     */
    private double pressure;

    /**
     * Expiry time of weather info.
     * If current time is above expiry time then current weather info is not actual!
     */
    private LocalDateTime expiryTime;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public void setFeelsLikeTemperature(double feelsLikeTemperature) {
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryTime);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!o.getClass().equals(WeatherInfo.class)) {
            return false;
        }
        WeatherInfo weatherInfo = (WeatherInfo) o;
        return city.equals(weatherInfo.getCity()) &&
                shortDescription.equals(weatherInfo.getShortDescription()) &&
                description.equals(weatherInfo.getDescription()) &&
                Double.compare(temperature, weatherInfo.getTemperature()) == 0 &&
                Double.compare(feelsLikeTemperature, weatherInfo.getFeelsLikeTemperature()) == 0 &&
                Double.compare(windSpeed, weatherInfo.getWindSpeed()) == 0 &&
                Double.compare(pressure, weatherInfo.getPressure()) == 0 &&
                expiryTime.equals(weatherInfo.getExpiryTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, shortDescription, description, temperature, feelsLikeTemperature, windSpeed, pressure, expiryTime);
    }
}
