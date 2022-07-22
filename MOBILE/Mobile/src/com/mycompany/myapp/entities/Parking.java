/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author malek
 */
public class Parking {
     private int id;
    private String nomp ,nbplace,adresse,description;

    public Parking() {
    }

    public Parking(int id, String nomp, String nbplace, String adresse, String description) {
        this.id = id;
        this.nomp = nomp;
        this.nbplace = nbplace;
        this.adresse = adresse;
        this.description = description;
    }
  public Parking( String nomp, String nbplace, String adresse, String description) {
        
        this.nomp = nomp;
        this.nbplace = nbplace;
        this.adresse = adresse;
        this.description = description;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomp() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }

    public String getNbplace() {
        return nbplace;
    }

    public void setNbplace(String nbplace) {
        this.nbplace = nbplace;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
