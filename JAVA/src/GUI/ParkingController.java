/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;



import Entite.PDFParking;
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
import Service.SeviceParking;
import Entite.Parking;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import static java.util.Collections.list;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import utils.Connexion;
import org.controlsfx.control.Notifications;
import mail.mail;
import static utils.Connexion.ConnectDb;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class ParkingController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfNb;
    @FXML
    private TextField tfad;
    @FXML
    private TextField tfd;
    @FXML
    private TableView<Parking> tvParkings;
    @FXML
    private TableColumn<Parking, String > colnomp;
    @FXML
    private TableColumn<Parking, String > colnb;
    @FXML
    private TableColumn<Parking, String > colad;
    @FXML
    private TableColumn<Parking, String > cold;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private Button PDF;
    @FXML
    private Button btnInsert;
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
    @FXML
    private TextField tfrecherche;
    @FXML
    private Button btnrecherche;
    ObservableList<Parking> list = FXCollections.observableArrayList();
    private ObservableList<Parking> listM;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Connection cnx = Connexion.getInstance().getConnection();
        ResultSet rs = null;
        try {
            rs = cnx.createStatement().executeQuery("SELECT * FROM parking");
        } catch (SQLException ex) {
            Logger.getLogger(ParkingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
                list.add(new Parking(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

                tvParkings.setItems(list);
                tvParkings.refresh();

            }
        } catch (SQLException ex) {
            Logger.getLogger(ParkingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AfficherParking();
        
 
    }    
    
   
      public ObservableList<Parking> getParkingList(){
        ObservableList<Parking> parkingList= FXCollections.observableArrayList(); 
        Connection cnx = Connexion.getInstance().getConnection();
        String query = "SELECT * FROM `parking` ";
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs= st.executeQuery(query);
            Parking parking;
            while (rs.next()){
                parking = new Parking(rs.getString("nomp"),rs.getString("nbplace"),rs.getString("adresse"),rs.getString("description")) ;
                parkingList.add(parking);
                
            }
        } catch (Exception ex){
            ex.printStackTrace();
    }
        return parkingList;
    }
    
    
    
    
    
    private boolean controleDeSaisi() {

        if (!Pattern.matches("[A-z]*", tfnom.getText())) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez l'objet ! ");
            tfnom.requestFocus();
            tfnom.selectEnd();
            return false;
        } else {
            if (!Pattern.matches("[1-50]*", tfNb.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la description ! ");
                tfNb.requestFocus();
                tfNb.selectEnd();
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

     
       
    

    private void onAjoute(ActionEvent event) {
// if (controleDeSaisi()) {
           SeviceParking fs = new SeviceParking();
          
            fs.ajouterParking(new Parking(tfnom.getText(), tfNb.getText(), tfad.getText(),tfd.getText()));

        
            colnomp.setCellValueFactory(new PropertyValueFactory<>("nomp"));
            colnb.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
            colad.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            cold.setCellValueFactory(new PropertyValueFactory<>("description"));
            addNotifications("Park ajouté avec succes", "park ajouté");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("park Ajouté");

            alert.showAndWait();
            UpdateTable();
            tvParkings.refresh();
  //      }
    
    }
    @FXML
    private void onSupprime(MouseEvent event) {
         TableColumn.CellEditEvent edittedcell = null;
        Parking f = getParking(edittedcell);
        UpdateTable();

        if (f != null) {

            int i = f.getId();
            SeviceParking cat = new SeviceParking();

             boolean s = cat.supprimer1(i);
            UpdateTable();

             
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("park Supprimée");
                addNotifications("park supprimée avec succes", "Park supprimé");
                alert.showAndWait();
               tvParkings.refresh();
            

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }

    }

    public Parking getParking(TableColumn.CellEditEvent edittedCell) {
       Parking test = tvParkings.getSelectionModel().getSelectedItem();
        return test;
    
    }

    private void AfficherParking() {
        
        colnomp.setCellValueFactory(new PropertyValueFactory<>("nomp"));
        colnb.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
        colad.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        cold.setCellValueFactory(new PropertyValueFactory<>("description"));


        UpdateTable();
        tvParkings.setItems(list);
    }

    private void UpdateTable() {
      
   
        colnomp.setCellValueFactory(new PropertyValueFactory<>("nomp"));
        colnb.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
        colad.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        cold.setCellValueFactory(new PropertyValueFactory<>("description"));

        listM = Connexion.getDataParking();

      tvParkings.setItems(listM);
    }

    @FXML
    private void Recherche(ActionEvent event) {
      
            FilteredList<Parking> filteredData = new FilteredList<>(list, b -> true);

                tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(parking -> {


            if (newValue == null || newValue.isEmpty()) {
            return true;}


               String lowerCaseFilter = newValue.toLowerCase();
                 if (parking.getNomp().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                     return true; // Filter matches last name.
                 }
                 else if (parking.getNbplace().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                  return true; // Filter matches first name.
                  
                 }
              else  
             return false; // Does not match.
              });
            });
                
             SortedList<Parking> sortedData = new SortedList<>(filteredData);

  
             
             
             
             sortedData.comparatorProperty().bind(tvParkings.comparatorProperty());

          tvParkings.setItems(sortedData);
           tvParkings.setRowFactory(tv -> new TableRow<Parking>() {
              protected void updateItem(Parking item, boolean empty){
               
                }
                
              // @Override
               
          });
         

    }
  
  
  
  
    @FXML
    private void generatePdf(ActionEvent event) {
        PDFParking pd=new PDFParking();
        try{
        pd.GeneratePdf("ListParking");
            System.out.println("validé");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());    
}
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/FXMLDocument.fxml"));
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
    private void onModif(MouseEvent event) throws SQLException {
         Connection cnx = Connexion.getInstance().getConnection();
       TableColumn.CellEditEvent edittedcell = null;
        Parking x = getParking(edittedcell);
        UpdateTable();
            
           if (x != null) {

            int i = x.getId();
          SeviceParking cat = new SeviceParking();

            cat.modifierParking(x,i);
            UpdateTable();
            //int value5 = Integer.parseInt(tfid.getText());         
            String value1= tfnom.getText();
            String value2= tfNb.getText();
            String value3= tfad.getText();
            String value4= tfd.getText();
            
            String sql ="update parking set nomp= '" + value1 + "',nbplace= '" + value2 + "',adresse= '"+ value3 + "'"+ "',description= '"+ value4 + "'"+" where id='" + i + "' ";
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("UPDATED");

            alert.showAndWait();
            UpdateTable();
            tvParkings.refresh();
            
    }else {     Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("veuillez selectionnez une reclamation afin de la modifier");
            alert.showAndWait();
        }
    }


    @FXML
    private void onAjoute(MouseEvent event) {
         SeviceParking fs = new SeviceParking();
          
            fs.ajouterParking(new Parking(tfnom.getText(), tfNb.getText(), tfad.getText(),tfd.getText()));

            colnomp.setCellValueFactory(new PropertyValueFactory<>("nomp"));
            colnb.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
            colad.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            cold.setCellValueFactory(new PropertyValueFactory<>("description"));
            addNotifications("Park ajouté avec succes", "park ajouté");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("park Ajouté");

            alert.showAndWait();
            UpdateTable();
            tvParkings.refresh();
  //      }
    
    }
}
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
    
    
