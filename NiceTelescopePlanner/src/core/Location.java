/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Victor Domingos
 */
public class Location {
    int id;
    String name;
    String address;
    Double latitude;
    Double longitude;
    int height;         // i.e. Altitude (above sea level).
    double timezone;
    
    /**
     * This is the most specific constructor, which should be preferred
     * for db interactions.
     * 
     * @param id
     * @param name
     * @param address
     * @param latitude
     * @param longitude
     * @param height
     * @param timezone
     */
    public Location(int id, String name, String address, Double latitude, Double longitude, int height, double timezone) {
        this(name, latitude, longitude, height);
        this.id = id;
        this.address = address;
        this.timezone = timezone;
    }
    
    /**
     * This is a simpler constructor, which takes just the most essential data.
     * @param name
     * @param latitude
     * @param longitude
     * @param height
     */
    public Location(String name, Double latitude, Double longitude, int height) {
        this.id = -1;
        this.name = name;
        this.address = null;
        this.latitude = latitude;
        this.longitude = longitude;
        this.height = height;
        this.timezone = 0;
    }
    
}

