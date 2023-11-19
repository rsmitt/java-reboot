package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Travel Service.
 */
public class TravelService {

    // do not change type
    private final List<CityInfo> cities = new ArrayList<>();

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        if (cities.stream().noneMatch(c -> c.getName().equals(cityInfo.getName()))) {
            cities.add(cityInfo);
        } else {
            throw new IllegalArgumentException("City already exist: " + cityInfo);
        }
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) {
        if (!cities.removeIf(city -> city.getName().equals(cityName))) {
            throw new IllegalArgumentException("City doesn't exist: " + cityName);
        }
    }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        return cities.stream()
                .map(CityInfo::getName)
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
    public int getDistance(String srcCityName, String destCityName) {
        final int EARTH_RADIUS = 6372795;

        CityInfo srcCityInfo = cities.stream().filter(c -> c.getName().equals(srcCityName)).findFirst().orElseThrow(IllegalArgumentException::new);
        CityInfo dstCityInfo = cities.stream().filter(c -> c.getName().equals(destCityName)).findFirst().orElseThrow(IllegalArgumentException::new);

        double cosLatitudeSrcCity = Math.cos(srcCityInfo.getPosition().getLatitude());
        double cosLatitudeDstCity = Math.cos(dstCityInfo.getPosition().getLatitude());
        double sinLatitudeSrcCity = Math.sin(srcCityInfo.getPosition().getLatitude());
        double sinLatitudeDstCity = Math.sin(dstCityInfo.getPosition().getLatitude());
        double deltaLongitudeBetweenCities = dstCityInfo.getPosition().getLongitude() - srcCityInfo.getPosition().getLongitude();
        double cosDeltaLongitudeBetweenCities = Math.cos(deltaLongitudeBetweenCities);
        double sinDeltaLongitudeBetweenCities = Math.sin(deltaLongitudeBetweenCities);

        double y = Math.sqrt(Math.pow(cosLatitudeDstCity * sinDeltaLongitudeBetweenCities, 2)
                + Math.pow(cosLatitudeSrcCity * sinLatitudeDstCity - sinLatitudeSrcCity * cosLatitudeDstCity * cosDeltaLongitudeBetweenCities, 2));
        double x = sinLatitudeSrcCity * sinLatitudeDstCity + cosLatitudeSrcCity * cosLatitudeDstCity * cosDeltaLongitudeBetweenCities;

        double atan2 = Math.atan2(y, x);
        double result = atan2 * EARTH_RADIUS;
        return (int) result/1000;
    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<String> getCitiesNear(String cityName, int radius) {
        return cities.stream().filter(c -> !c.getName().equals(cityName) && getDistance(cityName, c.getName()) <= radius).map(CityInfo::getName).toList();
    }
}
