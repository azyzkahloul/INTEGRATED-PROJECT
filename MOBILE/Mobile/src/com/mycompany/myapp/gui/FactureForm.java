/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Facture;
import com.mycompany.myapp.services.ServiceFacture;


/**
 *
 * @author Azyz
 */
public class FactureForm extends Form{
    
    
    
    
    
    public FactureForm(Form previous, Resources res) {
        setTitle("Gestion des Factures");
        setLayout(BoxLayout.y());

        TextField nbheure = new TextField("", "nbheure ");
        TextField pu = new TextField("", "pu");
        TextField total = new TextField("", "total ");
        TextField dateentrer = new TextField("", "dateentrer");
        
        Button btnAjout = new Button("Ajouter Facture");
        Button btnAfficher = new Button("Afficher Facture");
        btnAfficher.addActionListener((evt) -> {
            new ListFactureForm(previous).show();

        });

        btnAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nbheure.getText().length() == 0) || (pu.getText().length() == 0)|| 
                    (total.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
              Facture h = new Facture();
 // NE9ssa TOTAL
                       // Facture h = new Facture(nbheure.getText(), pu.getText(), total.getText(),dateentrer.getText());

                        if (ServiceFacture.getInstance().addFacture(h)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }

            }
        });

        addAll(nbheure,pu,total,dateentrer, btnAjout, btnAfficher);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    FactureForm(Resources res) {
      
        
    }
    
    
    
    
}
