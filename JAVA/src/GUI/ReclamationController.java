/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Reclamation;
import Service.ReclamationService;
import utils.Connexion;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.activation.DataSource;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ReclamationController implements Initializable {

    @FXML
    private AnchorPane InterfaceRec;
    @FXML
    private TextField tfdes;
    @FXML
    private TextField tfobj;
    @FXML
    private TextField tfetat;
    @FXML
    private TextField tfRech;
    @FXML
    private TableView<Reclamation> tabRes;
    //@FXML
    //private TableColumn<Reclamation, Integer> colId;
    @FXML
    private TableColumn<Reclamation, String> colobj;
    @FXML
    private TableColumn<Reclamation, String> coldes;
    @FXML
    private TableColumn<Reclamation, String> coletat;
    @FXML
    private Button btAjouter;
    @FXML
    private Button btmodif;
    @FXML
    private Button btsupp;
    ObservableList<Reclamation> list = FXCollections.observableArrayList();
    private ObservableList<Reclamation> listM;
    @FXML
    private Button btnexport;
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
            rs = cnx.createStatement().executeQuery("SELECT * FROM reclamation");
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));

                tabRes.setItems(list);
                tabRes.refresh();

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            AfficherReclamation();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Reclamation> getReclamationList(){
        ObservableList<Reclamation> reclamationList= FXCollections.observableArrayList(); 
        Connection cnx = Connexion.getInstance().getConnection();
        String query = "SELECT * FROM `reclamation` ";
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs= st.executeQuery(query);
            Reclamation reclamation;
            while (rs.next()){
                reclamation = new Reclamation(rs.getString("objet"),rs.getString("description"),rs.getString("etat")) ;
                reclamationList.add(reclamation);
                
            }
        } catch (Exception ex){
            ex.printStackTrace();
    }
        return reclamationList;
    }
    

    @FXML
    private void onAjout(MouseEvent event) {
       if (controleDeSaisi()) {
            ReclamationService sp = new ReclamationService();

            sp.ajouterReclamation(new Reclamation(tfobj.getText(),tfdes.getText(),tfetat.getText()));

           // colId.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
            colobj.setCellValueFactory(new PropertyValueFactory<>("objet"));
            coldes.setCellValueFactory(new PropertyValueFactory<>("description"));
            coletat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         

           TrayNotification tray = new TrayNotification();
        tray.setTitle("Reclamation Ajoutée");
        tray.setMessage("Ajout avec succes");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();
            UpdateTable();
            tabRes.refresh();
        }
    }

    public void AfficherReclamation() throws SQLException {
       // colId.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
        colobj.setCellValueFactory(new PropertyValueFactory<>("objet"));
        coldes.setCellValueFactory(new PropertyValueFactory<>("description"));
        coletat.setCellValueFactory(new PropertyValueFactory<>("etat"));

        UpdateTable();
        tabRes.setItems(list);
        
 FilteredList<Reclamation> filteredData = new FilteredList<>(list, b -> true);

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
          });
    }

    private void UpdateTable() {
       // colId.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
        colobj.setCellValueFactory(new PropertyValueFactory<>("objet"));
        coldes.setCellValueFactory(new PropertyValueFactory<>("description"));
        coletat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        listM = Connexion.getDataReclamation();

        tabRes.setItems(listM);
    }
        
 private boolean controleDeSaisi() {

        if (!Pattern.matches("[A-z]*", tfobj.getText())) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez l'objet ! ");
            tfobj.requestFocus();
            tfobj.selectEnd();
            return false;
        } else {
            if (!Pattern.matches("[A-z]*", tfdes.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la description ! ");
                tfdes.requestFocus();
                tfdes.selectEnd();
                return false;
            }

        }
        return true;
    }

 public Reclamation gettempReclamation(TableColumn.CellEditEvent edittedCell) {
        Reclamation test = tabRes.getSelectionModel().getSelectedItem();
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
    private void onModif(MouseEvent event) throws SQLException{
        Connection cnx = Connexion.getInstance().getConnection();
       TableColumn.CellEditEvent edittedcell = null;
        Reclamation x = gettempReclamation(edittedcell);
        UpdateTable();
            
           if (x != null) {

            int i = x.getId_rec();
            ReclamationService cat = new ReclamationService();

            cat.modifierRec(x,i);
            UpdateTable();
            //int value5 = Integer.parseInt(tfid.getText());         
            String value1= tfobj.getText();
            String value2= tfdes.getText();
            String value3= tfetat.getText();
           
            
            String sql ="update reclamation set objet= '" + value1 + "',description= '" + value2 + "',etat= '"+ value3 + "'"+" where id_rec='" + i + "' ";
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("UPDATED");

            alert.showAndWait();
            UpdateTable();
            tabRes.refresh();
            
    }else {     Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("veuillez selectionnez une reclamation afin de la modifier");
            alert.showAndWait();
        }
    }

    @FXML
    private void onSupp(MouseEvent event) throws SQLException{
        TableColumn.CellEditEvent edittedcell = null;
        Reclamation x = gettempReclamation(edittedcell);
        UpdateTable();

        if (x != null) {

            int i = x.getId_rec();
            ReclamationService cat = new ReclamationService();

              boolean s = cat.supprimerRec(i);
            UpdateTable();

            if (s == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Reclamation supprimer");

                alert.showAndWait();
                tabRes.refresh();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("veuillez selectionnez une reclamation afin de la supprimer");
            alert.showAndWait();
        }
    }

    @FXML
    private void exporter(ActionEvent event) throws IOException {
         Workbook workbook = new HSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet("sample");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < tabRes.getColumns().size(); j++) {
            row.createCell(j).setCellValue(tabRes.getColumns().get(j).getText());
        }

        for (int i = 0; i < tabRes.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < tabRes.getColumns().size(); j++) {
                if(tabRes.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(tabRes.getColumns().get(j).getCellData(i).toString());
                }
                else {
                    row.createCell(j).setCellValue("");
                }  
            }
        }
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        workbook.write(fileOut);
        fileOut.close();
    }

    

   

    @FXML
    private void deco(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Connexion.fxml"));
            Parent root = loader.load();
            admin.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
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
    }}

    @FXML
    private void Amande(ActionEvent event) {
    }
    
}
