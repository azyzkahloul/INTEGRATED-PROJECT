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
public class Reclamation {
        private int id_rec;
    private String objet,description,etat;

    public Reclamation() {
}
     public Reclamation(int id_rec, String objet, String description, String etat) {
        this.id_rec = id_rec;
        this.objet = objet;
        this.description = description;
        this.etat = etat;
    }
    public Reclamation(String objet, String description, String etat) {
        
        this.objet = objet;
        this.description = description;
        this.etat = etat;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_rec=" + id_rec + ", objet=" + objet + ", description=" + description + ", etat=" + etat + '}';
    }
    
    
    
}
