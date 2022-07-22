/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Client;

import com.mycompany.myapp.services.ServiceClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class AddClientForm extends Form{
 
    
    
    Form current;
    public AddClientForm(Form previous) throws IOException {
        setTitle("Ajouter Client");
        setLayout(BoxLayout.y());
        
        TextField nom = new TextField("","Nom : ");
        TextField prenom= new TextField("", "Prénom");
        TextField numtel= new TextField("", "Numéro Tel");
        TextField voitmat= new TextField("", "Matricule");
        TextField login= new TextField("", "Login");
        
        // maybe here to try how u can use password form
        TextField password= new TextField("", "Password");

        Button btnValider = new Button("Ajouter");
        //metier
       // Button mail = new Button("Envoyer un e-mail",Image.createImage("/send.png"));

        Button btnAfficher = new Button("Afficher Client");
           //metier
      //   mail.setTextPosition(Component.BOTTOM);

            // mail.addActionListener(e -> new MessageForm(current).show());
        btnAfficher.addActionListener((evt) -> {
            new ListClientForm(previous).show();
        });
       btnValider.addActionListener((e) -> {
            
            try {
                
                if(nom.getText().equals("") || prenom.getText().equals("") || numtel.getText().equals("")     ) 
                {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    
                    //njibo idFacture men session (current user)
                    Client a = new Client(
                            String.valueOf(nom.getText()),
                            String.valueOf(prenom.getText()),
                            String.valueOf(numtel.getText()),
                            String.valueOf(voitmat.getText()),
                            String.valueOf(login.getText()),
                            String.valueOf(password.getText())
                    
                    );
                                  

                    
                    System.out.println("data  client == "+a);
                    // à revoir
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceClient.getInstance().addClient(a);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                   
                    //ba3d ajout net3adaw lel ListFactureForm
                    
                    new ListClientForm(current).show();
                    
        
                             

                    //
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }   
            
        });
           //metier
        addAll(nom,prenom,numtel,voitmat,login,password,btnValider,btnAfficher);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
    
    
    
}
