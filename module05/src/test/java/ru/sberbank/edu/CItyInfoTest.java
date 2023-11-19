package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CItyInfoTest {

    @Test
    public void constructorTest() {
        CityInfo cityInfo = new CityInfo("Ekaterinburg", new GeoPosition("55", "66(11'22'')"));
        Assertions.assertEquals("Ekaterinburg", cityInfo.getName());
        Assertions.assertEquals(0.9599310885968813, cityInfo.getPosition().getLatitude());
        Assertions.assertEquals(1.1552237356214246, cityInfo.getPosition().getLongitude());
    }

    @Test
    public void toStringTest() {
        CityInfo cityInfo = new CityInfo("Ekaterinburg", new GeoPosition("55", "66(11'22'')"));
        Assertions.assertEquals("CityInfo{" +
                        "name='Ekaterinburg'" +
                        ", position=" +
                        "latitude in radian 0.9599310885968813" +
                        ", longitude in radian 1.1552237356214246" +
                        '}',
                cityInfo.toString());
    }
}

