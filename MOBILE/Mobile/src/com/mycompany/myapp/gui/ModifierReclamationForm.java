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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author Lenovo
 */
public class ModifierReclamationForm extends Form {
   
      Form current;
    public ModifierReclamationForm(Reclamation r,Form previous) {
        setTitle("Modifier Reclamation");
        //r.get  mthebch tet3ada 3al integer
         TextField objet = new TextField("","objet");
        TextField description= new TextField("", "description");
        TextField etat= new TextField("", "etat");
      
        
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setObjet(objet.getText());
           r.setDescription(description.getText());
           r.setEtat(etat.getText());
    
           
       //appel fonction modfier Facture men service
       
       if(ServiceReclamation.getInstance().updateReclamation(r)) { // if true
           new ListReclamationForm(previous).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListReclamationForm(previous).show();
       });
       
        Container content = BoxLayout.encloseY(
                new FloatingHint(objet),
                new FloatingHint(description),
                new FloatingHint(etat),
             
             
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        
        
    }
    
    
}
