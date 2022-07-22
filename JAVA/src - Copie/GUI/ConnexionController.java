/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Client;
import Service.ServiceClient;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import utils.Connexion;




/**
 * FXML Controller class
 *
 * @author Bourguiba
 */
public class ConnexionController implements Initializable {
  

    Connection cnx;

    @FXML
    private AnchorPane fenetreprofil;
    private Button profil;
    @FXML
    private Button deco;
    private Label email1;
    @FXML
    private Label labelprenom;
    @FXML
    private AnchorPane fenetreconnexion;
    @FXML
    private TextField email;
    @FXML
    private PasswordField mdp;
    @FXML
    private Label invalid;
    @FXML
    private Button connexionn;
    @FXML
    private Hyperlink mdpoublié;
    @FXML
    private Hyperlink inscription;
    private Button Parking;
    private Button crud;
    @FXML
    private Button apropos;
    @FXML
    private Button user;
    @FXML
    private Button parking;
    @FXML
    private Button fourriere;
    @FXML
    private Button amande;
    @FXML
    private Button facture;
    @FXML
    private Button reclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fenetreconnexion.setVisible(true);
   //fenetreparam.setVisible(false);
    }    

    @FXML
    public void inscri(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/inscription.fxml"));
            Parent root = loader.load();
            inscription.getScene().setRoot(root);
           } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
    @FXML
    private void profil(ActionEvent event) {
         try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ProfilUser.fxml"));
          
            Parent root = loader.load();
            profil.getScene().setRoot(root);
                 } catch (IOException ex) {
    
    
    }
    }

    

    @FXML
    public void deco(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Connexion.fxml"));
          
            Parent root = loader.load();
            
            deco.getScene().setRoot(root);
                 } catch (IOException ex) {
                     System.out.print(ex.getMessage());
    }
    }
    


    @FXML
    public void connexion(ActionEvent event) throws SQLException, IOException {
        if (email.getText().isEmpty() == false && cryptage(mdp.getText()).isEmpty()== false )
        {
             
          cnx = Connexion.getInstance().getConnection();
         
         String req = "select * from client where login = '" + email.getText()+ "' AND password='"+ cryptage(mdp.getText())+ "'" ;
         Statement st = cnx.createStatement();
         ResultSet rs = st.executeQuery(req);
         if (!rs.isBeforeFirst()){
             /*Alert alert = new Alert (Alert.AlertType.ERROR);
             alert.setContentText("Vérifiez vous paramétres s'il vous plaît !");
             alert.show();
             
         } else {
             // notification();
             //email1.setText(email.getText());*/
             String m = "select CONCAT (Nom ,' ', Prenom)  from client where login = '" + email.getText()+ "' " ;
             Statement p = cnx.createStatement();
             ResultSet q = p.executeQuery(m);
             q.next();
             labelprenom.setText(q.getString(1));
             System.out.println(q.getString(1));
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Accueil.fxml"));
          
            Parent root = loader.load();
            connexionn.getScene().setRoot(root);
                 } catch (IOException ex) {
    
    }
             
        }
  
   }
    }

    
           

    
        
    private String cryptage(String pass) { 
        try {
            MessageDigest msg = MessageDigest.getInstance("MD5");
       msg.update(pass.getBytes());
       byte [] rs = msg.digest();
       StringBuilder sb = new  StringBuilder();
       for (byte b :rs)
       {
           sb.append(String.format("%02x", b));
       }
       return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        
        }return "" ;     }
       
    @FXML
   public void mdpoublié(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/methode.fxml"));
            Parent root = loader.load();
            inscription.getScene().setRoot(root);
           } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
        
        
    }

    
    private void crud(ActionEvent event) {
        
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Crud.fxml"));
          
            Parent root = loader.load();
            crud.getScene().setRoot(root);
                 } catch (IOException ex) {
    
    }
    }
      @FXML
    public void apropos(ActionEvent event) {   
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Accueil.fxml"));
          
            Parent root = loader.load();
            Parking.getScene().setRoot(root);
                 } catch (IOException ex) {
    
    }
    }
}
    
   /* public void login(ActionEvent event) throws IOException, NoSuchAlgorithmException, Exception {
        Alert alert = new Alert (Alert.AlertType.ERROR);
        Client user = new Client();
        ServiceClient userService = new ServiceClient();
        user.setLogin(email.getText());
        user.setPassword(mdp.getText());
        Client u = userService.rechercherParEmail(user);
        MessageDigest msg = MessageDigest.getInstance("MD5");
        byte[] hash = msg.digest(mdp.getText().getBytes(StandardCharsets.UTF_8));
        // convertir bytes en hexadécimal
        StringBuilder s = new StringBuilder();
        for (byte b : hash) {
            s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        if (email.getText().equals(u.getLogin()) && s.toString().equals(u.getPassword())) {
           // ServiceClient.login(u);

            if (u.getLogin().toString().equals("abirbourguiba.1998@gmail.com")) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../GUI/Crud.fxml"));
                Parent root = loader.load();
                email.getScene().setRoot(root);
                /*Notifications notificationBuilder = Notifications.create()
                        .title("Succées").text("Bienvenu").graphic(null).position(Pos.CENTER);
                Platform.runLater(() -> notificationBuilder.showInformation());
                
            } else {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../GUI/Fourriere.fxml"));
                Parent root = loader.load();
                email.getScene().setRoot(root);
                Notifications notificationBuilder = Notifications.create()
                        .title("Succées").text("Bienvenu").graphic(null).position(Pos.CENTER);
                Platform.runLater(() -> notificationBuilder.showInformation());
            } else {
                alert.setContentText("Veuillez confirmer votre compte");
                alert.showAndWait();
                userService.modifierVerif(u);
                JavaMailUtils1.sendMail(u);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ActiveAccount.fxml"));
                Parent root = loader.load();
                up.getScene().setRoot(root);
            }
         else {
            alert.setContentText("Veuillez saisir l'email ou le mot de passe correctement");
            alert.showAndWait();
        }
    }
    }*/





    
    
    
    

