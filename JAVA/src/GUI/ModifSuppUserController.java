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
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import utils.Connexion;

/**
 * FXML Controller class
 *
 * @author Faycel
 */
public class ModifSuppUserController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfpren;
    @FXML
    private TextField tftel;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfmdp;
    @FXML
    private TextField tfstat;
    @FXML
    private TableColumn<String, Client> colnom;
    @FXML
    private TableColumn<String, Client> colprenom;
    @FXML
    private TableColumn<String, Client> coltel;
    @FXML
    private TableColumn<String, Client> colmat;
    @FXML
    private TableColumn<String, Client> colemail;
    @FXML
    private TableColumn<String, Client> colmdp;
    @FXML
    private TableColumn<String, Client> colmstat;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private Button admin;
    @FXML
    private Button facture;
    @FXML
    private Button deco;
    @FXML
    private Button user;
    @FXML
    private Button parking;
    @FXML
    private Button fourriere;
    @FXML
    private Button amande;
    @FXML
    private Button rec;
    @FXML
    private Button retour;
     ObservableList<Client> list = FXCollections.observableArrayList();
    private ObservableList<Client> listM;
    @FXML
    private TableView<Client> tvClient;
    private TextField tfimm;
    @FXML
    private TextField tfmat;

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
        try {
            while (rs.next()) {
                list.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));

               tvClient.setItems(list);
              tvClient.refresh();

            }
        } catch (SQLException ex) {
            Logger.getLogger(ModifSuppUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AfficherClient();
    }   
    
     // private boolean controleDeSaisi() {

     //   if (!Pattern.matches("[A-z]*", tfnom.getText())) {
          //  showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez l'objet ! ");
          //  tfnom.requestFocus();
           // tfnom.selectEnd();
          //  return false;
        //} else {
           // if (!Pattern.matches("[1-50]*", tfNb.getText())) {
             //   showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la description ! ");
              //  tfNb.requestFocus();
              //  tfNb.selectEnd();
              //  return false;
           // }

       // }
       // return true;
   // }
    
    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
     private void UpdateTable() {
      
   
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        colmat.setCellValueFactory(new PropertyValueFactory<>("voitmat"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("login"));
        colmdp.setCellValueFactory(new PropertyValueFactory<>("password"));
        colmstat.setCellValueFactory(new PropertyValueFactory<>("statut"));

        listM = Connexion.getDataClient();

      tvClient.setItems(listM);
    }

    private void AfficherClient() {
        
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        colmat.setCellValueFactory(new PropertyValueFactory<>("voitmat"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("login"));
        colmdp.setCellValueFactory(new PropertyValueFactory<>("password"));
        colmstat.setCellValueFactory(new PropertyValueFactory<>("statut"));


        UpdateTable();
        tvClient.setItems(list);
    }
    public Client getClient(TableColumn.CellEditEvent edittedCell) {
       Client test = tvClient.getSelectionModel().getSelectedItem();
        return test;
   
    }

  
        

    @FXML
    private void Gereradmin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListAdmin.fxml"));
            Parent root = loader.load();
            admin.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
    }

    @FXML
    private void GererFacture(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/FXMLDocument.fxml"));
            Parent root = loader.load();
            facture.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
    }

    @FXML
    private void deco(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Connexion.fxml"));
          
            Parent root = loader.load();
            
            deco.getScene().setRoot(root);
                 } catch (IOException ex) {
                     System.out.print(ex.getMessage());
    }
    }

    @FXML
    private void GererUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ProfilUser.fxml"));
            Parent root = loader.load();
            user.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
    }

   

    @FXML
    private void GererFourriere(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Fourriere.fxml"));
            Parent root = loader.load();
            fourriere.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
    }

    @FXML
    private void Amande(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ProfilUser.fxml"));
            Parent root = loader.load();
            amande.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
    }

    @FXML
    private void GererReclam(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Reclamation.fxml"));
            Parent root = loader.load();
            rec.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Accueil.fxml"));
          
            Parent root = loader.load();
            retour.getScene().setRoot(root);
                 } catch (IOException ex) {
    } 
        
    }

    @FXML
    private void GererParking(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Parking.fxml"));
            Parent root = loader.load();
            rec.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
    }

    
    private void onModif(ActionEvent event) {
          
   
 try {
            UpdateTable();
            Connection cnx = Connexion.ConnectDb();
           
      String value1 = tfnom.getText();
      String value2 = tfpren.getText();
      String value3 = tftel.getText();
      String value4 = tfmat.getText();
      String value5 = tfemail.getText();
      String value6 = tfmdp.getText();
      String value7 = tfstat.getText();
      
      
         
            
            
            String sql ="update client set nom= '" + value1 + "',prenom= '" + value2 + "',numtel= '"+ value3 + "' ,voitmat= '"+ value4 +"' ,login= '"+ value5 +"' ,password= '"+ value6 +"' ,statut= '"+ value7 + "'" ;
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Mis à jour");

            alert.showAndWait();
            UpdateTable();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @FXML
    private void onSupprime(MouseEvent event) {
        TableColumn.CellEditEvent edittedcell = null;
        Client f = getClient(edittedcell);
        UpdateTable();

        if (f != null) {

            int i = f.getId_client();
            ServiceClient cat = new ServiceClient();

             boolean s = cat.supprimer(i);
            UpdateTable();

             
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Utilisateur Supprimée");
               // addNotifications("park supprimée avec succes", "Park supprimé");
                alert.showAndWait();
               tvClient.refresh();
            

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        
    }
    
    }

    @FXML
    private void onModif(MouseEvent event) {
      try {
            UpdateTable();
            Connection cnx = Connexion.ConnectDb();
           
      String value1 = tfnom.getText();
      String value2 = tfpren.getText();
      String value3 = tftel.getText();
      String value4 = tfmat.getText();
      String value5 = tfemail.getText();
      String value6 = tfmdp.getText();
      String value7 = tfstat.getText();

         
            
            
            String sql ="update client set nom= '" + value1 + "',prenom= '" + value2 + "',numtel= '"+ value3 + "' ,voitmat= '"+ value4 +"' ,login= '"+ value5 +"' ,password= '"+ value6 + "',statut= '"+ value7 + "'";
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("UPDATED");

            alert.showAndWait();
            UpdateTable();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }  
    

    private void onSupprime(ActionEvent event) {
        TableColumn.CellEditEvent edittedcell = null;
        Client f = getClient(edittedcell);
        UpdateTable();

        if (f != null) {

            int i = f.getId_client();
            ServiceClient cat = new ServiceClient();

             boolean s = cat.supprimer(i);
            UpdateTable();

             
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("park Supprimée");
               // addNotifications("park supprimée avec succes", "Park supprimé");
                alert.showAndWait();
               tvClient.refresh();
            

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        
    }
    
    




    }

    
}

