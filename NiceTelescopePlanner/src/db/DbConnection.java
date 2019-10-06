/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

import core.Location;
import java.sql.PreparedStatement;
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

    private ResultSet getData(String ssql) {
        Connection con = null;
        Statement st = null;
        ResultSet r = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            r = st.executeQuery(ssql);
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
        return r;
    }

    private int updateData(String ssql) {
        Connection con = null;
        Statement st = null;
        int r = -1;

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            r = st.executeUpdate(ssql);
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
        return r;
    }

    public ArrayList<Location> getAllLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        String ssql = "SELECT id, name, address, latitude, longitude, height, timezone "
                + "FROM location";

        ResultSet r = getData(ssql);

        try {
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
            return null;
        }
    }

    /**
     * Inserts a new database record on the location table. If the record
     * already exists, it will be updated with the new data.
     *
     * @param locId
     * @param name
     * @param latitude
     * @param longitude
     * @param height
     * @param address
     * @param timezone
     * @return
     */
    public int insertOrUpdateLocation(int locId, String name, Double latitude,
            Double longitude, int height, String address, Double timezone) {
        String ssql;

        Connection con = null;
        PreparedStatement st = null;
        int r = -1;

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
            st.setDouble(2, latitude);
            st.setDouble(3, longitude);
            st.setInt(4, height);
            st.setString(5, address);
            st.setDouble(6, timezone);
            
            
//            System.out.println(ssql);
//            System.out.println(st);
//            System.out.println(st.getParameterMetaData());
//            System.out.println(st.getWarnings());
//            
            
            r = st.executeUpdate();
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
        return r;
    }

    /*
    
        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            r = st.executeQuery("SELECT * FROM veiculo");

            while (r.next()) {
                String matricula = r.getString("matricula");
                String marca = r.getString("marca");
                String modelo = r.getString("modelo");
                String n_portas = r.getString("n_portas");

                System.out.println("Matrícula: " + matricula + "  Marca: " + marca
                + "  Modelo: " + modelo + "    Nº de portas: " + n_portas);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }
        finally {
            try {
                if (r != null)    { r.close();}
                if (st != null)   { st.close(); }
                if (con != null)  { con.close(); } 
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
            }
        }
     */
}
