package ru.sberbank.edu;

/**
 * Geo position.
 */
public class GeoPosition {
    private final double latitude; // широта в радианах
    private final double longitude; // долгота в радианах

    public GeoPosition(String latitudeGradus, String longitudeGradus) {
        this.latitude = parseDegrees(latitudeGradus);
        this.longitude = parseDegrees(longitudeGradus);
    }

    private double parseDegrees(String degrees) {
        if (!degrees.contains("(")) {
            return Math.toRadians(Double.parseDouble(degrees));
        }
        double radian = Double.parseDouble(degrees.substring(0, 2));
        int minutesIndex = degrees.indexOf("(") + 1;
        int secondsIndex = degrees.indexOf("'") + 1;
        double minutes = Double.parseDouble(degrees.substring(minutesIndex, secondsIndex - 1));
        double seconds = Double.parseDouble(degrees.substring(secondsIndex, degrees.length() - 3));

        // Перевод минут и секунд в доли градуса
        double degreesOffset = radian + (minutes / 60) + (seconds / 3600);
        return Math.toRadians(degreesOffset);
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
}