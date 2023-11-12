package ru.sberbank.edu;

import java.util.Objects;

/**
 * Geo position.
 */
public class GeoPosition {

    /**
     * Широта в радианах.
     */
    private double latitude;

    /**
     * Долгота в радианах.
     */
    private double longitude;

    private double converterFromDegreesToRadians(String inputString) {
        String[] parsedString = inputString.split("[([']\"[''])]");
        double output = 0;
        for (int i = 0; i < parsedString.length; i++) {
            output += Integer.parseInt(parsedString[i]) / Math.pow(60, i);
        }
        output *= Math.PI / 180;
        return output;
    }

    /**
     * Ctor.
     *
     * @param latitudeGradus  - latitude in gradus
     * @param longitudeGradus - longitude in gradus
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGradus, String longitudeGradus) {
        this.latitude = converterFromDegreesToRadians(latitudeGradus);
        this.longitude =  converterFromDegreesToRadians(longitudeGradus);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!o.getClass().equals(GeoPosition.class)) {
            return false;
        }
        GeoPosition geoPosition = (GeoPosition) o;
        return latitude == geoPosition.getLatitude() && longitude == geoPosition.getLongitude();
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
