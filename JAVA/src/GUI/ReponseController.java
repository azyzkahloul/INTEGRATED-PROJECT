/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Reponse;
import Service.ReponseService;
import java.io.IOException;
import utils.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.activation.DataSource;
import javax.swing.JOptionPane;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/*
 * FXML Controller class
 *
 * @author user
 */
public class ReponseController implements Initializable {

    @FXML
    private TextArea tfrps;
    @FXML
    private TableView<Reponse> tabRep;
    //@FXML
    //private TableColumn<Reponse, Integer> colId;
    @FXML
    private TableColumn<Reponse, String> colrps;
    @FXML
    private Button btAjouter1;
    @FXML
    private Button btmodif1;
    @FXML
    private Button btsupp1;
    ObservableList<Reponse> list = FXCollections.observableArrayList();
    private ObservableList<Reponse> listM;
    @FXML
    private AnchorPane interfaceRep;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Connection cnx = Connexion.getInstance().getConnection();
        ResultSet rs = null;
        try {
            rs = cnx.createStatement().executeQuery("SELECT * FROM reponse");
        } catch (SQLException ex) {
            Logger.getLogger(ReponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
                list.add(new Reponse(rs.getInt(1), rs.getString(2)));

                tabRep.setItems(list);
                tabRep.refresh();

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            AfficherReponse();
        } catch (SQLException ex) {
            Logger.getLogger(ReponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Reponse> getReponseList(){
        ObservableList<Reponse> reponseList= FXCollections.observableArrayList(); 
        Connection cnx = Connexion.getInstance().getConnection();
        String query = "SELECT * FROM `reponse` ";
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs= st.executeQuery(query);
            Reponse reponse;
            while (rs.next()){
                reponse = new Reponse(rs.getString("rps")) ;
                reponseList.add(reponse);
                
            }
        } catch (Exception ex){
            ex.printStackTrace();
    }
        return reponseList;
    }
    

    @FXML
    private void onAjout1(MouseEvent event) {
       if (controleDeSaisi()) {
            ReponseService sp = new ReponseService();

            sp.ajouterReponse(new Reponse(tfrps.getText()));

           // colId.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
            colrps.setCellValueFactory(new PropertyValueFactory<>("rps"));
         

           TrayNotification tray = new TrayNotification();
        tray.setTitle("Reponse Ajoutée");
        tray.setMessage("Ajout avec succes");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();
            UpdateTable();
            tabRep.refresh();
        }
    }

    public void AfficherReponse() throws SQLException {
       // colId.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
        colrps.setCellValueFactory(new PropertyValueFactory<>("rps"));

        UpdateTable();
        tabRep.setItems(list);
        
 /*FilteredList<Reponse> filteredData = new FilteredList<>(list, b -> true);

tfRech.textProperty().addListener((observable, oldValue, newValue) -> {
filteredData.setPredicate(reclamation -> {
// If filter text is empty, display all persons.

if (newValue == null || newValue.isEmpty()) {
return true;
                                       
}

// Compare first name and last name of every person with filter text.
String lowerCaseFilter = newValue.toLowerCase();

// if (reservation.getClient().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
// return true; // Filter matches first name.
                                         if (reclamation.getObjet().toLowerCase().indexOf(lowerCaseFilter) != -1) {
return true; // Filter matches last name.
}else if (reclamation.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
return true; // Filter matches first name.
//if (reservation.getDuree().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//return true; // Filter matches first name.
//}else if (reservation.getInfos_Supp().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//return true; // Filter matches first name.
}
    else  
    return false; // Does not match.
});
});
                // 3. Wrap the FilteredList in a SortedList.
SortedList<Reclamation> sortedData = new SortedList<>(filteredData);

// 4. Bind the SortedList comparator to the TableView comparator.
//  Otherwise, sorting the TableView would have no effect.
sortedData.comparatorProperty().bind(tabRes.comparatorProperty());

// 5. Add sorted (and filtered) data to the table.
tabRes.setItems(sortedData);
        tabRes.setRowFactory(tv -> new TableRow<Reclamation>() {
           
              // @Override
                protected void updateItem(Reclamation item, boolean empty){
               
                }
          });*/
    }

    private void UpdateTable() {
       // colId.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
        colrps.setCellValueFactory(new PropertyValueFactory<>("rps"));
        listM = Connexion.getDataReponse();

        tabRep.setItems(listM);
    }
        
 private boolean controleDeSaisi() {

        if (!Pattern.matches("[A-z]*", tfrps.getText())) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez votre reponse ! ");
            tfrps.requestFocus();
            tfrps.selectEnd();
            return false;
        } else
        return true;
    }

 public Reponse gettempReponse(TableColumn.CellEditEvent edittedCell) {
        Reponse test = tabRep.getSelectionModel().getSelectedItem();
        return test;
    }
 
    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    

    @FXML
    private void onModif1(MouseEvent event) throws SQLException{
        Connection cnx = Connexion.getInstance().getConnection();
       TableColumn.CellEditEvent edittedcell = null;
        Reponse x = gettempReponse(edittedcell);
        UpdateTable();
            
           if (x != null) {

            int i = x.getId();
            ReponseService cat = new ReponseService();

            cat.modifierRep(x,i);
            UpdateTable();
            //int value5 = Integer.parseInt(tfid.getText());         
            String value1= tfrps.getText();
           
            
            String sql ="update reponse set rps= '" + value1 +"'"+" where id='" + i + "' ";
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("UPDATED");

            alert.showAndWait();
            UpdateTable();
            tabRep.refresh();
            
    }else {     Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("veuillez selectionnez une reponse afin de la modifier");
            alert.showAndWait();
        }
    }

    @FXML
    private void onSupp1(MouseEvent event) throws SQLException{
        TableColumn.CellEditEvent edittedcell = null;
        Reponse x = gettempReponse(edittedcell);
        UpdateTable();

        if (x != null) {

            int i = x.getId();
            ReponseService cat = new ReponseService();

              boolean s = cat.supprimerRep(i);
            UpdateTable();

            if (s == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Reponse supprimer");

                alert.showAndWait();
                tabRep.refresh();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("veuillez selectionnez une reponse afin de la supprimer");
            alert.showAndWait();
        }
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
    private void deco(ActionEvent event) {
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
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Accueil.fxml"));
          
            Parent root = loader.load();
            retour.getScene().setRoot(root);
                 } catch (IOException ex) {
    }
    }
    
}