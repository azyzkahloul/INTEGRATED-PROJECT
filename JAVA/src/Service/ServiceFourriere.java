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
import Entite.Fourriere;
import utils.Connexion;

/**
 *
 * @author Faycel
 */
public class ServiceFourriere {
      Connection cnx;

    public ServiceFourriere() {
        cnx=Connexion.getInstance().getConnection();
    }
    
    public void ajouterFourriere2(Fourriere f){
   String query="INSERT INTO fourriere(nomf,nbplace,flatitude,flongtitude) values (?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, f.getNomf());
            ste.setString(2, f.getNbplace());

            ste.setString(3, f.getFlatitude());
            ste.setString(4, f.getFlongtitude());
            ste.executeUpdate();
            System.out.println("Fourriere Ajoutée!!");
        } catch (SQLException ex) {
            System.out.println("");
        }
    
}
    public List<Fourriere> afficherFourriere(){
    List<Fourriere> fourrieres = new ArrayList<>();
    String sql = "select * from fourriere";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Fourriere f = new Fourriere();
                f.setId(rs.getInt("id"));
                                f.setNomf(rs.getString("nomf"));
                f.setNbplace(rs.getString("nbplace"));
                

                  f.setFlatitude(rs.getString("flatitude"));
               
                    f.setFlongtitude(rs.getString("flongtitude"));
               
               
                fourrieres.add(f);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return fourrieres;
}
    public void Ajouter(Fourriere t) {
         try {
            String sql="insert into fourriere (nomf ,nbplace, flatitude , flongtitude)"
                    + "values('"+t.getNomf()+"','"+t.getNbplace()+"','"+t.getFlatitude()+"','"+t.getFlongtitude()+"')";
            Statement ste =cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Fourriere  Ajoutée");
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

}
    public boolean supprimer1(int id) {
        boolean supp = true;
        try {
            String sql = "delete from  fourriere where id ='"+id+"'";    
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println(" Fourriere supprimée");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            supp = false;
        }  
        return supp;
    }
    public boolean modifer(Fourriere f,int id) {
         boolean modifier= true;
        String sql = "update fourriere set nomf='"+f.getNomf()+"', nbplace='"+f.getNbplace()+"',flatitude='"+f.getFlatitude()+"',flongtitude='"+f.getFlongtitude()+"'"
             
                + "where id ='"+id+"'";
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println("Fourriere modifiée!!");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            modifier = false;
        }
        return modifier;
    }
}
