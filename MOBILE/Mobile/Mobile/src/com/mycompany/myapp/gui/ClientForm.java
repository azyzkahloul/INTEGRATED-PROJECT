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
import com.mycompany.myapp.entities.Client;
import com.mycompany.myapp.services.ServiceClient;

/**
 *
 * @author Bourguiba
 */
public class ClientForm extends Form {

    public ClientForm(Form previous, Resources res) {
        setTitle("Gestion des Clients");
        setLayout(BoxLayout.y());

        TextField nom = new TextField("", "nom ");
        TextField prenom = new TextField("", "prenom");
        
        Button btnAjout = new Button("Ajouter client");
        Button btnAfficher = new Button("Afficher client");
        btnAfficher.addActionListener((evt) -> {
            new ListClientForm(previous).show();

        });

        btnAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nom.getText().length() == 0) || (prenom.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Client h = new Client(nom.getText(), prenom.getText());

                        if (ServiceClient.getInstance().addClient(h)) {
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

        addAll(nom,prenom, btnAjout, btnAfficher);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    ClientForm(Resources res) {
      
        
    }
}

