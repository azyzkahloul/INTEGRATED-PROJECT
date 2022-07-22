/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Client;
import Service.ServiceClient;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Bourguiba
 */
public class ModifierUserController implements Initializable {

    @FXML
    private AnchorPane modifierboutique;
    @FXML
    private Button ModifierBoutique;
    private Button retour;
    private TableColumn<Client, String> tprenom;
    @FXML
    private TableColumn<Client, String> tnom;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField tel;
    @FXML
    private TextField mat;
    @FXML
    private TextField email;
    @FXML
    private TextField mdp;
    @FXML
    private TextField statut;
    ServiceClient sc =new ServiceClient();
    @FXML
    private TableColumn<Client, String> tpren;
    @FXML
    private TableColumn<Client, String> ttel;
    @FXML
    private TableColumn<Client, String> tmat;
    @FXML
    private TableColumn<Client, String> temail;
    @FXML
    private TableColumn<Client, String> tmdp;
    @FXML
    private TableColumn<Client, String> tstat;
    @FXML
    private TableView<Client> tableuser;
    @FXML
    private TextField tfid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showClient();
    }  
    
    public ObservableList<Client> getClientList(){
        ObservableList<Client> clientList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query= "SELECT * FROM client";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Client clients;
            while (rs.next()) {
            clients = new Client (rs.getString("nom"),rs.getString("prenom"),rs.getString("numtel"),rs.getString("voitmat"),
           rs.getString("login"),rs.getString("password"),rs.getString("statut"));
            
            clientList.add(clients);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return clientList;
    }
    
    public void showClient(){
        ObservableList<Client> list = getClientList();
         
        tnom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
        tpren.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
        ttel.setCellValueFactory(new PropertyValueFactory<Client, String>("numtel"));
        tmat.setCellValueFactory(new PropertyValueFactory<Client, String>("voitmat"));
        temail.setCellValueFactory(new PropertyValueFactory<Client, String>("login"));
        tmdp.setCellValueFactory(new PropertyValueFactory<Client, String>("password"));
        tstat.setCellValueFactory(new PropertyValueFactory<Client, String>("statut"));
       
        tableuser.setItems(list);
    }
     public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidevweb","root","");
            return conn;
            
        }catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
        private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
                    
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


    private void retour(ActionEvent event) {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ProfilUser.fxml"));
          
            Parent root = loader.load();
            retour.getScene().setRoot(root);
                 } catch (IOException ex) {
    }
    }
    
    @FXML
     public void updateRecord() {

        String query = "UPDATE  client SET nom = '" + tnom.getText()+"', prenom = '" + tprenom.getText() +"', numtel= '" + ttel.getText()+ "', voitmat = '" + tmat.getText() + 
                "', login= '" + temail.getText() + "', password= '" + tmdp.getText() +"', statut= '" +tstat.getText()
                + "' WHERE id = " +Integer.parseInt(tfid.getText())  + "";
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                 alert.setContentText("Mis à jour effectué avec succès");
                 alert.show();
        executeQuery(query);
        showClient();
    
    }
     

   
    
}
