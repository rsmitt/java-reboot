package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeoPositionTest {

    @Test
    public void degreeToRadianConstructorConvertTest() {
        GeoPosition gp = new GeoPosition("23(45'11'')", "33");
        Assertions.assertEquals(0.41456902685357533, gp.getLatitude());
        Assertions.assertEquals(0.5759586531581288, gp.getLongitude());
    }

    @Test
    public void toStringTest() {
        GeoPosition gp = new GeoPosition("23(45'11'')", "33");
        Assertions.assertEquals("GeoPosition{" +
                "latitude=0.41456902685357533" +
                ", longitude=0.5759586531581288" +
                '}',
                gp.toString());
    }

    @Test
    public void incorrectCoordinates() {
        try {
            GeoPosition gp = new GeoPosition("55(11'22'33)", "55");
            Assertions.fail();
        } catch (IllegalArgumentException e) {}
    }
}
