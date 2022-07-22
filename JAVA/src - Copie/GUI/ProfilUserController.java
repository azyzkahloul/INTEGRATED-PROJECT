/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Bourguiba
 */
public class ProfilUserController implements Initializable {

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
    private Button rec;
    @FXML
    private Button deco;
    @FXML
    private Button consulterprofilbtn;
    @FXML
    private Button gererUserbtn;
    @FXML
    private Button modifierUserbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    @FXML
    private void consulterprofilbtn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ProfilUser.fxml"));
          
            Parent root = loader.load();
            consulterprofilbtn.getScene().setRoot(root);
                 } catch (IOException ex) {
    }
    }

    @FXML
    private void gererlisteuserbtn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AfficheListUser.fxml"));
          
            Parent root = loader.load();
            gererUserbtn.getScene().setRoot(root);
                 } catch (IOException ex) {
    }
    }

    @FXML
    private void modifierprofilbtn(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ModifierUser.fxml"));
          
            Parent root = loader.load();
            modifierUserbtn.getScene().setRoot(root);
                 } catch (IOException ex) {
    }
    }

    
    
}
