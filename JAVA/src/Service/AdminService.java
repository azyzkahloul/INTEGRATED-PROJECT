/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author LENOVO
 */
import Entite.Admin;
import java.sql.*;
import utils.Connexion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdminService {

    private Connection con = Connexion.getInstance().getConnection();
    private Statement ste;
     private PreparedStatement pst ;
    private ResultSet res ;


    public AdminService() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ajouter(Admin p) throws SQLException {
         try {
        String req = "INSERT INTO `admin` (`nom`, `prenom`,`numtel`,`login`,`password`)"+"VALUES('"+
                  p.getNom() + "', '" + p.getPrenom()+ "', '" + p.getNumtel() + "', '" +p.getLogin()+"', '"+p.getPassword()+"');";
        ste.executeUpdate(req);
        System.out.println("Admin Ajoutée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 
    public List<Admin> afficherAdmin() throws SQLException {
        List<Admin> list = new ArrayList<>();
try {
        ResultSet rs = ste.executeQuery("select * from admin");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String numtel = rs.getString("numtel");    
            String login = rs.getString("login");
            String password = rs.getString("password");
            
            
            Admin p;
            p = new Admin(id,nom,prenom,numtel,login,password);
            list.add(p);
        }
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
      
    

    }
  public boolean supprimer(int id) {
        boolean supp = true;
        try {
            String sql = "delete from admin where id ='"+id+"'";    
            Statement stm = con.createStatement();
            stm.executeUpdate(sql);
            System.out.println("admin supprimée");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            supp = false;
        }  
        return supp;
    }

  public boolean modifier(Admin t, int id) {
      boolean m= true;
        
          String req = "update admin set nom='"+t.getNom()+"' , prenom='"+t.getPrenom()+"' , numtel='"+t.getNumtel()+"' , login='"+t.getLogin()+"' , Password='"+t.getPassword()+"'" +" where id='"+t.getId()+"'";
           try {
            Statement ps = con.createStatement(); 
            ps.executeUpdate(req);
            System.out.println("Admin modifié avec succès");
       
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            m= false;
       }
           return m;
        
   }
  public List<Admin> Trier(String critere) {
      
        List<Admin> admin = new ArrayList();
    
        
      
      String req = "select * from Admin  order by "+critere;
        try{ 
            
        
      ste = con.createStatement();
        //ensemble de resultat
        ResultSet rst = ste.executeQuery(req);

        while (rst.next()) {
          
            
            Admin f = new Admin();
             f.setId(rst.getInt(1));
             f.setNom(rst.getString(2));
             f.setPrenom(rst.getString(3));
             f.setNumtel(rst.getString(4));
             f.setLogin(rst.getString(5));
             f.setPassword(rst.getString(6));
              
            admin.add(f);
        } 
        }
      catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        
        return admin;
    }

}

