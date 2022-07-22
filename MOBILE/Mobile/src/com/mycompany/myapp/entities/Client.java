/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


public class Client {
    private int id_client;
    private String nom,prenom,numtel,voitmat,login,password;
 
  
    

    

    public Client(String nom, String prenom, String numtel, String voitmat, String login, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.voitmat = voitmat;
        this.login = login;
        this.password = password;
    }

  



    public Client() {
    }

   
    public Client(int id_client, String nom, String prenom, String numtel, String voitmat,  String login, String password) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.voitmat = voitmat;
        this.login = login;
        this.password = password;
        
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

   

}


