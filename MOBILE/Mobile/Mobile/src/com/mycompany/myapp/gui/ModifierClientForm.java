/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Client;
import com.mycompany.myapp.services.ServiceClient;
import javafx.scene.control.PasswordField;

/**
 *
 * @author Lenovo
 */
public class ModifierClientForm extends Form {
    
    Form current;
    public ModifierClientForm(Client r,Form previous) {
        setTitle("Modifier Client");
        
        TextField nom = new TextField(r.getNom(), "Nom" , 20 , TextField.ANY);
        TextField prenom = new TextField(r.getPrenom() , "Prenom" , 20 , TextField.ANY);
        TextField numtel = new TextField(r.getNumtel(), "Numtel" , 20 , TextField.ANY);
        TextField voitmat = new TextField(r.getVoitmat(), "Nom" , 20 , TextField.ANY);
        TextField instamonnaie = new TextField(r.getInstamonnaie(), "Instamonnaie" , 20 , TextField.ANY);
        TextField login = new TextField(r.getLogin(), "Login" , 20 , TextField.ANY);
        TextField password = new TextField(r.getPassword(), "Password" , 20 , TextField.ANY);
        TextField statut = new TextField(r.getStatut(), "Statut" , 20 , TextField.ANY);
        
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNom(nom.getText());
           r.setPrenom(prenom.getText());
           r.setNumtel(numtel.getText());
           r.setVoitmat(voitmat.getText());
           r.setInstamonnaie(instamonnaie.getText());
           r.setLogin(login.getText());
           r.setPassword(password.getText());
           r.setStatut(statut.getText());
       
       //appel fonction modfier client men service
       
       if(ServiceClient.getInstance().updateClient(r)) { // if true
           new ListClientForm(previous).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListClientForm(previous).show();
       });
       
        Container content = BoxLayout.encloseY(
                new FloatingHint(nom),
                new FloatingHint(prenom),
                new FloatingHint(numtel),
                new FloatingHint(voitmat),
                new FloatingHint(instamonnaie),
                new FloatingHint(login),
                new FloatingHint(password),
                new FloatingHint(statut),

                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        
        
    }
}

