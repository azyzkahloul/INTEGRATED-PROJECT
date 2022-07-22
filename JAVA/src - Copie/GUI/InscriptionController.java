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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import utils.Connexion;

/**
 * FXML Controller class
 *
 * @author Bourguiba
 */
public class InscriptionController implements Initializable {

    @FXML
    private AnchorPane inscr;
    private Button terminer;
    @FXML
    private VBox pageinscription;
    @FXML
    private Label invalid;
    @FXML
    private TextField Nom;
    @FXML
    private TextField Prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField num;
    @FXML
    private TextField matricule;
    @FXML
    private SplitMenuButton Role;
    @FXML
    private MenuItem client;
    @FXML
    private MenuItem agentservice;
    @FXML
    private PasswordField mdp;
    @FXML
    private PasswordField Cmdp;
    @FXML
    private Label generatedString;
    @FXML
    private TextField text;
    @FXML
    private SplitMenuButton question;
    @FXML
    private MenuItem q1;
    @FXML
    private MenuItem q2;
    @FXML
    private MenuItem q3;
    @FXML
    private MenuItem q4;
    @FXML
    private MenuItem q5;
    @FXML
    private TextField Reponse;
    @FXML
    private Button inscription;
    @FXML
    private Button back;
    Connection cnx;
    ServiceClient uti = new ServiceClient ();
    @FXML
    private TextField statut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void terminer(ActionEvent event) throws SQLException {
        //if (String.valueOf(codevalidation())!=Code.getText()) {
           
         //invalid.setText("");
         cnx = Connexion.getInstance().getConnection();
         //if (inscription()== true) {
             Client u = new Client() ;
             u.setNom(Nom.getText());
             u.setPrenom(Prenom.getText());
             //u.setRole(Role.getText());
             // System.out.println(Role.getText());
             u.setNumtel(num.getText());
             u.setVoitmat(matricule.getText());
             u.setLogin(email.getText());
             u.setPassword(cryptage(mdp.getText()));
             u.setStatut(statut.getText());
             //u.setReponse(Reponse.getText());
             //u.setQuestion(question.getText());
             
             uti.ajout(u);
             JOptionPane.showMessageDialog(null, "Bienvenue Chez Instapark "+Nom.getText()+" "+Prenom.getText());
             try {
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Connexion.fxml"));
                 Parent root = loader.load();
                 inscription.getScene().setRoot(root);
             } catch (IOException ex) {
                 System.out.print(ex.getMessage());
                 
             }
             
        /* }else {
             Alert alert = new Alert (Alert.AlertType.ERROR);
             alert.setContentText("Votre code est invalid ");
             alert.show();
         }*/
    }

  

    @FXML
    private void client(ActionEvent event) {
    }

    @FXML
    private void client(Event event) {
    }

    @FXML
    private void agentservice(ActionEvent event) {
    }

    @FXML
    private void agentservice(Event event) {
    }

    @FXML
    private void Role(ActionEvent event) {
    }

    @FXML
    private void q1(ActionEvent event) {
    }

    @FXML
    private void q1(Event event) {
    }

    @FXML
    private void q2(ActionEvent event) {
    }

    @FXML
    private void q2(Event event) {
    }

    @FXML
    private void q3(ActionEvent event) {
    }

    @FXML
    private void q3(Event event) {
    }

    @FXML
    private void q4(ActionEvent event) {
    }

    @FXML
    private void q4(Event event) {
    }

    @FXML
    private void q5(ActionEvent event) {
    }

    @FXML
    private void q5(Event event) {
    }

    @FXML
    private void Sexe(ActionEvent event) {
    }

   

    @FXML
    private void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Connexion.fxml"));
            Parent root = loader.load();
            inscription.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
    }

    private boolean verifemail(String email) {
       String e ="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
       Pattern em = Pattern.compile(e, Pattern.CASE_INSENSITIVE);
       Matcher matcher = em.matcher(email);
        return matcher.find();          }

   /* private int codevalidation() {
        int borneInf =1000 ;
       int borneSup = 9999;
       Random random = new Random();
       int nb;
       nb = borneInf+random.nextInt(borneSup-borneInf);
       System.out.println(""+nb);
       return nb;
    }*/

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
            System.out.println(ex.getMessage());
        }return "" ; 
    }

    private boolean inscription() {
        boolean verif=true;
         if (email.getText().isEmpty() == false
                && Nom.getText().isEmpty() == false  
                && Prenom.getText().isEmpty() == false
                && matricule.getText().isEmpty() == false && num.getText().isEmpty() == false  
                && mdp.getText().isEmpty() == false
                && Reponse.getText().isEmpty() == false && question.getText().isEmpty() == false
                && Cmdp.getText().isEmpty() == false && text.getText().isEmpty() == false
                && num.getText().isEmpty() == false) {
      
           if  (Nom.getText().equals(Prenom.getText())){
                 invalid.setText("Nom et prenom doivent être différents "); }   
           else if (verifemail(email.getText())== false)
        {
            invalid.setText("Cette Email est invalid ");
        }  else if (mdp.getText().length()<8 ) {
            invalid.setText("Le mot de passe ne doit pas étre inferieur à 8 caractere");}
     
        else if((mdp.getText().equals(Cmdp.getText()))==false ) {
           
     invalid.setText("Les mots de passe ne sont pas identiques");}
         
            else if ((num.getText().length()!=8))
           {
              invalid.setText("Numéro est invalide");
            }
           
                }else {
                 verif=false;
                 Alert alert = new Alert (Alert.AlertType.ERROR);
                 alert.setContentText("Remplir Tous les cases s'il vous plaît ");
                 alert.show();
                 }
         return verif;    }
    
}
