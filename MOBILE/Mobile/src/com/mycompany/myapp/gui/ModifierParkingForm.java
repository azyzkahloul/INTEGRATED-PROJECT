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
import com.mycompany.myapp.entities.Parking;
import com.mycompany.myapp.services.ServiceParking;
/**
 *
 * @author malek
 */
public class ModifierParkingForm extends BaseForm {
      Form current;
    public ModifierParkingForm(Resources res , Parking r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField nomp = new TextField(r.getNomp() , "Nom" , 20 , TextField.ANY);
        TextField nbplace = new TextField(r.getNbplace() , "Prenom" , 20 , TextField.ANY);
        TextField adresse = new TextField(r.getAdresse() , "Adresse" , 20 , TextField.ANY);
        TextField description = new TextField(r.getDescription() , "description" , 20 , TextField.ANY);
         

        
        nomp.setUIID("NewsTopLine");
       nbplace.setUIID("NewsTopLine");
        adresse.setUIID("NewsTopLine");
      description.setUIID("NewsTopLine");
        
        
        nomp.setSingleLineTextArea(true);
        nbplace.setSingleLineTextArea(true);
         adresse.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
      
        
       Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNomp(nomp.getText());
           r.setNbplace(nbplace.getText());
           r.setAdresse(adresse.getText());
           r.setDescription(description.getText());
           
          
           
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceParking.getInstance().modifierParking(r)) { // if true
           new ListParkingForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListParkingForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(nomp),
                createLineSeparator(),
                new FloatingHint(nbplace),
                createLineSeparator(),
                new FloatingHint(adresse),
                createLineSeparator(),//ligne de séparation
                new FloatingHint(description),
                createLineSeparator(),//ligne de séparation
               
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}



