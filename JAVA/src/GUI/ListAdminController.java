/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Entite.Admin;
import Entite.PDFAdmin;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import Service.AdminService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import utils.Connexion;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import mail.mail;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ListAdminController implements Initializable {

    @FXML
    private TextField nommodif;
    @FXML
    private TextField prenommodif;
    @FXML
    private TextField nummodif;
    @FXML
    private TextField loginmodif;
    @FXML
    private TextField passwordmodif;
    @FXML
    private Button btnM;
    @FXML
    private TableView<Admin> admintable;
    @FXML
    private TableColumn<Admin,Integer> id;
    @FXML
    private TableColumn<Admin,String> nom;
    @FXML
    private TableColumn<Admin,String> prenom;
    @FXML
    private TableColumn<Admin,String> numtel;
    @FXML
    private TableColumn<Admin,String> login;
    
    AdminService sp = new AdminService () ;
    @FXML
    private TextField searchAdminByID;
    @FXML
    private Button admin;
    @FXML
    private Button facture;
    @FXML
    private Button deco;
    @FXML
    private Button parking;
    @FXML
    private Button fourriere;
    @FXML
    private Button amande;
    @FXML
    private Button btnS;
    @FXML
    private Button btnA;
    @FXML
    private TableColumn<Admin, String> password;
    private final ObservableList<Admin> data = FXCollections.observableArrayList();
    private ObservableList<Admin> listM;
    ObservableList<Admin> list = FXCollections.observableArrayList();
    AdminService sc = new AdminService();
    @FXML
    private TextField idmodif;
    @FXML
    private Button pdf;
    @FXML
    private Button eval;
    @FXML
    private Button btntrier;
    @FXML
    private ComboBox<String> choisir;
    @FXML
    private Button btnmail;
    @FXML
    private Button user;
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
            rs = cnx.createStatement().executeQuery("SELECT * FROM Admin");
        } catch (SQLException ex) {
            Logger.getLogger(ListAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
                list.add(new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

                admintable.setItems(list);
                admintable.refresh();

            }
        } catch (SQLException ex) {
            Logger.getLogger(ListAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(ListAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
       choisir.getItems().addAll(
         
       "id",
       "nom",
       "prenom",
       "numtel",
       "login" );
    }    
    public void afficher () throws SQLException {
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        numtel.setCellValueFactory(new PropertyValueFactory<>("Numtel"));
        login.setCellValueFactory(new PropertyValueFactory<>("Login"));
        password.setCellValueFactory(new PropertyValueFactory<>("Password"));
        
        
        
        admintable.setItems(list);
        UpdateTable();
        
        FilteredList<Admin> filteredData = new FilteredList<>(list, b -> true);

        searchAdminByID.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(Admin -> {
        // If filter text is empty, display all persons.

        if (newValue == null || newValue.isEmpty()) {
        return true;

        }

        // Compare first name and last name of every person with filter text.
        String lowerCaseFilter = newValue.toLowerCase();

        // if (reservation.getClient().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
        // return true; // Filter matches first name.
          if (Admin.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
        return true; // Filter matches last name.
        }else if (Admin.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
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
        SortedList<Admin> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        //  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(admintable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        admintable.setItems(sortedData);
                admintable.setRowFactory(tv -> new TableRow<Admin>() {


                  });
            }
    @FXML
    private void handleModifyButtonAction(ActionEvent event) throws SQLException {
        if(nom_evalide()&& prenom_evalide() && numtel_evalide()&& login_evalide() ) {
         try {
            UpdateTable();
            Connection cnx = Connexion.ConnectDb();
            String value1 = nommodif.getText();
            int value7 = Integer.parseInt(idmodif.getText());

            String value2 = prenommodif.getText();
            String value3 = nummodif.getText();
            
            String value4 = loginmodif.getText();
            String value5 = passwordmodif.getText();
            String sql = "update admin set nom= '" + value1 + "',prenom= '" + value2 + "',numtel= '"
                    + value3 + "',login= '" + value4 + "', password= '" + value5 + "' where id='" + value7 + "' ";
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.execute();
             TrayNotification tray = new TrayNotification();
        tray.setTitle("Modifier un Admin");
        tray.setMessage("Modifié avec succès!");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();
            UpdateTable();
            admintable.refresh();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
         
    }
    }

 @FXML
    private void generatePdf(ActionEvent event) {
        PDFAdmin pd=new PDFAdmin();
        try{
        pd.GeneratePdf("ListAdmin");
            System.out.println("validé");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());    
}
    }
    @FXML
    private void deco(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Accueil.fxml"));
          
            Parent root = loader.load();
            deco.getScene().setRoot(root);
                 } catch (IOException ex) {
    }
    }


    @FXML
    private void Amande(ActionEvent event) {try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Accueil.fxml"));
          
            Parent root = loader.load();
            amande.getScene().setRoot(root);
                 } catch (IOException ex) {
    }
    }


    @FXML
    private void handledeleteButtonAction(ActionEvent event) throws SQLException {
        
      
        TableColumn.CellEditEvent edittedcell = null;
        Admin x = gettempEvenement(edittedcell);
        
        UpdateTable();

        if (x != null) {

            int i = x.getId();
         
            AdminService cat = new AdminService();
            boolean s = cat.supprimer(i);
            UpdateTable();

            if (s == true) {
                 TrayNotification tray = new TrayNotification();
        tray.setTitle("Supprimer un nouveau Admin");
        tray.setMessage("Supprimé avec succès!");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();
                admintable.refresh();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selectionner un champ SVP");
            alert.showAndWait();
        }
         admintable.refresh();
    }
    public Admin gettempEvenement(TableColumn.CellEditEvent edittedCell) {
        Admin test = admintable.getSelectionModel().getSelectedItem();
        return test;
        }

    public void UpdateTable() throws SQLException {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        numtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        listM = Connexion.getDataEvenement();

        admintable.setItems(listM);
    }
    
    @FXML
    private void handleAddButtonAction(ActionEvent event) throws SQLException {
      if(nom_evalide()&& prenom_evalide() && numtel_evalide()&& login_evalide() ) {
        
       
        AdminService sc =new  AdminService();       
        Admin c = new Admin(nommodif.getText(),prenommodif.getText(), nummodif.getText(),loginmodif.getText(),passwordmodif.getText());
        sc.ajouter(c);
        admintable.refresh();
        
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Ajouter un nouveau Admin");
        tray.setMessage("Ajout avec succès!");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();
                        
         afficher();
         UpdateTable();
         admintable.refresh();
        
        }
        
    }
    

    @FXML
    private void getSelected(MouseEvent event) {
    
        int index = admintable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        idmodif.setText(id.getCellData(index).toString());
        nommodif.setText((String) nom.getCellData(index));
        prenommodif.setText((String) prenom.getCellData(index));
        nummodif.setText((String) numtel.getCellData(index));
        loginmodif.setText((String) login.getCellData(index));
        passwordmodif.setText((String) password.getCellData(index)); 
    
    }
    private boolean nom_evalide(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(nommodif.getText());
        if(m.find() && m.group().equals(nommodif.getText())&& (!nommodif.getText().isEmpty()) && (nommodif.getLength()> 2)){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Type valide !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un nom valide !");
                alert.showAndWait();
           
            return false;            
        }
     }
    
    private boolean prenom_evalide(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(prenommodif.getText());
        if(m.find() && m.group().equals(prenommodif.getText())&& (!prenommodif.getText().isEmpty()) && (prenommodif.getLength()> 2)){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Type valide !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un prenom valide !");
                alert.showAndWait();
           
            return false;            
        }
     }
    
    private boolean numtel_evalide(){
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(nummodif.getText());
        if(m.find() && m.group().equals(nummodif.getText())&& (!nummodif.getText().isEmpty()) && (nummodif.getLength()== 8)){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Type valide !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un num valide !");
                alert.showAndWait();
           
            return false;            
        }
     }
    private boolean login_evalide(){
        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
                    Pattern pattern = Pattern.compile(masque);
                    Matcher controler = pattern.matcher(loginmodif.getText());
                    if(controler.find() && controler.group().equals(loginmodif.getText())&& (!loginmodif.getText().isEmpty())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Type valide !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer une adresse mail valide !");
                alert.showAndWait();
           
            return false;
     }
    }
  
    

    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    
    
    
               
         // table.setHeaderRows(1);
//         ObservableList<Produit> list = (ObservableList<Produit>) ps.afficherProduit();
 //  colids.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("id"));
//      colprix.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("prix"));
//      colquan.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("quantite"));
//      date_exp.setCellValueFactory(new PropertyValueFactory<Produit,Date>("date_exp"));
//      coltype.setCellValueFactory(new PropertyValueFactory<Produit,String>("type"));
//     
//        tab_s.setItems(list);
       
//        table.addCell("");
//   table.addCell("1.1");
//             table.addCell("1.2");
//               table.addCell("2.1");
//                table.addCell("2.2");
//        
  

    

    @FXML
    private void evaluation(ActionEvent event) {
    
    
        /////////////// NAVISGATION ENTRE PAGES :::::::::::
             try {

         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));  ///interface li mechilha///
         
              Parent root = loader.load();
               ///controlleur mta3 l interface li mechilha//
             // FXMLController controller = loader.getController();
              eval.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ///controlleur mta3 ena fin///
            Logger.getLogger(ListAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Trier(ActionEvent event) {
        
         AdminService us = new AdminService() ; 
       List<Admin> facture = us.Trier((choisir.getSelectionModel().getSelectedItem()).toString());
            try{
            list = FXCollections.observableArrayList(facture);
            admintable.setItems(list);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          
           // numtelC.setCellValueFactory(new PropertyValueFactory<>("numtel"));
           prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            // descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
            //emailC.setCellValueFactory(new PropertyValueFactory<>("email"));
           // methodepaimentC.setCellValueFactory(new PropertyValueFactory<>("methodepaiment"));
            numtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
            login.setCellValueFactory(new PropertyValueFactory<>("login"));
           } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
 @FXML
    private void  Envoimail (ActionEvent event) throws MessagingException  {
      mail.SendMail("eyabenazzouna@gmail.com");
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
    }
    }

    
    
    
    
   
}

    

