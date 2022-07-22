/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author user
 */
public class Reponse {
    private int id;
    private String rps;
    
      public Reponse() {
    }
      
    public Reponse(int id, String rps) {
        this.id = id;
        this.rps = rps;
    }

    public Reponse(String rps) {
        this.rps = rps;    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRps() {
        return rps;
    }

    public void setRps(String rps) {
        this.rps = rps;
    }
}
