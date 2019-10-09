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
    private Double latitude;
    private Double longitude;
    private int height;         // i.e. Altitude (above sea level).
    private Double timezone;

    /**
     * This is the most specific constructor, which should be preferred for db
     * interactions.
     *
     * @param id
     * @param name
     * @param address
     * @param latitude
     * @param longitude
     * @param height
     * @param timezone
     */
    public Location(int id, String name, String address, Double latitude, 
            Double longitude, int height, Double timezone) {
        this(name, latitude, longitude, height);
        this.id = id;
        this.address = address;
        this.timezone = timezone;
    }

    /**
     * This is a simpler constructor, which takes just the most essential data.
     *
     * @param name
     * @param latitude in radians
     * @param longitude in radians
     * @param height in meters
     */
    public Location(String name, Double latitude, Double longitude, int height) {
        this.id = -1;
        this.name = name;
        this.address = null;
        this.latitude = latitude;
        this.longitude = longitude;
        this.height = height;
        this.timezone = Location.getTimeZoneOffset(latitude, longitude);
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
        this.id = -1;
        this.latitude = onloc.getLatitude();
        this.longitude = onloc.getLongitude();
        this.address = "";
        this.name = onloc.getCity() + "/" + onloc.getCountry() + " (IP)";
        this.height = Integer.parseInt(DEFAULT_LOCATION_HEIGHT);
        this.timezone = Location.getTimeZoneOffset(onloc.getLatitude(), onloc.getLongitude());
                
                
        
    }

    /**
     * Gets the current GPS coordinates based on the current outbound IP address 
     * by parsing a JSON response received from IP-API.com.
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
        OnlineLocation loc = gson.fromJson(sb.toString(), OnlineLocation.class);
        return loc;
    }
    
    public Double getTimeZoneOffset(){
        return Location.getTimeZoneOffset(this.latitude, this.longitude) / 1000 / 3600;
    }
    
    public static Double getTimeZoneOffset(double latitude, double longitude){
        String tzone = TimezoneMapper.latLngToTimezoneString(
                latitude * Constant.RAD_TO_DEG, 
                longitude * Constant.RAD_TO_DEG);
        
        
        System.out.println(latitude * Constant.RAD_TO_DEG + " - " + longitude * Constant.RAD_TO_DEG);
        System.out.println("T1:" + tzone);
        System.out.println("T2:" + TimeZone.getTimeZone(tzone));
        System.out.println("T3:" + TimeZone.getTimeZone(tzone).getRawOffset()/1000/3600);
        
        
        return Double.valueOf(TimeZone.getTimeZone(tzone).getRawOffset()) / 1000 / 3600;
    }
    
    
    public String getTimeZoneID(){
        return getTimeZoneID(this.latitude, this.longitude);
    }
    
    public String getTimeZoneID(double latitude, double longitude){
        String tzone = TimezoneMapper.latLngToTimezoneString(latitude, longitude);
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

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public int getHeight() {
        return height;
    }

  

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", name=" + name + ", address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + ", height=" + height + ", timezone=" + timezone + '}';
    }
    
    

}
