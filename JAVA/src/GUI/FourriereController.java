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
import Service.ServiceFourriere;
import Entite.Fourriere;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import utils.Connexion;
import org.controlsfx.control.Notifications;
import mail.mail;
import static utils.Connexion.ConnectDb;


/**
 * FXML Controller class
 *
 * @author Faycel
 */
public class FourriereController implements Initializable {

    @FXML
    private AnchorPane InterfaceFour;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfnbplace;
    @FXML
    private TextField tflatitude;
    @FXML
    private TextField tflongtitude;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodify;
    @FXML
    private Button btnsup;
    @FXML
    private TableView<Fourriere> tabFour;
    @FXML
    private TableColumn<Fourriere, Integer> colid;
    @FXML
    private TableColumn<Fourriere, String> colnom;
    @FXML
    private TableColumn<Fourriere, String> colnomb;
    @FXML
    private TableColumn<Fourriere, String> colalt;
    @FXML
    private TableColumn<Fourriere, String> collong;
    ObservableList<Fourriere> list = FXCollections.observableArrayList();
    private ObservableList<Fourriere> listM;
    
    @FXML
    private Button btnmail;
    @FXML
    private TextField tfrecherche;
    @FXML
    private Button btnrecherche;
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
    private Button admin1;
    @FXML
    private Button facture1;
    @FXML
    private Button deco1;
    @FXML
    private Button user1;
    @FXML
    private Button parking1;
    @FXML
    private Button fourriere1;
    @FXML
    private Button amande1;
    @FXML
    private Button rec1;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection cnx = Connexion.getInstance().getConnection();
        ResultSet rs = null;
        try {
            rs = cnx.createStatement().executeQuery("SELECT * FROM fourriere");
        } catch (SQLException ex) {
            Logger.getLogger(FourriereController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
                list.add(new Fourriere(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

                tabFour.setItems(list);
                tabFour.refresh();

            }
        } catch (SQLException ex) {
            Logger.getLogger(FourriereController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AfficherFourriere();
    }    
    private boolean controleDeSaisi() {

        if (!Pattern.matches("[A-z]*", tfnom.getText())) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez l'objet ! ");
            tfnom.requestFocus();
            tfnom.selectEnd();
            return false;
        } else {
            if (!Pattern.matches("[1-50]*", tfnbplace.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la description ! ");
                tfnbplace.requestFocus();
                tfnbplace.selectEnd();
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

    private void onModif(ActionEvent event) {
           try {
            UpdateTable();
            Connection cnx = Connexion.ConnectDb();
           
      String value1 = tfnom.getText();
      String value2 = tfnbplace.getText();
      String value3 = tflatitude.getText();
      String value4 = tflongtitude.getText();

         
            
            
            String sql ="update fourriere set nomf= '" + value1 + "',nbplace= '" + value2 + "',flatitude= '"+ value3 + "' ,flongtitude= '"+ value4 + "'" ;
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
           ServiceFourriere fs = new ServiceFourriere ();
          
            fs.ajouterFourriere2(new Fourriere(tfnom.getText(), tfnbplace.getText(), tflatitude.getText(),tflongtitude.getText()));

            colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colnom.setCellValueFactory(new PropertyValueFactory<>("nomf"));
            colnomb.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
            colalt.setCellValueFactory(new PropertyValueFactory<>("flatitude"));
            collong.setCellValueFactory(new PropertyValueFactory<>("flongtitude"));
            addNotifications("Fourriere ajoutée avec succes", "Fourriere ajoutée");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Fourriere Ajoutée");

            alert.showAndWait();
            UpdateTable();
            tabFour.refresh();
  //      }
    
    }
    @FXML
    private void onSupprime(MouseEvent event) {
         TableColumn.CellEditEvent edittedcell = null;
        Fourriere f = gettempFourriere(edittedcell);
        UpdateTable();

        if (f != null) {

            int i = f.getId();
            ServiceFourriere cat = new ServiceFourriere();

             boolean s = cat.supprimer1(i);
            UpdateTable();

             
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Fourriere Supprimée");
                addNotifications("Fourriere supprimée avec succes", "Fourriere supprimée");
                alert.showAndWait();
                tabFour.refresh();
            

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }

    }

    public Fourriere gettempFourriere(TableColumn.CellEditEvent edittedCell) {
        Fourriere test = tabFour.getSelectionModel().getSelectedItem();
        return test;
    
    }

    private void AfficherFourriere() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nomf"));
        colnomb.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
        colalt.setCellValueFactory(new PropertyValueFactory<>("flatitude"));
        collong.setCellValueFactory(new PropertyValueFactory<>("flongtitude"));


        UpdateTable();
        tabFour.setItems(list);
    }

    @FXML
    private void UpdateTable() {
      
       colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nomf"));
        colnomb.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
        colalt.setCellValueFactory(new PropertyValueFactory<>("flatitude"));
        collong.setCellValueFactory(new PropertyValueFactory<>("flongtitude"));

        listM = Connexion.getDataFourriere();

        tabFour.setItems(listM);
    }

    @FXML
    private void Recherche(ActionEvent event) {
      
            FilteredList<Fourriere> filteredData = new FilteredList<>(list, b -> true);

                tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(fourriere -> {


            if (newValue == null || newValue.isEmpty()) {
            return true;}


               String lowerCaseFilter = newValue.toLowerCase();
                 if (fourriere.getNomf().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                     return true; // Filter matches last name.
                 }
                 else if (fourriere.getNbplace().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                  return true; // Filter matches first name.
                  
                 }
              else  
             return false; // Does not match.
              });
            });
                
             SortedList<Fourriere> sortedData = new SortedList<>(filteredData);


             sortedData.comparatorProperty().bind(tabFour.comparatorProperty());

             tabFour.setItems(sortedData);
             tabFour.setRowFactory(tv -> new TableRow<Fourriere>() {
           
                
              // @Override
                protected void updateItem(Fourriere item, boolean empty){
               
                }
          });
         

    }
    @FXML
    private void  Envoimail (ActionEvent event) throws MessagingException {
      mail.SendMail("eyabenazzouna@gmail.com");

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
                .hideAfter(Duration.seconds(360))
                .position(Pos.BOTTOM_RIGHT);

        notificationBuilder.showInformation();
    }

    

    @FXML
    private void Gereradmin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ProfilUser.fxml"));
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
    private void GererAmande(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ProfilUser.fxml"));
            Parent root = loader.load();
            amande.getScene().setRoot(root);
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
               System.out.print(ex.getMessage());
        }
    }

    @FXML
    private void onModif(MouseEvent event) {
         try {
            UpdateTable();
            Connection cnx = Connexion.ConnectDb();
           
      String value1 = tfnom.getText();
      String value2 = tfnbplace.getText();
      String value3 = tflatitude.getText();
      String value4 = tflongtitude.getText();

         
            
            
            String sql ="update fourriere set nomf= '" + value1 + "',nbplace= '" + value2 + "',flatitude= '"+ value3 + "' ,flongtitude= '"+ value4 + "'" ;
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
        ServiceFourriere fs = new ServiceFourriere ();
          
            fs.ajouterFourriere2(new Fourriere(tfnom.getText(), tfnbplace.getText(), tflatitude.getText(),tflongtitude.getText()));

            colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colnom.setCellValueFactory(new PropertyValueFactory<>("nomf"));
            colnomb.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
            colalt.setCellValueFactory(new PropertyValueFactory<>("flatitude"));
            collong.setCellValueFactory(new PropertyValueFactory<>("flongtitude"));
            addNotifications("Fourriere ajoutée avec succes", "Fourriere ajoutée");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Fourriere Ajoutée");

            alert.showAndWait();
            UpdateTable();
            tabFour.refresh();
  //      }
    
    
    }

    @FXML
    private void Amande(ActionEvent event) {
    }

   
}
    
    

