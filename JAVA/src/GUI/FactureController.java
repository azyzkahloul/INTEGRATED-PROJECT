/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Service.ServiceFacture;
import Entite.Facture;
import Entite.Reclamation;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import utils.Connexion;
import org.controlsfx.control.Notifications;
import mail.mail;
import static utils.Connexion.ConnectDb;
/**
 * FXML Controller class
 *
 * @author malek
 */
public class FactureController implements Initializable {

    @FXML
    private TextField tfNbh;
    @FXML
    private TextField tfPu;
    @FXML
    private TextField tfTot;
    @FXML
    private TextField tfDate;
    @FXML
    private TableView<Facture> tvfacture;
    @FXML
    private TableColumn<Facture, String> colNbh;
    @FXML
    private TableColumn<Facture, String> colPu;
    @FXML
    private TableColumn<Facture, String> colTot;
    @FXML
    private TableColumn<Facture, String> colDate;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button pdf;
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
    ObservableList<Facture> list = FXCollections.observableArrayList();
    private ObservableList<Facture> listM;
    @FXML
    private TextField tfrecherche;
    @FXML
    private Button btnrecherche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection cnx = Connexion.getInstance().getConnection();
        ResultSet rs = null;
        try {
            rs = cnx.createStatement().executeQuery("SELECT * FROM facture ");
        } catch (SQLException ex) {
            Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
                list.add(new Facture(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

               tvfacture.setItems(list);
              tvfacture.refresh();

            }
        } catch (SQLException ex) {
            Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AfficherFacture();
        
    }    
    
    
 
    
    private boolean controleDeSaisi() {

        if (!Pattern.matches("[A-z]*", tfNbh.getText())) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez l'objet ! ");
            tfNbh.requestFocus();
           tfNbh.selectEnd();
            return false;
        } else {
            if (!Pattern.matches("[1-50]*", tfPu.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la description ! ");
                tfPu.requestFocus();
               tfPu.selectEnd();
                return false;
            }

        }
        return true;
    }
    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }

    @FXML
    private void onModif(ActionEvent event) {
           try {
            UpdateTable();
            Connection cnx = Connexion.ConnectDb();
           
      String value1 = tfNbh.getText();
      String value2 = tfPu.getText();
      String value3 = tfTot.getText();
      String value4 = tfDate.getText();

         
            
            String sql ="update facture set nbheure= '" + value1 + "',pu= '" + value2 + "',total= '"+ value3 + "' ,dateentrer= '"+ value4 + "'" ;
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
       
    

    private void onAjoute(ActionEvent event) {
// if (controleDeSaisi()) {
           ServiceFacture fs = new ServiceFacture();
          
            fs.ajouterFacture(new Facture(tfNbh.getText(), tfPu.getText(),tfTot.getText(),tfDate.getText()));

        
            colNbh.setCellValueFactory(new PropertyValueFactory<>("nbheure"));
            colPu.setCellValueFactory(new PropertyValueFactory<>("pu"));
            colTot.setCellValueFactory(new PropertyValueFactory<>("total"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("dateentrer"));
            addNotifications(" facture a ajouté avec succes", "facture ajouté");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("fact Ajouté");

            alert.showAndWait();
            UpdateTable();
            tvfacture.refresh();
  //      }
    
    }
    @FXML
    private void onSupprime(MouseEvent event) {
         TableColumn.CellEditEvent edittedcell = null;
       Facture f = getFacture(edittedcell);
        UpdateTable();

        if (f != null) {

            int i = f.getId_facture();
            ServiceFacture cat = new ServiceFacture();

             boolean s = cat.supprimer1(i);
            UpdateTable();

             
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("facture Supprimé");
                addNotifications("facture supprimé avec succes", "facture supprimé");
                alert.showAndWait();
               tvfacture.refresh();
            

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }

    }

    public Facture getFacture(TableColumn.CellEditEvent edittedCell) {
       Facture test = tvfacture.getSelectionModel().getSelectedItem();
        return test;
    
    }

    private void AfficherFacture() {
        
        
        colNbh.setCellValueFactory(new PropertyValueFactory<>("nbheure"));
        colPu.setCellValueFactory(new PropertyValueFactory<>("pu"));
        colTot.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateentrer"));


        UpdateTable();
        tvfacture.setItems(list);
    }

    private void UpdateTable() {
      
   
        colNbh.setCellValueFactory(new PropertyValueFactory<>("nbheure"));
        colPu.setCellValueFactory(new PropertyValueFactory<>("pu"));
        colTot.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateentrer"));

        listM = Connexion.getDataFacture();

      tvfacture.setItems(listM);
    }

    @FXML
    private void Recherche(ActionEvent event) {
      
            FilteredList<Facture> filteredData = new FilteredList<>(list, b -> true);

                tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(facture -> {


            if (newValue == null || newValue.isEmpty()) {
            return true;}


               String lowerCaseFilter = newValue.toLowerCase();
                 if (facture.getNbheure().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                     return true; // Filter matches last name.
                 }
                 else if (facture.getPu().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                  return true; // Filter matches first name.
                  
                 }
              else  
             return false; // Does not match.
              });
            });
                
             SortedList<Facture> sortedData = new SortedList<>(filteredData);

  
             
             
             
             sortedData.comparatorProperty().bind(tvfacture.comparatorProperty());

          tvfacture.setItems(sortedData);
           tvfacture.setRowFactory(tv -> new TableRow<Facture>() {
              protected void updateItem(Facture item, boolean empty){
               
                }
                
              // @Override
               
          });
         

    }
  
        private void addNotifications(String title, String content) {

        if (null != content) {
            if (content.length() > 50) {
                content = content.substring(0, 49) + "......";
            }
        }
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(content)
               // .hideAfter(Duration.seconds(360))
                .position(Pos.BOTTOM_RIGHT);

        notificationBuilder.showInformation();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Facture.fxml"));
            Parent root = loader.load();
            facture.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
    }

    @FXML
    private void deco(ActionEvent event) {try {
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
    private void GererParking(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Parking.fxml"));
            Parent root = loader.load();
            parking.getScene().setRoot(root);
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
    private void onModif(MouseEvent event) {
       try {
            UpdateTable();
            Connection cnx = Connexion.ConnectDb();
           
      String value1 = tfNbh.getText();
      String value2 = tfPu.getText();
      String value3 = tfTot.getText();
      String value4 = tfDate.getText();

         
            
            String sql ="update facture set nbheure= '" + value1 + "',pu= '" + value2 + "',total= '"+ value3 + "' ,dateentrer= '"+ value4 + "'" ;
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


    @FXML
    private void onAjoute(MouseEvent event) {
        ServiceFacture fs = new ServiceFacture();
          
            fs.ajouterFacture(new Facture(tfNbh.getText(), tfPu.getText(),tfTot.getText(),tfDate.getText()));

        
            colNbh.setCellValueFactory(new PropertyValueFactory<>("nbheure"));
            colPu.setCellValueFactory(new PropertyValueFactory<>("pu"));
            colTot.setCellValueFactory(new PropertyValueFactory<>("total"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("dateentrer"));
            addNotifications(" facture a ajouté avec succes", "facture ajouté");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("fact Ajouté");

            alert.showAndWait();
            UpdateTable();
            tvfacture.refresh();
 
    
    }
    }    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


