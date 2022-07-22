
package Service;

import Entite.Reponse;
import Entite.Reponse;
import utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReponseService {
     private PreparedStatement pst ;
            private Connection cnx;
                private ResultSet res ;
                 private Statement ste;

    public ReponseService() {
        cnx=Connexion.getInstance().getConnection();
    }
    
 public void ajouterReponse(Reponse p){
       try {
            String sql="insert into Reponse (rps)"
                    + "values('"+p.getRps()+"')";
            Statement ste =cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Reponse Ajoutée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 }  
     
public void ajouterReponse2(Reponse p){
   String query="insert into Reponse (rps) values (?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, p.getRps());
            ste.executeUpdate();
            System.out.println("Reponse Ajoutée!!");
        } catch (SQLException ex) {
            System.out.println("");
        }
    
} 

public List<Reponse> afficherReponse(){
    List<Reponse> reponses = new ArrayList<>();
    String sql = "select * from reponse";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Reponse p = new Reponse();
                p.setRps(rs.getString("rps"));
                reponses.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return reponses;
}

    public boolean supprimerRep(int id) {
        boolean supp = true;
        try {
            String sql = "delete from  reponse where id ='"+id+"'";    
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println(" Reponse supprimée");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            supp = false;
        }  
        return supp;
    }
   
    public void modifierRep(Reponse R,int id) {
            try {
            String requete = " update reponse set `rps`=? where id= '"+id+"'" ;
            pst = cnx.prepareStatement(requete);
            pst.setString(1, R.getRps());
            // pst.setInt(5,R.getId_rec());
            pst.executeUpdate();
            System.out.println(" Reponse a ete modifiee!!");
        } catch (SQLException ex) {
System.out.println(ex.getMessage());
        }

    }
    
}
