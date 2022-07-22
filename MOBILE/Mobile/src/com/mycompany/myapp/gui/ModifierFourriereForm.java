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
import com.mycompany.myapp.entities.Fourriere;
import com.mycompany.myapp.services.ServiceFourriere;
/**
 *
 * @author Faycel
 */
public class ModifierFourriereForm extends BaseForm {
    
       
    Form current;
    public ModifierFourriereForm(Resources res , Fourriere r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField nom = new TextField(r.getNomf() , "Nom" , 20 , TextField.ANY);
        TextField nbplace = new TextField(r.getNbplace() , "Nombre Place" , 20 , TextField.ANY);
        TextField flatitude = new TextField(r.getFlatitude() , "Atitude" , 20 , TextField.ANY);
        TextField flongtitude = new TextField(r.getFlongtitude() , "Longtitude" , 20 , TextField.ANY);

        
 
        
        
        
        
        
        
        nom.setUIID("NewsTopLine");
        nbplace.setUIID("NewsTopLine");
        flatitude.setUIID("NewsTopLine");
        flongtitude.setUIID("NewsTopLine");
      
        nom.setSingleLineTextArea(true);
        nbplace.setSingleLineTextArea(true);
        flatitude.setSingleLineTextArea(true);
        flongtitude.setSingleLineTextArea(true);
        
       Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNomf(nom.getText());
           r.setNbplace(nbplace.getText());
           r.setFlatitude(flatitude.getText());
           r.setFlongtitude(flongtitude.getText());
  
          
           
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceFourriere.getInstance().modifierFourriere(r)) { // if true
           new ListFourriereForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListFourriereForm(res).show();
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
                new FloatingHint(nbplace),
                createLineSeparator(),
                new FloatingHint(flatitude),
                createLineSeparator(),//ligne de séparation
                new FloatingHint(flongtitude),
                createLineSeparator(),//ligne de séparation
             
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
    
    
    
}
