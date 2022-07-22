/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entite.Facture;
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
/**
 *
 * @author malek
 */
public class ServiceFacture {
    
   Connection cnx;

    public ServiceFacture () {
        cnx=Connexion.getInstance().getConnection();
    }
    
   public void ajouterFacture(Facture f){
   String query="INSERT INTO facture(nbheure,pu,total,dateentrer) values (?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, f.getNbheure());
            ste.setString(2, f.getPu());

            ste.setString(3, f.getTotal());
            ste.setString(4, f.getDateentrer());
            ste.executeUpdate();
            System.out.println("Facture Ajouté!!");
        } catch (SQLException ex) {
            System.out.println("");
        }
    
}
    public List<Facture> afficherFacture(){
    List<Facture>  factures = new ArrayList<>();
    String sql = "select * from facture";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Facture f = new Facture();
                f.setId_facture(rs.getInt("id_facture"));
                                f.setNbheure(rs.getString("nbheure"));
                f.setPu(rs.getString("pu"));
                

                  f.setTotal(rs.getString("total"));
               
                    f.setDateentrer(rs.getString("dateentrer"));
               
               
                factures.add(f);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return  factures;
}
    public void Ajouter(Facture t) {
         try {
            String sql="insert into facture (nbheure ,pu,total , dateentrer)"
                    + "values('"+t.getNbheure()+"','"+t.getPu()+"','"+t.getTotal()+"','"+t.getDateentrer()+"')";
            Statement ste =cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println(" Facture Ajouté");
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

}
    public boolean supprimer1(int id) {
        boolean supp = true;
        try {
            String sql = "delete from   facture where id_facture='"+id+"'";    
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println(" facture supprimé");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            supp = false;
        }  
        return supp;
    }
    public boolean modifer( Facture f,int id) {
         boolean modifier= true;
        String sql = "update  facture set nbheure='"+f.getNbheure()+"', pu='"+f.getPu()+"',total='"+f.getTotal()+"',dateentrer='"+f.getDateentrer()+"'"
             
                + "where id ='"+id+"'";
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println("Facture modifié!!");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            modifier = false;
        }
        return modifier;
    }
   
}
