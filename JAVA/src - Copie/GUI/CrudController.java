/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Client;
import Entite.PDFUSER;
import Service.ServiceClient;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Bourguiba
 */
public class CrudController implements Initializable {

    private TextField tfid;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfnum;
    @FXML
    private TextField tfimma;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfmdp;
    @FXML
    private TableView<Client> tvclient;
    @FXML
    private TableColumn<Client, String> colnom;
    @FXML
    private TableColumn<Client, String> colpren;
    @FXML
    private TableColumn<Client, String> coltel;
    @FXML
    private TableColumn<Client, String> colimmat;
    @FXML
    private TableColumn<Client, String> colemail;
    @FXML
    private TableColumn<Client, String> colmdp;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TextField tfid1;
    @FXML
    private Button PDF;

    /**
     * Initializes the controller class.
     */
    @Override
      public void initialize(URL url, ResourceBundle rb) {
        showClient();
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
    
    @FXML
        public void handleButtonAction(ActionEvent event) {        
        
        if(event.getSource() == btnupdate){
            updateRecord();
        }else if(event.getSource() == btndelete){
            deleteButton();
        }
            
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
           rs.getString("login"),rs.getString("password"));
            
            clientList.add(clients);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return clientList;
    }
    
    public void showClient(){
        ObservableList<Client> list = getClientList();
         
        colnom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
        colpren.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
        coltel.setCellValueFactory(new PropertyValueFactory<Client, String>("numtel"));
        colimmat.setCellValueFactory(new PropertyValueFactory<Client, String>("voitmat"));
        colemail.setCellValueFactory(new PropertyValueFactory<Client, String>("login"));
        colmdp.setCellValueFactory(new PropertyValueFactory<Client, String>("password"));
       
        tvclient.setItems(list);
    }
     public void insertRecord(Client c){
        Client u = new Client();
       String query="insert into user(nom,prenom,numtel,voitmat,logine,password) values (?,?,?,?,?,?)";
        try {
            Connection cnx = getConnection();
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, u.getNom());
            ste.setString(2, u.getPrenom());
            ste.setString(3, u.getNumtel());
            ste.setString(4, u.getVoitmat());
            ste.setString(5, u.getLogin());
            ste.setString(6, u.getPassword());
            ste.executeUpdate();
            System.out.println("Client Ajoutée!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}

    public void updateRecord() {

        String query = "UPDATE  client SET nom = '" + tfnom.getText()+"', prenom = '" + tfprenom.getText() +"', numtel= '" + tfnum.getText()+ "', voitmat = '" + tfimma.getText() + 
                "', login= '" + tfemail.getText() + "', password= '" + tfmdp.getText() 
                + "' WHERE id = " +Integer.parseInt(tfid.getText())  + "";
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                 alert.setContentText("Mis à jour effectué avec succès");
                 alert.show();
        executeQuery(query);
        showClient();
    
    }
     
    
    public void deleteButton(){
        String query = "DELETE FROM client WHERE id =" + tfid1.getText() + "";
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                 alert.setContentText("Voulez vous supprimer ce client? ");
                 alert.show();
        executeQuery(query);
        showClient();
    }
    @FXML
    
    private void generatePdf(ActionEvent event) {
        PDFUSER pd=new PDFUSER();
        try{
        pd.GeneratePdf("listClient");
            System.out.println("validé");
        } catch (Exception ex) {
            Logger.getLogger(ServiceClient.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
