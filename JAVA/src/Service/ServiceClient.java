

package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entite.Client;
import utils.Connexion;
/**
 *
 * @author malek
 */
public class ServiceClient {
    
  Connection cnx;

    public ServiceClient() {
        cnx=Connexion.getInstance().getConnection();
    }
    
   public void ajouterClient(Client f){
   String query="INSERT INTO client(nom,prenom,numtel,voitmat,login,password,statut) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, f.getNom());
            ste.setString(2, f.getPrenom());

            ste.setString(3, f.getNumtel());
            ste.setString(4, f.getVoitmat());
            ste.setString(5, f.getLogin());
            ste.setString(6, f.getPassword());
            ste.setString(7, f.getStatut());
            ste.executeUpdate();
            System.out.println("Client Ajouté!!");
        } catch (SQLException ex) {
            System.out.println("");
        }
   
}
    public List<Client> afficherClient(){
    List<Client>  clients = new ArrayList<>();
    String sql = "select * from client";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Client f = new Client();
                f.setId_client(rs.getInt("id_client"));
                    f.setNom(rs.getString("nom"));
                f.setPrenom(rs.getString("prenom"));
                

                  f.setNumtel(rs.getString("numtel"));
               
                    f.setVoitmat(rs.getString("voitmat"));
                    f.setLogin(rs.getString("login"));
                    f.setPassword(rs.getString("password"));
                    f.setStatut(rs.getString("statut"));
               
               
                clients.add(f);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return  clients;
}
    public void Ajouter(Client t) {
         try {
            String sql="insert into client (nom ,prenom,numtel , voitmat,login,password,statut)"
                    + "values('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getNumtel()+"','"+t.getVoitmat()+"','"+t.getLogin()+"','"+t.getPassword()+"','"+t.getStatut()+"')";
            Statement ste =cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println(" Client Ajouté");
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

}
    public boolean supprimer(int id_client) {
        boolean supp = true;
        try {
            String sql = "delete from client where id_client ='"+id_client+"'";    
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println(" client supprimé");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            supp = false;
        }  
        return supp;
    }
    public boolean modifer(Client f , int id_client) {
         boolean modifier= true;
        String sql = "update  client set nom='"+f.getNom()+"', prenom='"+f.getPrenom()+"',numtel='"+f.getNumtel()+"',voitmat='"+f.getVoitmat()+"',login='"+f.getLogin()+"',password='"+f.getPassword()+"',statut='"+f.getStatut()+"'"
             
                + "where id_client ='"+id_client+"'";
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println("client modifié!!");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            modifier = false;
        }
        return modifier;
    }
   
}





