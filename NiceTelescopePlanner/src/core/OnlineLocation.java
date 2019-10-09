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
    private Double latitude_deg; // in degrees
    private Double longitude_deg; // in degres
    private String timezone;

    public OnlineLocation(String city, String country, Double latitude_deg, 
            Double longitude_deg, String timezone) {
        this.setCity(city);
        this.setCountry(country);
        this.setLatitude(latitude_deg);
        this.setLongitude(longitude_deg);
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

    public Double getLatitude() {
        return latitude_deg;
    }

    public void setLatitude(Double latitude) {
        this.latitude_deg = latitude;
    }

    public Double getLongitude() {
        return longitude_deg;
    }

    public void setLongitude(Double longitude) {
        this.longitude_deg = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.city);
        hash = 43 * hash + Objects.hashCode(this.country);
        hash = 43 * hash + Objects.hashCode(this.latitude_deg);
        hash = 43 * hash + Objects.hashCode(this.longitude_deg);
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
        if (!Objects.equals(this.latitude_deg, other.latitude_deg)) {
            return false;
        }
        if (!Objects.equals(this.longitude_deg, other.longitude_deg)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OnlineLocation{" + "city=" + city + ", country=" + country 
                + ", lat=" + latitude_deg + ", lon=" + longitude_deg + '}';
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone() {
        return timezone;
    }

}
