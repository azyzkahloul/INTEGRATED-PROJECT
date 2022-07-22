/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import javafx.scene.control.TextField;


public class Client {
    private int id_client;
    private String nom;
    private String prenom;
    private String numtel;
    private String voitmat;
    private String login;
    private String password;
    private String is_verified;
    private String reset_token;
    private String statut;
    private int Reset;

    public Client(int id_client, String nom, String prenom, String numtel, String voitmat, String login, String password, String is_verified, String reset_token, String statut, int Reset) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.voitmat = voitmat;
        this.login = login;
        this.password = password;
        this.is_verified = is_verified;
        this.reset_token = reset_token;
        this.statut = statut;
        this.Reset = Reset;
    }

    public Client(String nom, String prenom, String numtel, String voitmat, String login, String password, String is_verified, String reset_token, String statut, int Reset) {
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.voitmat = voitmat;
        this.login = login;
        this.password = password;
        this.is_verified = is_verified;
        this.reset_token = reset_token;
        this.statut = statut;
        this.Reset = Reset;
    }

    public Client() {
    }

    public Client(int id_client, String nom, String prenom, String numtel, String voitmat, String login, String password, String statut) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.voitmat = voitmat;
        this.login = login;
        this.password = password;
        this.statut = statut;
    }

  
    
    

    public Client(String nom, String prenom, String numtel, String voitmat, String login, String statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.voitmat = voitmat;
        this.login = login;
        this.statut = statut;
       
    }

    public Client(String nom, String prenom, String numtel, String voitmat, String login, String password, String statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.voitmat = voitmat;
        this.login = login;
        this.password = password;
        this.statut = statut;
    }
    

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
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

    public String getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(String is_verified) {
        this.is_verified = is_verified;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getReset() {
        return Reset;
    }

    public void setReset(int Reset) {
        this.Reset = Reset;
    }

  
    

}


