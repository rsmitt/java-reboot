package ru.sberbank.edu;

import java.util.Arrays;
import java.util.Map;

public class WeaterObj {

    private MainObj[] weather;
    private Map<String, Float> main;
    private Map<String, Float> wind;

    public Object[] getWeather() {
        return weather;
    }

    public void setWeather(MainObj[] weather) {
        this.weather = weather;
    }

    public Map<String, Float> getMain() {
        return main;
    }

    public void setMain(Map<String, Float> main) {
        this.main = main;
    }

    public Map<String, Float> getWind() {
        return wind;
    }

    public void setWind(Map<String, Float> wind) {
        this.wind = wind;
    }

    public WeaterObj() {
    }

    public WeaterObj(MainObj[] weather, Map<String, Float> main, Map<String, Float> wind) {

        this.weather = weather;
        this.main = main;
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "WeaterObj{" +
                "weather=" + Arrays.toString(weather) +
                ", main=" + main +
                ", wind=" + wind +
                '}';
    }

}
