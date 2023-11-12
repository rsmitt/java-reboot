package ru.sberbank.edu;

public class App {
    public static void main(String[] args) {
        TravelService travelService = new TravelService();
        travelService.add(new CityInfo("Moscow", new GeoPosition("55(45'07'')", "37(37'03'')")));
        travelService.add(new CityInfo("St Pete", new GeoPosition("59(56'29'')", "30(18'47'')")));
        System.out.println(travelService.getDistance("Moscow", "St Pete"));
    }
}
