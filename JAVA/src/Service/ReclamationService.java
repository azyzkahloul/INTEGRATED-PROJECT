
package Service;

import Entite.Reclamation;
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

public class ReclamationService {
     private PreparedStatement pst ;
            private Connection cnx;
                private ResultSet res ;
                 private Statement ste;

    public ReclamationService() {
        cnx=Connexion.getInstance().getConnection();
    }
    
 public void ajouterReclamation(Reclamation p){
       try {
            String sql="insert into Reclamation (objet,description,etat)"
                    + "values('"+p.getObjet()+"','"+p.getDescription()+"','"+p.getEtat()+"')";
            Statement ste =cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Reclamation Ajoutée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 }  
     
public void ajouterReclamation2(Reclamation p){
   String query="insert into Reclamation (objet,description,etat) values (?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, p.getObjet());
            ste.setString(2, p.getDescription());
            ste.setString(3, p.getEtat());
            ste.executeUpdate();
            System.out.println("Reclamation Ajoutée!!");
        } catch (SQLException ex) {
            System.out.println("");
        }
    
} 

public List<Reclamation> afficherReclamation(){
    List<Reclamation> reclamations = new ArrayList<>();
    String sql = "select * from reclamation";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Reclamation p = new Reclamation();
                p.setObjet(rs.getString(2));
                p.setDescription(rs.getString("description"));
                p.setEtat(rs.getString("etat"));
                reclamations.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return reclamations;
}

    public boolean supprimerRec(int id_rec) {
        boolean supp = true;
        try {
            String sql = "delete from  reclamation where id_rec ='"+id_rec+"'";    
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println(" Reclamation supprimée");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            supp = false;
        }  
        return supp;
    }
   
    public void modifierRec(Reclamation R,int id_rec) {
            try {
            String requete = " update reclamation set `objet`=? , `description`=? , `etat`=?   where id= '"+id_rec+"'" ;
            pst = cnx.prepareStatement(requete);
            pst.setString(1, R.getObjet());
            pst.setString(2,R.getDescription());
            pst.setString(3,R.getEtat());
            // pst.setInt(5,R.getId_rec());
            pst.executeUpdate();
            System.out.println(" Reclamation a ete modifiee!!");
        } catch (SQLException ex) {
System.out.println(ex.getMessage());
        }

    }
    
}
