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
import com.mycompany.myapp.entities.Facture;
import com.mycompany.myapp.services.ServiceFacture;
import javafx.scene.control.PasswordField;
/**
 *
 * @author Azyz
 */
public class ModifierFactureForm extends Form{
    
    
      Form current;
    public ModifierFactureForm(Facture r,Form previous) {
        setTitle("Modifier Facture");
        //r.get  mthebch tet3ada 3al integer
         TextField nbheure = new TextField("","nbheure");
        TextField pu= new TextField("", "pu");
        TextField total= new TextField("", "total");
        TextField dateentrer= new TextField("", "dateentrer");
        
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNbheure(nbheure.getText());
           r.setPu(pu.getText());
           r.setTotal(total.getText());
           r.setDateentrer(dateentrer.getText());
           
       //appel fonction modfier Facture men service
       
       if(ServiceFacture.getInstance().updateFacture(r)) { // if true
           new ListFactureForm(previous).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListFactureForm(previous).show();
       });
       
        Container content = BoxLayout.encloseY(
                new FloatingHint(nbheure),
                new FloatingHint(pu),
                new FloatingHint(total),
                new FloatingHint(dateentrer),
             
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        
        
    }
    
    
}
