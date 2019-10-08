/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;


import static Constants.Constants.DEFAULT_HTTP_CONNECTION_TIMEOUT;
import static Constants.Constants.DEFAULT_LOCATION_HEIGHT;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

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
    private double timezone;

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
    public Location(int id, String name, String address, Double latitude, Double longitude, int height, double timezone) {
        this(name, latitude, longitude, height);
        this.id = id;
        this.address = address;
        this.timezone = timezone;
    }

    /**
     * This is a simpler constructor, which takes just the most essential data.
     *
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
        this.timezone = 0;
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
        URL url = new URL("http://ip-api.com/json/?fields=city,country,lat,lon");

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

    public double getTimezone() {
        return timezone;
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", name=" + name + ", address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + ", height=" + height + ", timezone=" + timezone + '}';
    }
    
    

}
