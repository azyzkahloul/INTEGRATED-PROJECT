/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import utils.Connexion;

/**
 * FXML Controller class
 *
 * @author Bourguiba
 */
public class ResetController implements Initializable {
   
    Connection cnx ;
    @FXML
    private Button annuler;
    @FXML
    private Button Reset;
    @FXML
    private TextField youremail;
    @FXML
    private PasswordField Newpasswd;
    @FXML
    private PasswordField verifypasswd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
   public void ResetPass(ActionEvent event) throws IOException, SQLException {
      
         if (Newpasswd.getText().length()<8 ) {
            JOptionPane.showMessageDialog(null, "Le mot de passe ne doit pas étre inferieur à 8 caractere");
            
        }else if (Newpasswd.getText().equals(verifypasswd.getText())==false){
            
            JOptionPane.showMessageDialog(null, "Les deux mots de passe ne sont pas identiques !");
            
        } else {   
             cnx = Connexion.getInstance().getConnection(); 
             Statement st = cnx.createStatement();
             st.executeUpdate("update client set password ='"+  cryptage(Newpasswd.getText())+"' where login = '"+youremail.getText()+"'" );
           JOptionPane.showMessageDialog(null, "Votre nouveau mot de passe est validé vous allez "
                   + "automatiquement accéder à la page de  connexion!");
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Connexion.fxml"));
            Parent root = loader.load();
           Reset.getScene().setRoot(root);
           } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }}
        
    }
    
 public String cryptage(String pass) 
   {
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
            System.out.print(ex.getMessage());
        
        }return "" ; 
   }
 
  @FXML
   public void annuler(ActionEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Connexion.fxml"));
            Parent root = loader.load();
            annuler.getScene().setRoot(root);
           } catch (IOException ex) {
System.out.print(ex.getMessage());        }
    }

    

   

   
    
}
