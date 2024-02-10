package ru.sberbank.edu;

import java.time.LocalDateTime;

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

    public String city() {
        return city;
    }

    public String shortDescription() {
        return shortDescription;
    }

    public String description() {
        return description;
    }
    public double temperature() {
        return temperature;
    }

    public double feelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public double windSpeed() {
        return windSpeed;
    }

    public double pressure() {
        return pressure;
    }

    public LocalDateTime expiryTime() {
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

    public static WeatherInfoBuilder getBuilder(){
        return new WeatherInfo().new WeatherInfoBuilder();
    }

    public class WeatherInfoBuilder{
        private WeatherInfoBuilder(){}

        public WeatherInfo build(){
            return WeatherInfo.this;
        }

        public WeatherInfoBuilder setCity(String city) {
            WeatherInfo.this.city = city;
            return this;
        }

        public WeatherInfoBuilder setShortDescription(String shortDescription) {
            WeatherInfo.this.shortDescription = shortDescription;
            return this;
        }

        public WeatherInfoBuilder setDescription(String description) {
            WeatherInfo.this.description = description;
            return this;
        }

        public WeatherInfoBuilder setTemperature(double temperature) {
            WeatherInfo.this.temperature = temperature;
            return this;
        }

        public WeatherInfoBuilder setFeelsLikeTemperature(double feelsLikeTemperature) {
            WeatherInfo.this.feelsLikeTemperature = feelsLikeTemperature;
            return this;
        }
        public WeatherInfoBuilder setPressure(double pressure) {
            WeatherInfo.this.pressure = pressure;
            return this;
        }

        public WeatherInfoBuilder setExpiryTime(LocalDateTime expiryTime) {
            WeatherInfo.this.expiryTime = expiryTime;
            return this;
        }

        public WeatherInfoBuilder setWindSpeed(double windSpeed) {
            WeatherInfo.this.windSpeed = windSpeed;
            return this;
        }
    }
}
