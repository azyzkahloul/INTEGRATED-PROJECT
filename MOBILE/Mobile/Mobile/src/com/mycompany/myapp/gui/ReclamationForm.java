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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author Bourguiba
 */
public class ReclamationForm extends Form {

    public ReclamationForm(Form previous, Resources res) {
        setTitle("Gestion des Reclamations");
        setLayout(BoxLayout.y());

        TextField objet = new TextField("", "objet ");
        TextField description = new TextField("", "description");
        TextField etat = new TextField("", "etat");
        
        Button btnAjout = new Button("Ajouter reclamation");
        Button btnAfficher = new Button("Afficher reclamation");
        btnAfficher.addActionListener((evt) -> {
            new ListReclamationForm(previous).show();

        });

        btnAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((objet.getText().length() == 0) || (description.getText().length() == 0) || (etat.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Reclamation r = new Reclamation(objet.getText(), description.getText(), etat.getText());

                        if (ServiceReclamation.getInstance().addReclamation(r)) {
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

        addAll(objet, description, etat, btnAjout, btnAfficher);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    ReclamationForm(Resources res) {
      
        
    }
}

