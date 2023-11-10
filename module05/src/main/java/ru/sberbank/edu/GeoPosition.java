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
        String[] latitudeGradusArray = latitudeGradus.split("[(')]");
        String[] longitudeGradusArray = longitudeGradus.split("[(')]");

        double latitudeGrad = Integer.parseInt(latitudeGradusArray[0]);
        double latitudeMinutes = latitudeGradusArray.length > 1 ? Integer.parseInt(latitudeGradusArray[1]) : 0;
        double latitudeSec = latitudeGradusArray.length > 1 ? Integer.parseInt(latitudeGradusArray[2]) : 0;

        double longitudeGrad = Integer.parseInt(longitudeGradusArray[0]);
        double longitudeMinutes = longitudeGradusArray.length > 1 ? Integer.parseInt(longitudeGradusArray[1]) : 0;
        double longitudeSec = longitudeGradusArray.length > 1 ? Integer.parseInt(longitudeGradusArray[2]) : 0;

        latitude = latitudeGrad > 0 ? latitudeGrad + (latitudeMinutes / 60.0) + (latitudeSec / 3600.0) :
                latitudeGrad + (-latitudeMinutes / 60.0) + (-latitudeSec / 3600.0);
        longitude = longitudeGrad > 0 ?  longitudeGrad + (longitudeMinutes / 60.0) + (longitudeSec / 3600.0) :
                latitudeGrad + (-latitudeMinutes / 60.0) + (-latitudeSec / 3600.0);
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