/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.Objects;

/**
 * This class is a generic container intended to receive the contents of a web
 * request from core.Location.getOnlineLocation().
 * 
 * @author victordomingos
 */
public final class OnlineLocation {

    private String city;
    private String country;
    private Double lat;
    private Double lon;

    public OnlineLocation(String city, String country, Double latitude, 
            Double longitude) {
        this.setCity(city);
        this.setCountry(country);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return lat;
    }

    public void setLatitude(Double latitude) {
        this.lat = latitude;
    }

    public Double getLongitude() {
        return lon;
    }

    public void setLongitude(Double longitude) {
        this.lon = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.city);
        hash = 43 * hash + Objects.hashCode(this.country);
        hash = 43 * hash + Objects.hashCode(this.lat);
        hash = 43 * hash + Objects.hashCode(this.lon);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OnlineLocation other = (OnlineLocation) obj;
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.lat, other.lat)) {
            return false;
        }
        if (!Objects.equals(this.lon, other.lon)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OnlineLocation{" + "city=" + city + ", country=" + country 
                + ", lat=" + lat + ", lon=" + lon + '}';
    }

}
