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
        //r.get  mthebch tet3ada 3al integer
         TextField nom = new TextField("","nom");
        TextField prenom= new TextField("", "prenom");
        TextField numtel= new TextField("", "numtel");
        TextField voitmat= new TextField("", "voitmat");
        TextField login= new TextField("", "login");
        TextField password= new TextField("", "password");
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNom(nom.getText());
           r.setPrenom(prenom.getText());
           r.setNumtel(numtel.getText());
           r.setVoitmat(voitmat.getText());
           r.setLogin(login.getText());
           r.setPassword(password.getText());
       //appel fonction modfier Facture men service
       
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
             new FloatingHint(login),
                new FloatingHint(password),
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        
        
    }
    
    
}
