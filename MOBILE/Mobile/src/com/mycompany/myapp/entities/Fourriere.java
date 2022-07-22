/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Faycel
 */
public class Fourriere {
    private int id;
    private String nomf,nbplace,flatitude,flongtitude;
    
    public Fourriere() {
    }

    
    
    
  
    
    public Fourriere(int id, String nomf, String nbplace, String flatitude, String flongtitude) {
        this.id = id;
        this.nomf = nomf;
        this.nbplace = nbplace;
        this.flatitude = flatitude;
        this.flongtitude= flongtitude;
        
        
        
    }

   public Fourriere(String nomf, String nbplace, String flatitude, String flongtitude) {
      
        this.nomf = nomf;
        this.nbplace = nbplace;
        this.flatitude = flatitude;
        this.flongtitude= flongtitude;
        
        
        
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
        return nbplace;
    }

    public void setNbplace(String nbplace) {
        this.nbplace = nbplace;
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
