/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author TRABELSI Amaal
 */
public class MethodeController implements Initializable {

    @FXML
    private AnchorPane methode;
    @FXML
    private Button parmail;
    @FXML
    private Button parq;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
    @FXML
    private void parmail(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Parmail.fxml"));
            Parent root = loader.load();
            parmail.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());   
           }
        
        
    }

    @FXML
    private void parq(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Rest.fxml"));
            Parent root = loader.load();
            parq.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());   
        }
        
    }
   
     

    @FXML
    private void back(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Connexion.fxml"));
          
            Parent root = loader.load();
            back.getScene().setRoot(root);
                 } catch (IOException ex) {
    }
    }
    
  
    
}
