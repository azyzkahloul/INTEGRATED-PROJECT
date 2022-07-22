/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author malek
 */
public class Facture {
    
    private int id_facture ;
    private String nbheure,pu,total,dateentrer;

    
    
    
       public Facture() {
     
    }
    
    
    

    public Facture(int id_facture, String nbheure, String pu, String total, String dateentrer) {
        this.id_facture = id_facture;
        this.nbheure = nbheure;
        this.pu = pu;
        this.total = total;
        this.dateentrer = dateentrer;
    }

    public Facture(String nbheure, String pu, String total, String dateentrer) {
        this.nbheure = nbheure;
        this.pu = pu;
        this.total = total;
        this.dateentrer = dateentrer;
    }

 
    
    
     @Override
    public String toString() {
        return "Facture{" + "id_facture=" + id_facture + ", nbheure=" + nbheure + ", pu=" + pu + ", total=" + total + ", dateentrer=" + dateentrer + '}';
    }

    
    

    public int getId_facture() {
        return id_facture;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public String getNbheure() {
        return nbheure;
    }

    public void setNbheure(String nbheure) {
        this.nbheure = nbheure;
    }

    public String getPu() {
        return pu;
    }

    public void setPu(String pu) {
        this.pu = pu;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDateentrer() {
        return dateentrer;
    }

    public void setDateentrer(String dateentrer) {
        this.dateentrer = dateentrer;
    }
    
}
