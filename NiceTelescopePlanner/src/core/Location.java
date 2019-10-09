/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import static Constants.Constants.DEFAULT_HTTP_CONNECTION_TIMEOUT;
import static Constants.Constants.DEFAULT_LOCATION_HEIGHT;
import com.google.gson.Gson;
import com.skedgo.converter.TimezoneMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.TimeZone;
import jparsec.math.Constant;

/**
 *
 * @author Victor Domingos
 */
public class Location {

    private int id;
    private String name;
    private String address;
    private Double latitude_rad;
    private Double longitude_rad;
    private int height;         // i.e. Altitude (above sea level).
    private Double timezone;

    /**
     * This is the most specific constructor, which should be preferred for db
     * interactions.
     *
     * @param id
     * @param name
     * @param address
     * @param latitude_rad
     * @param longitude_rad
     * @param height
     * @param timezone
     */
    public Location(int id, String name, String address, Double latitude_rad,
            Double longitude_rad, int height, Double timezone) {
        this(name, latitude_rad, longitude_rad, height);
        this.id = id;
        this.address = address;
        this.timezone = timezone;
    }

    /**
     * This is a simpler constructor, which takes just the most essential data.
     *
     * @param name
     * @param latitude_rad in radians
     * @param longitude_rad in radians
     * @param height in meters
     */
    public Location(String name, Double latitude_rad, Double longitude_rad, int height) {
        this.id = -1;
        this.name = name;
        this.address = null;
        this.latitude_rad = latitude_rad;
        this.longitude_rad = longitude_rad;
        this.height = height;
        this.timezone = Location.getTimeZoneOffset(latitude_rad, longitude_rad);
    }

    /**
     * This constructor gets approximate values for latitude/longitude by using
     * an estimation based on the current public IP address.
     *
     * @throws MalformedURLException
     * @throws ProtocolException
     * @throws IOException
     */
    public Location() throws MalformedURLException, ProtocolException, IOException {
        OnlineLocation onloc = getOnlineLocation();
        System.out.println(onloc);
        this.id = -1;
        this.latitude_rad = onloc.getLat() * Constant.DEG_TO_RAD;
        this.longitude_rad = onloc.getLon() * Constant.DEG_TO_RAD;
        this.address = "";
        this.name = onloc.getCity() + "/" + onloc.getCountry() + " (IP)";
        this.height = Integer.parseInt(DEFAULT_LOCATION_HEIGHT);
        this.timezone = Location.getTimeZoneOffset(this.latitude_rad, this.longitude_rad);

    }

    /**
     * Gets the current GPS coordinates (in degrees) based on the current 
     * outbound IP address by parsing a JSON response received from IP-API.com.
     *
     * @return OnlineLocation
     * @throws MalformedURLException
     * @throws ProtocolException
     * @throws IOException
     */
    public OnlineLocation getOnlineLocation() throws MalformedURLException,
            ProtocolException, IOException {
        String line;
        URL url = new URL("http://ip-api.com/json?/fields=city,country,lat,lon,timezone");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(DEFAULT_HTTP_CONNECTION_TIMEOUT);
        con.setReadTimeout(DEFAULT_HTTP_CONNECTION_TIMEOUT);

        StringBuilder sb = new StringBuilder();
        try (BufferedReader inBuffer = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {

            while ((line = inBuffer.readLine()) != null) {
                sb.append(line);
            }
        }
        con.disconnect();

        Gson gson = new Gson();
        OnlineLocation onloc = gson.fromJson(sb.toString(), OnlineLocation.class);
        System.out.println(sb.toString());
        return onloc;
    }

    /**
     * Returns the timezone offset in hours, for the current lat/lon in radians
     * 
     * @return 
     */
    public Double getTimeZoneOffset() {
        System.out.println("getTimeZoneOffset(): " + this.latitude_rad + " - " + this.longitude_rad);
        
        return Location.getTimeZoneOffset(this.latitude_rad, this.longitude_rad);
    }

    /**
     * Returns the timezone offset in hours, for the specified lat/lon in radians
     * 
     * @param latitude_rad in radians
     * @param longitude_rad in radians
     * @return 
     */
    public static Double getTimeZoneOffset(double latitude_rad, double longitude_rad) {
        System.out.println("getTimeZoneOffset(double latitude_rad, double longitude_rad): " 
                        + latitude_rad + " - " + longitude_rad);

        String tzone = TimezoneMapper.latLngToTimezoneString(
                latitude_rad * Constant.RAD_TO_DEG,
                longitude_rad * Constant.RAD_TO_DEG);

        System.out.println("=== getTimeZoneOffset =========================");
        System.out.println("LAT_RAD: " + latitude_rad);
        System.out.println("LAT_DEG: " + latitude_rad * Constant.RAD_TO_DEG);
        System.out.println("LON_RAD: " + longitude_rad);
        System.out.println("LON_DEG: " + longitude_rad * Constant.RAD_TO_DEG);    
        System.out.println("TZONE:   " + tzone);
        System.out.println("TMZ MS:  " + TimeZone.getTimeZone(tzone));
        System.out.println("TMZ HH:  " + TimeZone.getTimeZone(tzone).getRawOffset() / 1000 / 3600);
        System.out.println("DTMZ HH: " + Double.valueOf(TimeZone.getTimeZone(tzone).getRawOffset()) / 1000 / 3600);
        System.out.println("== /getTimeZoneOffset =========================");
        
        return Double.valueOf(TimeZone.getTimeZone(tzone).getRawOffset()) / 1000 / 3600;
    }

    /**
     * Get a timezone identifier for the current lat/lon in radians 
     */
    public String getTimeZoneID() {
        return getTimeZoneID(this.latitude_rad, this.longitude_rad);
    }

    /**
     * Get a timezone identifier from lat/lon (in radians)
     * 
     * @param latitude_rad in radians
     * @param longitude_rad in radians
     * @return 
     */
    public String getTimeZoneID(double latitude_rad, double longitude_rad) {
        String tzone = TimezoneMapper.latLngToTimezoneString(
                latitude_rad * Constant.RAD_TO_DEG, 
                longitude_rad * Constant.RAD_TO_DEG);
        
        System.out.println("TZONE ID: " + tzone);
        return tzone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Double getLatitudeRad() {
        return latitude_rad;
    }

    public Double getLongitudeRad() {
        return longitude_rad;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", name=" + name + ", address=" + address + ", latitude=" + latitude_rad + ", longitude=" + longitude_rad + ", height=" + height + ", timezone=" + timezone + '}';
    }

}
