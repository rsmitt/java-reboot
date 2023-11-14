package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Travel Service.
 */
public class TravelService {

    // do not change type
    private List<CityInfo> cities = new ArrayList<>();

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        cities.add(cityInfo);
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) {
        Stream<CityInfo> streamOfCities = cities.stream();
        cities = streamOfCities.filter(x -> !cityName.equals(x.getName()))
                .collect(Collectors.toList());

    }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        Stream<CityInfo> streamOfCities = cities.stream();
        return streamOfCities.map(CityInfo::getName)
                .collect(Collectors.toList());
    }

    /**
     * Get distance in kilometers between two cities.
     * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     * @throws IllegalArgumentException if source or destination city doesn't exist.
     */
    public double getDistance(String srcCityName, String destCityName) {
        Stream<CityInfo> streamOfCities = cities.stream();
        List<CityInfo> citiesResult = streamOfCities.filter(x -> srcCityName.equals(x.getName()) ||
                destCityName.equals(x.getName())).toList();
        GeoPosition geo_1 = citiesResult.get(0).getPosition();
        GeoPosition geo_2 = citiesResult.get(1).getPosition();
        double cl1 = Math.cos(geo_1.getLatitude());
        double cl2 = Math.cos(geo_2.getLatitude());
        double sl1 = Math.sin(geo_1.getLatitude());
        double sl2 = Math.sin(geo_2.getLatitude());
        double delta = geo_1.getLongitude() - geo_2.getLongitude();
        double cDelta = Math.cos(delta);
        double sDelta = Math.sin(delta);
        double y = Math.sqrt(Math.pow(cl2 * sDelta, 2) + Math.pow(cl1 * sl2 - sl1 * cl2 * cDelta, 2));
        double x = sl1 * sl2 + cl1 * cl2 * cDelta;
        double ad = Math.atan2(y, x);
        return ad * 6372795.0;
    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<String> getCitiesNear(String cityName, int radius) {
        return null;
    }
}
