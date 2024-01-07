package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CityInfoTest {

    @Test
    public void testGetName() {
        CityInfo cityInfo = new CityInfo("CityName", new GeoPosition("55(01'01'')", "55(45'47'')"));

        assertEquals("CityName", cityInfo.getName());
    }

    @Test
    public void testGetPosition() {
        CityInfo cityInfo = new CityInfo("CityName", new GeoPosition("55(01'01'')", "55(45'47'')"));

        assertEquals(0.960226824942358, cityInfo.getPosition().getLatitude());
        assertEquals(0.9732489204169602, cityInfo.getPosition().getLongitude());
    }

    @Test
    public void testToString() {
        CityInfo cityInfo = new CityInfo("CityName", new GeoPosition("55(01'01'')", "55(45'47'')"));

        String expected = "CityInfo{name='CityName', position=GeoPosition{latitude=0.960226824942358, longitude=0.9732489204169602}}";
        assertEquals(expected, cityInfo.toString());
    }

    @Test
    public void testEquals() {
        CityInfo cityInfo1 = new CityInfo("CityName", new GeoPosition("55(01'01'')", "55(45'47'')"));
        CityInfo cityInfo2 = new CityInfo("CityName", new GeoPosition("55(01'01'')", "55(45'47'')"));

        assertEquals(cityInfo2, cityInfo1);
    }

    @Test
    public void testEqualsWithDifferentName() {
        CityInfo cityInfo1 = new CityInfo("CityName", new GeoPosition("55(01'01'')", "55(45'47'')"));
        CityInfo cityInfo2 = new CityInfo("CityName ", new GeoPosition("55(01'01'')", "55(45'47'')"));

        assertNotEquals(cityInfo2, cityInfo1);
    }

    @Test
    public void testEqualsWithDifferentLatitude() {
        CityInfo cityInfo1 = new CityInfo("CityName", new GeoPosition("55(01'00'')", "55(45'47'')"));
        CityInfo cityInfo2 = new CityInfo("CityName", new GeoPosition("55(01'01'')", "55(45'47'')"));

        assertNotEquals(cityInfo2, cityInfo1);
    }

}
