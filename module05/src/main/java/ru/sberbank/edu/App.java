package ru.sberbank.edu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        GeoPosition city = new GeoPosition("55(01'01'')", "55(45'47'')");
        GeoPosition city1 = new GeoPosition("22(01'01'')", "33(45'47'')");
        GeoPosition city3 = new GeoPosition("55(01'03'')", "55(45'48'')");
        System.out.println(city.getLatitude());
        System.out.println(city.getLongitude());
        System.out.println(city.toString());
        CityInfo cityInfo = new CityInfo("Gorod", city);
        CityInfo cityInfo1 = new CityInfo("City", city1);
        CityInfo cityInfo3 = new CityInfo("assa", city3);
        System.out.println(cityInfo.toString());
        TravelService travelService = new TravelService();
        travelService.add(cityInfo);
        travelService.add(cityInfo1);
        System.out.println(travelService.citiesNames());
        System.out.println(travelService.getDistance(cityInfo,cityInfo1));
        System.out.println(travelService.getCitiesNear(cityInfo3,10000));


    }
}
