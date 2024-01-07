package ru.sberbank.edu;

import java.util.Objects;

public class CityInfo {

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(GeoPosition position) {
        this.position = position;
    }

    private String name;
    private GeoPosition position;


    public CityInfo(String name, GeoPosition position) {
        this.name = name;
        this.position = position;
    }

    public CityInfo() {

    }

    public String getName() {
        return name;
    }

    public GeoPosition getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "name='" + name + '\'' +
                ", position=" + position +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o.getClass()==CityInfo.class)) {
            return false;
        }
        CityInfo cityInfo = (CityInfo) o;
        if(!(this.name.equals(cityInfo.name))) {
            return false;
        }
        if (!(this.position.getLatitude()==cityInfo.position.getLatitude())) {
            return false;
        }
          return  this.position.getLongitude()==cityInfo.position.getLongitude() ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}