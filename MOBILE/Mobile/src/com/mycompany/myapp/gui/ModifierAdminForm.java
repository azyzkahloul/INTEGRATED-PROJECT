/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

/**
 *
 * @author LENOVO
 */
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
import com.mycompany.myapp.entities.Admin;
import com.mycompany.myapp.services.ServiceAdmin;

/**
 *
 * @author Lenovo
 */
public class ModifierAdminForm extends BaseForm {
    
    Form current;
    public ModifierAdminForm(Resources res , Admin r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField nom = new TextField(r.getNom() , "Nom" , 20 , TextField.ANY);
        TextField prenom = new TextField(r.getPrenom() , "Prenom" , 20 , TextField.ANY);
        TextField numtel = new TextField(r.getNumtel() , "Numéro téléphone" , 20 , TextField.ANY);
        TextField login = new TextField(r.getLogin() , "Adresse Email" , 20 , TextField.ANY);
        TextField password = new TextField(r.getPassword() , "Mot de passe" , 20 , TextField.ANY);
        
 
        
        
        
        
        
        
        nom.setUIID("NewsTopLine");
        prenom.setUIID("NewsTopLine");
        numtel.setUIID("NewsTopLine");
        login.setUIID("NewsTopLine");
        password.setUIID("NewsTopLine");
        
        nom.setSingleLineTextArea(true);
        prenom.setSingleLineTextArea(true);
        numtel.setSingleLineTextArea(true);
        login.setSingleLineTextArea(true);
        password.setSingleLineTextArea(true);
        
       Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNom(nom.getText());
           r.setPrenom(prenom.getText());
           r.setNumtel(numtel.getText());
           r.setPrenom(prenom.getText());
           r.setNom(nom.getText());
          
           
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceAdmin.getInstance().modifierAdmin(r)) { // if true
           new ListAdminForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListAdminForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(numtel),
                createLineSeparator(),//ligne de séparation
                new FloatingHint(login),
                createLineSeparator(),//ligne de séparation
                new FloatingHint(password),
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}

