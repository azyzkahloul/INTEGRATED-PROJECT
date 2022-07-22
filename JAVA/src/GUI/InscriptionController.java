/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Client;
import Entite.Parking;
import Service.ServiceClient;
import Service.SeviceParking;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
    private PasswordField mdp;
    private PasswordField Cmdp;
    private TextField text;
    private SplitMenuButton question;
    private TextField Reponse;
    @FXML
    private Button inscription;
    @FXML
    private Button back;
    Connection cnx;
    ServiceClient uti = new ServiceClient();
    private TextField statut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Connection cnx = Connexion.getInstance().getConnection();
        ResultSet rs = null;
        try {
            rs = cnx.createStatement().executeQuery("SELECT * FROM client");
        } catch (SQLException ex) {
            Logger.getLogger(ModifSuppUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
    }    
     public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
  

    @FXML
    private void GoToAddClient(ActionEvent event) {
      ServiceClient fs = new ServiceClient();
          
            fs.Ajouter(new Client(Nom.getText(), Prenom.getText(),num.getText(), matricule.getText(), email.getText(), mdp.getText()));

        
            
            //addNotifications("Park ajouté avec succes", "park ajouté");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Utilisateur Ajouté");

            alert.showAndWait();

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
    
    
}
