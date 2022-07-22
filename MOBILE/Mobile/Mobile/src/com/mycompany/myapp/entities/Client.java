/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String numtel;
    private String voitmat;
    private String instamonnaie;
    private String login;
    private String password;
    private String statut;

    public Client() {
    }

    public Client(String nom, String prenom, String numtel, String voitmat, String instamonnaie, String login, String password, String statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.voitmat = voitmat;
        this.instamonnaie = instamonnaie;
        this.login = login;
        this.password = password;
        this.statut = statut;
    }
    public Client(int id, String nom, String prenom, String numtel, String voitmat, String instamonnaie, String login, String password, String statut) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.voitmat = voitmat;
        this.instamonnaie = instamonnaie;
        this.login = login;
        this.password = password;
        this.statut = statut;
    }

    public Client(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getVoitmat() {
        return voitmat;
    }

    public void setVoitmat(String voitmat) {
        this.voitmat = voitmat;
    }

    public String getInstamonnaie() {
        return instamonnaie;
    }

    public void setInstamonnaie(String instamonnaie) {
        this.instamonnaie = instamonnaie;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    


    
    
}


