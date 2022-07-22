/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bourguiba
 */
   
public class Connexion {

    private static Connexion data;
    private Connection con;
    private static String url = "jdbc:mysql://localhost:3306/pidevweb";
    private static String login = "root";
    private static String pwd = "";

    private Connexion() {
        try {
            con = DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Connection getConnection() {
        return con;
    }

    public static Connexion getInstance() {
        if (data == null) {
            data = new Connexion();
        }
        return data;
    }
}
