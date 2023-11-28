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
        this.latitude = Math.toRadians(gradusCalculate(latitudeGradus));
        this.longitude = Math.toRadians(gradusCalculate(longitudeGradus));
    }

    private Double gradusCalculate(String gradus) {
        String[] parseGradus = gradus.split("\\D+");
        if (parseGradus.length == 1) {
            return Double.parseDouble(parseGradus[0]);
        } else if (parseGradus.length == 3) {
            return Double.parseDouble(parseGradus[0]) + Double.parseDouble(parseGradus[1])/60.0 + Double.parseDouble(parseGradus[2])/3600.0;
        } else {
            throw new IllegalArgumentException("Incorrect input data. Expected format 55, 55(45'07'')");
        }
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