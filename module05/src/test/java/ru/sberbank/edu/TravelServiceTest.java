package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TravelServiceTest {

    @Test
    public void addCityTest() {
        TravelService service = new TravelService();
        service.add(new CityInfo("Ekaterinburg", new GeoPosition("56(50'00'')", "60(35'00'')")));
        Assertions.assertEquals(1, service.citiesNames().size());
        Assertions.assertEquals("Ekaterinburg", service.citiesNames().get(0));

        try {
            service.add(new CityInfo("Ekaterinburg", new GeoPosition("56(50'01'')", "60(35'01'')")));
            Assertions.fail();
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void removeCityTest() {
        TravelService service = new TravelService();
        service.add(new CityInfo("Ekaterinburg", new GeoPosition("56(50'00'')", "60(35'00'')")));
        service.add(new CityInfo("Omsk", new GeoPosition("54(58'00'')", "73(23'00'')")));
        Assertions.assertEquals(2, service.citiesNames().size());
        Assertions.assertEquals("Ekaterinburg", service.citiesNames().get(0));
        Assertions.assertEquals("Omsk", service.citiesNames().get(1));
        service.remove("Ekaterinburg");
        Assertions.assertEquals(1, service.citiesNames().size());
        Assertions.assertEquals("Omsk", service.citiesNames().get(0));

        try {
            service.remove("Ekaterinburg");
            Assertions.fail();
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void cityNamesTest() {
        TravelService service = new TravelService();
        service.add(new CityInfo("Ekaterinburg", new GeoPosition("56(50'00'')", "60(35'00'')")));
        service.add(new CityInfo("Omsk", new GeoPosition("54(58'00'')", "73(23'00'')")));
        service.add(new CityInfo("Moscow", new GeoPosition("55(44'24'')", "37(36'36'')")));
        service.add(new CityInfo("Sochi", new GeoPosition("43(35'07'')", "39(43'13'')")));
        service.add(new CityInfo("Perm", new GeoPosition("55(09'00'')", "61(24'00'')")));

        List<String> list = new ArrayList<>();
        list.add("Ekaterinburg");
        list.add("Omsk");
        list.add("Moscow");
        list.add("Sochi");
        list.add("Perm");

        Assertions.assertEquals(list, service.citiesNames());
    }

    @Test
    public void getDistanceTest() {
        TravelService service = new TravelService();
        service.add(new CityInfo("Ekaterinburg", new GeoPosition("56(50'00'')", "60(35'00'')")));
        service.add(new CityInfo("Omsk", new GeoPosition("54(58'00'')", "73(23'00'')")));
        service.add(new CityInfo("Moscow", new GeoPosition("55(44'24'')", "37(36'36'')")));
        service.add(new CityInfo("Sochi", new GeoPosition("43(35'07'')", "39(43'13'')")));
        service.add(new CityInfo("Perm", new GeoPosition("55(09'00'')", "61(24'00'')")));

        Assertions.assertEquals(823, service.getDistance("Ekaterinburg", "Omsk"));

        try {
            service.getDistance("Chelyabinsk", "Moscow");
            Assertions.fail();
        } catch (IllegalArgumentException e) {}

        try {
            service.getDistance("Moscow", "London");
            Assertions.fail();
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void getCitiesNearTest() {
        TravelService service = new TravelService();
        service.add(new CityInfo("Ekaterinburg", new GeoPosition("56(50'00'')", "60(35'00'')")));
        service.add(new CityInfo("Omsk", new GeoPosition("54(58'00'')", "73(23'00'')")));
        service.add(new CityInfo("Moscow", new GeoPosition("55(44'24'')", "37(36'36'')")));
        service.add(new CityInfo("Sochi", new GeoPosition("43(35'07'')", "39(43'13'')")));
        service.add(new CityInfo("Perm", new GeoPosition("55(09'00'')", "61(24'00'')")));

        List<String> list = new ArrayList<>();
        list.add("Omsk");
        list.add("Perm");

        Assertions.assertEquals(list, service.getCitiesNear("Ekaterinburg", 1000));

        try {
            service.getCitiesNear("Chelyabinsk", 2000);
            Assertions.fail();
        } catch (IllegalArgumentException e) {}

    }


}
