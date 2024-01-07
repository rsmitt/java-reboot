package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelServiceTest {

    TravelService travelService;

    @BeforeEach
    public void setUp() {
        travelService = new TravelService();

        CityInfo moscow = new CityInfo();
        moscow.setName("Moscow");
        moscow.setPosition(new GeoPosition("55(01'01'')", "55(01'01'')"));

        CityInfo saintPetersburg = new CityInfo();
        saintPetersburg.setName("Saint Petersburg");
        saintPetersburg.setPosition(new GeoPosition("75(01'01'')", "75(01'01'')"));

        travelService.add(moscow);
        travelService.add(saintPetersburg);
    }

    @Test
    public void addCity_AlreadyExists_ThrowsException() {
        CityInfo city = new CityInfo();
        city.setName("Moscow");
        city.setPosition(new GeoPosition("55(01'01'')", "55(01'01'')"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> travelService.add(city));
    }

    @Test
    public void removeCity_NotFound_ThrowsException() {
        CityInfo city = new CityInfo();
        city.setName("Berlin");

        Assertions.assertThrows(IllegalArgumentException.class, () -> travelService.remove(city));
    }

    @Test
    public void removeCity_Successful_NoException() {
        CityInfo moscow = new CityInfo();
        moscow.setName("Moscow");
        moscow.setPosition(new GeoPosition("55(01'01'')", "55(01'01'')"));
        Assertions.assertDoesNotThrow(() -> travelService.remove(moscow));
    }

    @Test
    public void citiesNames_ReturnsCorrectCitiesList() {
        List<String> cityNames = travelService.citiesNames();

        Assertions.assertTrue(cityNames.contains("Moscow"));
        Assertions.assertTrue(cityNames.contains("Saint Petersburg"));
        assertEquals(2, cityNames.size());
    }

    @Test
    public void getDistance_BetweenTwoCities_ReturnsCorrectDistance() {
        CityInfo moscow = new CityInfo();
        moscow.setName("Moscow");
        moscow.setPosition(new GeoPosition("55(01'01'')", "55(01'01'')"));

        CityInfo saintPetersburg = new CityInfo();
        saintPetersburg.setName("Saint Petersburg");
        saintPetersburg.setPosition(new GeoPosition("75(01'01'')", "75(01'01'')"));

        double distance = travelService.getDistance(moscow, saintPetersburg);

        assertEquals(2384.844535114297, distance);
    }

    @Test
    public void getCitiesNear_WithinRadius_ReturnsCorrectListOfCities() {
        CityInfo city = new CityInfo();
        city.setName("city");
        city.setPosition(new GeoPosition("55(01'01'')", "55(01'01'')"));

        List<CityInfo> nearbyCities = travelService.getCitiesNear(city, 100000);

        assertEquals(nearbyCities.size(), 2);
    }

    @Test
    public void getCitiesNear_NoCitiesWithinRadius_ThrowsException() {
        CityInfo city = new CityInfo();
        city.setName("city");
        city.setPosition(new GeoPosition("99(01'01'')", "99(01'01'')"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> travelService.getCitiesNear(city, 10.0));
    }
}