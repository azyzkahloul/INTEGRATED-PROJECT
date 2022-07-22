/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Entite.Admin;
import Entite.Client;
import Entite.Parking;
import Entite.Facture;
import Entite.Fourriere;
import Entite.Reclamation;
import Entite.Reponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Bourguiba
 */
   
public class Connexion {

    private static Connexion data;
    private Connection con;
    private static String url = "jdbc:mysql://localhost:3306/instapark";
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
    
    
    public static Connection ConnectDb() {
        try {

            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/instapark", "root", "");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
        public static ObservableList<Admin> getDataEvenement() {
        Connection conn = ConnectDb();
        ObservableList<Admin> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from Admin");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Admin(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("numtel"), rs.getString("login"),rs.getString("password")));
            }
        } catch (Exception e) {
        }
        return list;
    }
        
        public static ObservableList<Reclamation> getDataReclamation() {
                Connection conn = ConnectDb();
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Reclamation ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Reclamation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
        public static ObservableList<Reponse> getDataReponse() {
                Connection conn = ConnectDb();
        ObservableList<Reponse> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Reponse ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Reponse(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }
        
            public static ObservableList<Fourriere> getDataFourriere() {
           Connection conn = ConnectDb();
        ObservableList<Fourriere> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from fourriere");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                     list.add(new Fourriere(rs.getInt("id"), rs.getString("nomf"), rs.getString("nbplace"), rs.getString("flatitude"), rs.getString("flongtitude")));
            }
        } catch (Exception e) {
        }
        return list;
    }
            
      
 public static ObservableList<Parking> getDataParking() {
         Connection conn = ConnectDb();
        ObservableList<Parking> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from parking");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                     list.add(new Parking(rs.getInt("id"), rs.getString("nomp"), rs.getString("nbplace"), rs.getString("adresse"), rs.getString("description")));
            }
        } catch (Exception e) {
        }
        return list;
    }
            
        public static ObservableList<Facture> getDataFacture() {
           Connection conn = ConnectDb();
        ObservableList<Facture> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from facture");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                     list.add(new Facture(rs.getInt("id_facture"), rs.getString("nbheure"), rs.getString("pu"), rs.getString("total"), rs.getString("dateentrer")));
            }
        } catch (Exception e) {
        }
        return list;
    }
          public static ObservableList<Client> getDataClient() {
           Connection conn = ConnectDb();
        ObservableList<Client> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from client");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                     list.add(new Client(rs.getString("nom"), rs.getString("prenom"), rs.getString("numtel"), rs.getString("voitmat"), rs.getString("login"), rs.getString("password"), rs.getString("statut")));
            }
        } catch (Exception e) {
        }
        return list;
    }
            
            
            
}
