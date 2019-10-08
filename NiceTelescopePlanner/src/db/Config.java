/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

public class Config {

    private static String dataBase = "nice_telescope_planner";
    private static String server = "192.168.23.129";
    private static String user = "vk";
    private static String password = "123.Abcç";

    public static String getDataBase() {
        return dataBase;
    }

    public static String getServer() {
        return server;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

}
