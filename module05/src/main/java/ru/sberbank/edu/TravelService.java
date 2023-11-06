package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Travel Service.
 */
public class TravelService {
    private static final double EARTH_RADIUS_KM = 6371.0;
    // do not change type
    private final List<CityInfo> cities = new ArrayList<>();

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
      if(cities.stream().anyMatch(city -> city.equals(cityInfo))) {
          throw new IllegalArgumentException("This city already added");
      }
      cities.add(cityInfo);
    }

    /**
     * remove city info.
     *
     * @param cityInfo - city name
     * @throws IllegalArgumentException if city doesn't exist
     *
     * ИСПРАВИЛ НА CITYINFO входной параметр! Могут существовать несколько городов с одним названием. Paris например штук 6.
     */
    public void remove(CityInfo cityInfo) {
       List<CityInfo> cityToRemove = cities.stream().filter(city -> city.equals(cityInfo)).toList();
       if(cityToRemove.isEmpty()){
           throw new IllegalArgumentException(String.format("City %s not found",cityInfo.getName()));
       }
       cities.removeAll(cityToRemove);
    }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        return cities.stream()
                .map(CityInfo::getName).toList();
    }

    /**
     * Get distance in kilometers between two cities.
     * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     * @throws IllegalArgumentException if source or destination city doesn't exist.
     */
    public double getDistance(CityInfo srcCityName, CityInfo destCityName) {

        double lat1 = srcCityName.getPosition().getLatitude();
        double lon1 = srcCityName.getPosition().getLongitude();
        double lat2 = destCityName.getPosition().getLatitude();
        double lon2 = destCityName.getPosition().getLongitude();
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<CityInfo> getCitiesNear(CityInfo cityName, double radius) {
        List<CityInfo> targetCity = cities.stream()
                .filter(city -> {
                    double distance = getDistance(city,cityName);
                    return distance < radius;
                })
                .toList();

        if (targetCity.isEmpty()) {
            throw new IllegalArgumentException("Вокруг нет городов в радиусе " + radius);

        } else {
            return targetCity;
        }
    }

}
