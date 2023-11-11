package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoPositionTest {

    @Test
    public void testGetLatitude() {
        GeoPosition geoPosition = new GeoPosition("10.5", "20.5");

        assertEquals(0.1832595714594046, geoPosition.getLatitude());
    }

    @Test
    public void testGetLongitude() {
        GeoPosition geoPosition = new GeoPosition("10.5", "20.5");

        assertEquals(0.35779249665883756, geoPosition.getLongitude());
    }

    @Test
    public void testToString() {
        GeoPosition geoPosition = new GeoPosition("10.5", "20.5");

        String expected = "GeoPosition{latitude=0.1832595714594046, longitude=0.35779249665883756}";
        assertEquals(expected, geoPosition.toString());
    }

    @Test
    public void testGetLatitudeWithSecs() {
        GeoPosition geoPosition = new GeoPosition("55(01'01'')", "55(45'47'')");

        assertEquals(0.960226824942358, geoPosition.getLatitude());
    }

    @Test
    public void testGetLongitudeWithSecs() {
        GeoPosition geoPosition = new GeoPosition("55(01'01'')", "55(45'47'')");

        assertEquals(0.9732489204169602, geoPosition.getLongitude());
    }

}
