package ru.sberbank.edu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        GeoPosition g1 = new GeoPosition("55", "55(45'07'')");
        CityInfo c1 = new CityInfo("m1", g1);

        GeoPosition g2 = new GeoPosition("55", "55(46'08'')");
        CityInfo c2 = new CityInfo("m2", g2);

        GeoPosition g3 = new GeoPosition("75", "75(45'07'')");
        CityInfo c3 = new CityInfo("m3", g3);

        GeoPosition g4 = new GeoPosition("85", "85(45'07'')");
        CityInfo c4 = new CityInfo("m4", g4);

        TravelService t1 = new TravelService();

        t1.add(c1);
        t1.add(c2);
        t1.add(c3);
        t1.add(c4);

        System.out.println(t1.citiesNames());

        t1.remove("m3");

        System.out.println(t1.citiesNames());

        System.out.println(t1.getDistance("m1", "m2"));
    }
}
