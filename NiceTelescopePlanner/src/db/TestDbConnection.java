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

/**
 *
 * @author EFA
 */
public class TestDbConnection {
    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        String dataBase = db.Config.getDataBase();
        String server = db.Config.getServer();
        String user = db.Config.getUser();
        String password = db.Config.getPassword();

        String url = "jdbc:mysql://" + server + ":3306/" + dataBase 
                + "?autoReconnect=true&useSSL=false";
        
        
        
        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("INSERT INTO `vrum`.`stand`\n" +
                             "(`codigo`, `nome`)\n" +
                             "VALUES (NULL, 'Stand NPK');");

           st.executeUpdate("INSERT INTO `vrum`.`veiculo`\n" +
                             "(`matricula`, `marca`, `modelo`, `n_portas`, `stand`)\n" +
                             "VALUES ('KITT', 'Knight Industries', 'K.I.T.T.', 3, 1);");

           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }
        finally {
            try {
                if (rs != null)     { rs.close();}
                if (st != null)     { st.close(); }
                if (con != null)    { con.close(); } 
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
            }
        }
        
        
        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM veiculo");

            while (rs.next()) {
                String matricula = rs.getString("matricula");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                String n_portas = rs.getString("n_portas");

                System.out.println("Matrícula: " + matricula + "  Marca: " + marca
                + "  Modelo: " + modelo + "    Nº de portas: " + n_portas);
            
        }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }
        finally {
            try {
                if (rs != null)     { rs.close();}
                if (st != null)     { st.close(); }
                if (con != null)    { con.close(); } 
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
            }
        }
        
        
        
    }
}
