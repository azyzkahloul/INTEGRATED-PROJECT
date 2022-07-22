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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import javafx.scene.control.PasswordField;

/**
 *
 * @author Lenovo
 */
public class ModifierReclamationForm extends Form {
    
    Form current;
    public ModifierReclamationForm(Reclamation re,Form previous) {
        setTitle("Modifier Reclamation");
        
        TextField objet = new TextField(re.getObjet(), "Objet" , 20 , TextField.ANY);
        TextField description = new TextField(re.getDescription() , "Description" , 20 , TextField.ANY);
        TextField etat = new TextField(re.getEtat(), "Etat" , 20 , TextField.ANY);
        
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           re.setObjet(objet.getText());
           re.setDescription(description.getText());
           re.setEtat(etat.getText());
       
       //appel fonction modfier reclamation men service
       
       if(ServiceReclamation.getInstance().updateReclamation(re)) { // if true
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

