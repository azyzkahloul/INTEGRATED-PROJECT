/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entite.Parking;
import utils.Connexion;
/**
 *
 * @author malek
 */
public class SeviceParking {
     private PreparedStatement pst ;
     private Connection cnx;
     private ResultSet res ;
    private Statement ste;


    public SeviceParking() {
        cnx=Connexion.getInstance().getConnection();
    }
    
   public void ajouterParking(Parking f){
   String query="INSERT INTO parking(nomp,nbplace,adresse,description) values (?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, f.getNomp());
            ste.setString(2, f.getNbplace());

            ste.setString(3, f.getAdresse());
            ste.setString(4, f.getDescription());
            ste.executeUpdate();
            System.out.println("Parking Ajouté!!");
        } catch (SQLException ex) {
            System.out.println("");
        }
    
}
    public List<Parking> afficherParking(){
    List<Parking>  parkings = new ArrayList<>();
    String sql = "select * from parking";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Parking f = new Parking();
                f.setId(rs.getInt("id"));
                                f.setNomp(rs.getString("nomp"));
                f.setNbplace(rs.getString("nbplace"));
                

                  f.setAdresse(rs.getString("adresse"));
               
                    f.setDescription(rs.getString("description"));
               
               
                parkings.add(f);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return  parkings;
}
    public void Ajouter(Parking t) {
         try {
            String sql="insert into parking (nomp ,nbplace,adresse , description)"
                    + "values('"+t.getNomp()+"','"+t.getNbplace()+"','"+t.getAdresse()+"','"+t.getDescription()+"')";
            Statement ste =cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println(" parking Ajouté");
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

}
    public boolean supprimer1(int id) {
        boolean supp = true;
        try {
            String sql = "delete from   parking where id ='"+id+"'";    
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println(" Parking supprimé");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            supp = false;
        }  
        return supp;
    }
  public void modifierParking(Parking R,int id) {
            try {
            String requete = " update parking set `nomp`=? , `nbplace`=? , `adresse`=?  `description`=?    where id= '"+id+"'" ;
            pst = cnx.prepareStatement(requete);
            pst.setString(1, R.getNomp());
            pst.setString(2,R.getNbplace());
            pst.setString(3,R.getAdresse());
            pst.setString(4,R.getDescription());
            // pst.setInt(5,R.getId_rec());
            pst.executeUpdate();
            System.out.println(" parrk a ete modifiee!!");
        } catch (SQLException ex) {
System.out.println(ex.getMessage());
        }

    }
   
}





