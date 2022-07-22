/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Client;
import Entite.PDFUSER;
import Service.ServiceClient;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Bourguiba
 */
public class AfficheListUserController implements Initializable {

    @FXML
    private Button btnadd;
    @FXML
    private ImageView imgretour;
    @FXML
    private Button retour;
    @FXML
    private TableColumn<Client, String> actPren;
    @FXML
    private TableColumn<Client, String> actTel;
    @FXML
    private TableColumn<Client, String> actmat;
    @FXML
    private TableColumn<Client, String> actemail;
    @FXML
    private TableColumn<Client, String> actstat;
    @FXML
    private TableColumn<Client, String> actNom;
    @FXML
    private Button pdf;
    @FXML
    private TableView<Client> tableuser;

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
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Accueil.fxml"));
          
            Parent root = loader.load();
            retour.getScene().setRoot(root);
                 } catch (IOException ex) {
    }
    }

    @FXML
    private void GoToAddClient(ActionEvent event) {
      /*   Client u = new Client();
       String query="insert into user(nom,prenom,numtel,voitmat,login,password) values (?,?,?,?,?,?)";
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
        }*/
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
           rs.getString("login"),rs.getString("statut"));
            
            clientList.add(clients);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return clientList;
    }
    
    @FXML
    public void showClient(){
        ObservableList<Client> list = getClientList();
         
        actNom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
        actPren.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
        actTel.setCellValueFactory(new PropertyValueFactory<Client, String>("numtel"));
        actmat.setCellValueFactory(new PropertyValueFactory<Client, String>("voitmat"));
        actemail.setCellValueFactory(new PropertyValueFactory<Client, String>("login"));
        actstat.setCellValueFactory(new PropertyValueFactory<Client, String>("statut"));
       
        tableuser.setItems(list);
    }
    
    @FXML
    public void generatePdf(ActionEvent event) {
        PDFUSER pd=new PDFUSER();
        try{
        pd.GeneratePdf("List des utilisateurs");
            System.out.println("Validé");
        } catch (Exception ex) {
            Logger.getLogger(ServiceClient.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
