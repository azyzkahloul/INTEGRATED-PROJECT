package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Bourguiba
 */
public class SupprimerUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
     public void deleteButton(){
        String query = "DELETE FROM client WHERE id =" + tfid1.getText() + "";
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                 alert.setContentText("Voulez vous supprimer ce client? ");
                 alert.show();
        executeQuery(query);
        showClient();
    }
    
}
