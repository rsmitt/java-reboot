package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AppTest {

    @Test
    @DisplayName("Тест на корректный перевод в радианы из градусной записи")
    public void testGeoPositionConverter() {
        String latitudeDegree = "55(45'31'')";
        String longitudeDegree = "35(23'17'')";
        GeoPosition geoPosition = new GeoPosition(latitudeDegree, longitudeDegree);
        Assertions.assertTrue(geoPosition.getLatitude() == 0.9731713502279826 &&
                geoPosition.getLongitude() == 0.6176380853231155);
    }

    @Test
    @DisplayName("Тест на добавление CityInfo, которго еще нет в списке TravelService")
    public void testAddNonDuplicateCity() {
        TravelService travelService = new TravelService();
        CityInfo cityInfo1 = new CityInfo("N", new GeoPosition("55(45'31'')", "35(23'17'')"));
        CityInfo cityInfo2 = new CityInfo("M", new GeoPosition("56(45'31'')", "34(23'17'')"));
        travelService.add(cityInfo1);
        travelService.add(cityInfo2);
        Assertions.assertEquals(2, travelService.getCities().size());
    }

    @Test
    @DisplayName("Тест на добавление CityInfo, который уже есть в списке TravelService")
    public void testAddDuplicatedCity() {
        TravelService travelService = new TravelService();
        CityInfo cityInfo1 = new CityInfo("N", new GeoPosition("55(45'31'')", "35(23'17'')"));
        CityInfo cityInfo2 = new CityInfo("N", new GeoPosition("55(45'31'')", "35(23'17'')"));
        travelService.add(cityInfo1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> travelService.add(cityInfo2));
    }

    @Test
    @DisplayName("Тест на удаление CityInfo, который есть в списке TravelService")
    public void testRemoveExistedItemInList() {
        TravelService travelService = new TravelService();
        CityInfo cityInfo1 = new CityInfo("N", new GeoPosition("55(45'31'')", "35(23'17'')"));
        CityInfo cityInfo2 = new CityInfo("M", new GeoPosition("56(45'31'')", "34(23'17'')"));
        travelService.add(cityInfo1);
        travelService.add(cityInfo2);
        travelService.remove("M");
        Assertions.assertEquals(1, travelService.getCities().size());
    }

    @Test
    @DisplayName("Тест на удаление CityInfo, которого нет в списке TravelService")
    public void testRemoveNotExistedItemInList() {
        TravelService travelService = new TravelService();
        CityInfo cityInfo1 = new CityInfo("N", new GeoPosition("55(45'31'')", "35(23'17'')"));
        CityInfo cityInfo2 = new CityInfo("M", new GeoPosition("56(45'31'')", "34(23'17'')"));
        travelService.add(cityInfo1);
        travelService.add(cityInfo2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> travelService.remove("K"));
    }

    @Test
    @DisplayName("Тест на метод возврата списка названий городов")
    public void testCitiesNames() {
        TravelService travelService = new TravelService();
        CityInfo cityInfo1 = new CityInfo("N", new GeoPosition("55(45'31'')", "35(23'17'')"));
        CityInfo cityInfo2 = new CityInfo("M", new GeoPosition("56(45'31'')", "34(23'17'')"));
        travelService.add(cityInfo1);
        travelService.add(cityInfo2);
        Assertions.assertEquals(List.of("N", "M"), travelService.citiesNames());
    }

    @Test
    @DisplayName("Тест на расчет расстояния между Московским Кремлем и Питерским Эрмитажем")
    public void testGetDistance() {
        TravelService travelService = new TravelService();
        travelService.add(new CityInfo("Moscow", new GeoPosition("55(45'07'')", "37(37'03'')")));
        travelService.add(new CityInfo("St Pete", new GeoPosition("59(56'29'')", "30(18'47'')")));
        Assertions.assertTrue(travelService.getDistance("Moscow", "St Pete") >= 600 &&
                travelService.getDistance("Moscow", "St Pete") <= 900);
    }

    @Test
    @DisplayName("Тест на метод поиска ближайших городов")
    public void testGetCitiesNear() {
        TravelService travelService = new TravelService();
        travelService.add(new CityInfo("Moscow", new GeoPosition("55(45'07'')", "37(37'03'')")));
        travelService.add(new CityInfo("St Pete", new GeoPosition("59(56'29'')", "30(18'47'')")));
        travelService.add(new CityInfo("Beijing", new GeoPosition("39(54'45'')", "116(23'834'')")));
        Assertions.assertTrue(travelService.getCitiesNear("Moscow", 1000).size() == 1 &&
                travelService.getCitiesNear("Moscow", 1000).get(0).equals("St Pete"));
    }
}
