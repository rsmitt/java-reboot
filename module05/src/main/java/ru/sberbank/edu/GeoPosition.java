package ru.sberbank.edu;

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

    /**
     * Ctor.
     *
     * @param latitudeGradus  - latitude in gradus
     * @param longitudeGradus - longitude in gradus
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGradus, String longitudeGradus) {
        if (latitudeGradus.length() <= 3) {
            latitude = Math.toRadians(Double.parseDouble(latitudeGradus));
        }
        else if (longitudeGradus.length() <= 3) {
            longitude = Math.toRadians(Double.parseDouble(longitudeGradus));
        }
        else if (longitudeGradus.length() > 3) {
            double grad = 0.0;
            double minutes = 0.0;
            double sec = 0.0;
            latitude = grad + (minutes / 60.0) + (sec / 3600.0);
        }
        else if (longitudeGradus.length() > 3) {

        }
        else {
            System.out.println("Введите значения в формате 55 или 55(45'07'')");
        }

        latitude = Math.toRadians(Double.parseDouble(latitudeGradus));
        longitude = Math.toRadians(Double.parseDouble(longitudeGradus));
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}