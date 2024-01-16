package ru.sberbank.edu;

import java.time.LocalDateTime;

/**
 * Weather info.
 */
public class WeatherInfo {

    private String city;
    private String shortDescription;
    private String description;
    private double temperature;
    private double feelsLikeTemperature;
    private double windSpeed;
    private double pressure;
    private LocalDateTime expiryTime;

    public String getCity() {
        return city;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getPressure() {
        return pressure;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setFeelsLikeTemperature(double feelsLikeTemperature) {
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
}
