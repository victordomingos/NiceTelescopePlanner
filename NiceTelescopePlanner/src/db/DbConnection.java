/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import core.Location;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author EFA
 */
public class DbConnection {

    private final String dataBase;
    private final String server;
    private final String user;
    private final String password;
    private final String url;

    public DbConnection() {
        dataBase = db.Config.getDataBase();
        server = db.Config.getServer();
        user = db.Config.getUser();
        password = db.Config.getPassword();
        url = "jdbc:mysql://" + server + ":3306/" + dataBase
                + "?autoReconnect=true&useSSL=false";
    }

    public ArrayList<Location> getAllLocations() {
        Connection con = null;
        Statement st = null;
        ResultSet r = null;
        ArrayList<Location> locations = new ArrayList<>();

        String ssql = "SELECT id, name, address, latitude, longitude, height, timezone "
                + "FROM location "
                + "ORDER BY id DESC";

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            r = st.executeQuery(ssql);

            while (r.next()) {
                locations.add(new Location(r.getInt("id"),
                        r.getString("name"),
                        r.getString("address"),
                        r.getDouble("latitude"),
                        r.getDouble("longitude"),
                        r.getInt("height"),
                        r.getDouble("timezone")));
            }

            return locations;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        } finally {
            try {
                if (r != null) {
                    r.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
            }
        }
        return null;
    }

    public Location getOneLocation(int id) {
        Connection con = null;
        Statement st = null;
        ResultSet r = null;
        Location loc = null;

        String ssql = "SELECT id, name, address, latitude, longitude, height, timezone "
                + "FROM location "
                + "WHERE id=" + id;

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            r = st.executeQuery(ssql);

            while (r.next()) {
                loc = new Location(r.getInt("id"),
                        r.getString("name"),
                        r.getString("address"),
                        r.getDouble("latitude"),
                        r.getDouble("longitude"),
                        r.getInt("height"),
                        r.getDouble("timezone"));

                System.out.println("timezone db: " + r.getDouble("timezone"));
            }

            return loc;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        } finally {
            try {
                if (r != null) {
                    r.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
            }
        }
        return null;
    }

    /**
     * Inserts a new database record on the location table. If the record
     * already exists, it will be updated with the new data.
     *
     * @param locId
     * @param name
     * @param latitude_rad
     * @param longitude_rad
     * @param height
     * @param address
     * @param timezone
     * @return
     */
    public int insertOrUpdateLocation(int locId, String name, Double latitude_rad,
            Double longitude_rad, int height, String address, Double timezone) {

        Connection con = null;
        PreparedStatement st = null;
        String ssql;
        int r = -1;
//
//        System.out.println("=== insertOrUpdateLocation =========================");
//        System.out.println("LAT_RAD: " + latitude_rad);
//        System.out.println("LAT_DEG: " + latitude_rad * Constant.RAD_TO_DEG);
//        System.out.println("LON_RAD: " + longitude_rad);
//        System.out.println("LON_DEG: " + longitude_rad * Constant.RAD_TO_DEG);
//        System.out.println("LOC_TZ:  " + timezone);
//        System.out.println("== /insertOrUpdateLocation =========================");

        try {
            con = DriverManager.getConnection(url, user, password);
            if (locId == -1) {
                ssql = "INSERT INTO location (name, latitude, longitude, height, "
                        + "address, timezone, id) \n"
                        + "VALUES (?, ?, ?, ?, ?, ?, null) \n"
                        + "ON DUPLICATE KEY UPDATE "
                        + "name=VALUES(name), "
                        + "latitude=VALUES(latitude), "
                        + "longitude=VALUES(longitude), "
                        + "height=VALUES(height), "
                        + "address=VALUES(address), "
                        + "timezone=VALUES(timezone), "
                        + "id=LAST_INSERT_ID(id) ";
                st = con.prepareStatement(ssql);

            } else {
                ssql = "INSERT INTO location (name, latitude, longitude, height, "
                        + "address, timezone, id) \n"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?) \n"
                        + "ON DUPLICATE KEY UPDATE "
                        + "name = VALUES(name), "
                        + "latitude = VALUES(latitude), "
                        + "longitude = VALUES(longitude), "
                        + "height = VALUES(height), "
                        + "address = VALUES(address), "
                        + "timezone = VALUES(timezone), "
                        + "id = LAST_INSERT_ID(id) ";
                st = con.prepareStatement(ssql);
                st.setInt(7, locId);
            }
            st.setString(1, name);
            st.setDouble(2, latitude_rad);
            st.setDouble(3, longitude_rad);
            st.setInt(4, height);
            st.setString(5, address);
            st.setDouble(6, timezone);
            System.out.println("DB INSERT:" + timezone);

            r = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        } finally {
            System.out.println("DB TIMEZONE IN: " + timezone);
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
            }
        }
        return r;
    }

    /**
     * Deletes a location record from tghe database
     *
     * @param locId
     */
    public void deleteLocation(int locId) {
        String ssql;
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            ssql = "DELETE FROM location WHERE id=?";
            st = con.prepareStatement(ssql);
            st.setInt(1, locId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
            }
        }
    }

}
