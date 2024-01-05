package ru.sberbank.edu;

import org.testng.annotations.Test;
import static org.junit.Assert.*;

import java.util.List;


public class TravelServiceTest {
    @Test
    public void WhenAdd() {
        GeoPosition g1 = new GeoPosition("55", "55(45'07'')");
        CityInfo c1 = new CityInfo("Moscow", g1);
        GeoPosition g2 = new GeoPosition("55", "55(46'08'')");
        CityInfo c2 = new CityInfo("Tver", g2);
        GeoPosition g3 = new GeoPosition("55", "55(55'07'')");
        CityInfo c3 = new CityInfo("Tula", g3);
        GeoPosition g4 = new GeoPosition("85", "85(45'07'')");
        CityInfo c4 = new CityInfo("Omsk", g4);
        TravelService t1 = new TravelService();
        t1.add(c1);
        t1.add(c2);
        t1.add(c3);
        t1.add(c4);
        String[] res = new String[]{"Moscow", "Tver", "Tula", "Omsk"};
        assertArrayEquals(t1.citiesNames().toArray(), res);
    }

    @Test
    public void WhenRemove() {
        GeoPosition g1 = new GeoPosition("55", "55(45'07'')");
        CityInfo c1 = new CityInfo("Moscow", g1);
        GeoPosition g2 = new GeoPosition("55", "55(46'08'')");
        CityInfo c2 = new CityInfo("Tver", g2);
        GeoPosition g3 = new GeoPosition("55", "55(55'07'')");
        CityInfo c3 = new CityInfo("Tula", g3);
        GeoPosition g4 = new GeoPosition("85", "85(45'07'')");
        CityInfo c4 = new CityInfo("Omsk", g4);
        TravelService t1 = new TravelService();
        t1.add(c1);
        t1.add(c2);
        t1.add(c3);
        t1.add(c4);
        t1.remove("Tver");
        String[] res = new String[]{"Moscow", "Tula", "Omsk"};
        assertArrayEquals(t1.citiesNames().toArray(), res);
    }

    @Test
    public void WhenGetDistance() {
        GeoPosition g1 = new GeoPosition("55", "55(45'07'')");
        CityInfo c1 = new CityInfo("Moscow", g1);
        GeoPosition g2 = new GeoPosition("55", "55(46'08'')");
        CityInfo c2 = new CityInfo("Tver", g2);
        GeoPosition g3 = new GeoPosition("55", "55(55'07'')");
        CityInfo c3 = new CityInfo("Tula", g3);
        GeoPosition g4 = new GeoPosition("85", "85(45'07'')");
        CityInfo c4 = new CityInfo("Omsk", g4);
        TravelService t1 = new TravelService();
        t1.add(c1);
        t1.add(c2);
        t1.add(c3);
        t1.add(c4);
        t1.getDistance("Tver", "Omsk");
        double res = t1.getDistance("Tver", "Omsk");
        double expectedRes = 3421093.094174924;
        assertEquals(res, expectedRes, 0.0001);
    }

    @Test
    public void WhenGetCitiesNear() {
        GeoPosition g1 = new GeoPosition("55", "55(45'07'')");
        CityInfo c1 = new CityInfo("Moscow", g1);
        GeoPosition g2 = new GeoPosition("55", "55(46'08'')");
        CityInfo c2 = new CityInfo("Tver", g2);
        GeoPosition g3 = new GeoPosition("55", "55(55'07'')");
        CityInfo c3 = new CityInfo("Tula", g3);
        GeoPosition g4 = new GeoPosition("85", "85(45'07'')");
        CityInfo c4 = new CityInfo("Omsk", g4);
        TravelService t1 = new TravelService();
        t1.add(c1);
        t1.add(c2);
        t1.add(c3);
        t1.add(c4);
        List<String> res = t1.getCitiesNear("Tula", 1000000);
        String[] expectedRes = new String[]{"Moscow", "Tver"};
        assertArrayEquals(res.toArray(), expectedRes);
    }
}
