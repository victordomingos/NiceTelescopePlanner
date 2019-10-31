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

    // These names must stay just like this 
    // for the sake of JSON conversion compatibility:
    private String city;
    private String country;
    private Double lat; // in degrees
    private Double lon; // in degres
    private String timezone;

    public OnlineLocation(String city, String country, Double latitude,
            Double longitude, String timezone) {
        this.setCity(city);
        this.setCountry(country);
        this.setLat(latitude);
        this.setLon(longitude);
        this.setTimezone(timezone);
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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
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
        if (!Objects.equals(this.city, other.getCity())) {
            return false;
        }
        if (!Objects.equals(this.country, other.getCountry())) {
            return false;
        }
        if (!Objects.equals(this.lat, other.getLat())) {
            return false;
        }
        if (!Objects.equals(this.lon, other.getLon())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OnlineLocation{" + "city=" + city + ", country=" + country
                + ", lat=" + lat + "º, lon=" + lon
                + "º, timezone=" + timezone + "}";
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone() {
        return timezone;
    }

}
