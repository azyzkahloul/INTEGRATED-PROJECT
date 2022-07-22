/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author user
 */
public class Fourriere {
     private int id;
    private String nomf;
    private String Nbplace;
     private String flatitude;
    private String flongtitude;

    public Fourriere() {
    }

    public Fourriere(int id, String nomf, String Nbplace, String flatitude, String flongtitude) {
        this.id = id;
        this.nomf = nomf;
        this.Nbplace = Nbplace;
        this.flatitude = flatitude;
        this.flongtitude = flongtitude;
    }

    public Fourriere(String nomf, String Nbplace, String flatitude, String flongtitude) {
        this.nomf = nomf;
        this.Nbplace = Nbplace;
        this.flatitude = flatitude;
        this.flongtitude = flongtitude;
    }

    @Override
    public String toString() {
        return "Fourriere{" + "id=" + id + ", nomf=" + nomf + ", Nbplace=" + Nbplace + ", flatitude=" + flatitude + ", flongtitude=" + flongtitude + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomf() {
        return nomf;
    }

    public void setNomf(String nomf) {
        this.nomf = nomf;
    }

    public String getNbplace() {
        return Nbplace;
    }

    public void setNbplace(String Nbplace) {
        this.Nbplace = Nbplace;
    }

    public String getFlatitude() {
        return flatitude;
    }

    public void setFlatitude(String flatitude) {
        this.flatitude = flatitude;
    }

    public String getFlongtitude() {
        return flongtitude;
    }

    public void setFlongtitude(String flongtitude) {
        this.flongtitude = flongtitude;
    }

     
    
}

