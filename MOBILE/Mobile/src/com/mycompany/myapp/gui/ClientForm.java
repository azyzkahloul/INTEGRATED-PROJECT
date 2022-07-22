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

        TextField nom = new TextField("", "Nom ");
        TextField prenom = new TextField("", "Prénom");
        TextField numtel = new TextField("", "Numero Tel ");
        TextField voitmat = new TextField("", "Matricule");
        TextField login = new TextField("", "Login ");
        TextField password = new TextField("", "Password");
        Button btnAjout = new Button("Ajouter Client");
        Button btnAfficher = new Button("Afficher Client");
        btnAfficher.addActionListener((evt) -> {
            new ListClientForm(previous).show();

        });

        btnAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nom.getText().length() == 0) || (prenom.getText().length() == 0)|| 
                    (numtel.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
              Client h = new Client();
 // NE9ssa TOTAL
                       // Facture h = new Facture(nbheure.getText(), pu.getText(), total.getText(),dateentrer.getText());

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

        addAll(nom,prenom,numtel,voitmat,login,password, btnAjout, btnAfficher);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    ClientForm(Resources res) {
      
        
    }
    
    
    
    
}
