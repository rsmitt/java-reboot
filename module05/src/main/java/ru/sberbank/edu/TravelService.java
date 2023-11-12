package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;

/**
 * Travel Service.
 */
public class TravelService {

    // do not change type
    private final List<CityInfo> cities = new ArrayList<>();

    private static final int EARTH_RADIUS_KM = 6373;

    public List<CityInfo> getCities() {
        return cities;
    }

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        if (cities.contains(cityInfo)) {
            throw new IllegalArgumentException("Данный город уже содержится в списке TravelService");
        } else {
            cities.add(cityInfo);
        }
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) {
        if (!citiesNames().contains(cityName)) {
            throw new IllegalArgumentException("Данного города нет в списке TravelService");
        } else {
            cities.removeIf(x -> x.getName().equals(cityName));
        }
    }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        return cities
                .stream()
                .map(CityInfo::getName)
                .toList();
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
        GeoPosition geoPositionSrc = cities
                .stream()
                .filter(cityInfo -> cityInfo.getName().equals(srcCityName))
                .map(CityInfo::getPosition)
                .findFirst()
                .orElse(null);
        GeoPosition geoPositionDest = cities
                .stream()
                .filter(cityInfo -> cityInfo.getName().equals(destCityName))
                .map(CityInfo::getPosition)
                .findFirst()
                .orElse(null);
        if (geoPositionSrc == null || geoPositionDest == null) {
            throw new IllegalArgumentException("Такого/таких города/городов нет в списке TravelService");
        } else {
            double latitudeSrc = geoPositionSrc.getLatitude();
            double longitudeSrc = geoPositionSrc.getLongitude();
            double latitudeDest = geoPositionDest.getLatitude();
            double longitudeDest = geoPositionDest.getLongitude();
            double deltaLongitude = longitudeDest - longitudeSrc;
            return (int) (EARTH_RADIUS_KM * Math.acos(Math.sin(latitudeSrc) * Math.sin(latitudeDest) +
                    Math.cos(latitudeSrc) * Math.cos(latitudeDest) * Math.cos(deltaLongitude)));
//            return (int) (EARTH_RADIUS_KM * Math.atan(Math.sqrt(Math.pow(Math.cos(latitudeDest) * Math.sin(deltaLongitude), 2) +
//                    Math.pow(Math.cos(latitudeSrc) * Math.sin(latitudeDest) - Math.sin(latitudeSrc) * Math.sin(latitudeDest) * Math.cos(deltaLongitude), 2)) /
//                    (Math.sin(latitudeSrc) * Math.sin(latitudeDest) + Math.cos(latitudeSrc) * Math.cos(latitudeDest) * Math.cos(deltaLongitude))));
        }
    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<String> getCitiesNear(String cityName, int radius) {
        if (!citiesNames().contains(cityName)) {
            throw new IllegalArgumentException("Такого/таких города/городов нет в списке TravelService");
        } else {
            return cities
                    .stream()
                    .map(CityInfo::getName)
                    .filter(name -> getDistance(name, cityName) <= radius)
                    .filter(name -> !name.equals(cityName))
                    .toList();
        }
    }
}
