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
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.services.ServiceReponse;

/**
 *
 * @author Lenovo
 */
public class ModifierReponseForm extends Form {
   
      Form current;
    public ModifierReponseForm(Reponse r,Form previous) {
        setTitle("Modifier Reponse");
        //r.get  mthebch tet3ada 3al integer
         TextField rps = new TextField("","rps");
      
        
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setRps(rps.getText());
    
           
       //appel fonction modfier Facture men service
       
       if(ServiceReponse.getInstance().updateReponse(r)) { // if true
           new ListReponseForm(previous).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListReponseForm(previous).show();
       });
       
        Container content = BoxLayout.encloseY(
                new FloatingHint(rps),
             
             
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        
        
    }
    
    
}
