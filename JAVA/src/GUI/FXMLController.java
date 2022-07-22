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
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FXMLController implements Initializable {

    @FXML
    private Rating jaimrating;
    @FXML
    private Rating jaimprationg;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAcrion(ActionEvent event) {
        
        System.out.println("Les j'aime données par l'utilisateur :" + jaimrating.getRating());
        System.out.println("Les j'aime pas données par l'utilisateur :" + jaimprationg.getRating());
    }

    @FXML
    private void retour(ActionEvent event) {
        
         try {

         FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAdmin.fxml"));  
         
              Parent root = loader.load();
               
              ListAdminController controller = loader.getController();
              retour.getScene().setRoot(root);
            
        } catch (IOException ex) {
            
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
