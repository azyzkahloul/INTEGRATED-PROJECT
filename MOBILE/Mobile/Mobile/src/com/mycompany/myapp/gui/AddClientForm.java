/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Client;
import com.mycompany.myapp.services.ServiceClient;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class AddClientForm extends Form{

    public AddClientForm(Form previous) {
        setTitle("Ajouter nouveau client");
        setLayout(BoxLayout.y());
        
        TextField nom = new TextField("","nom");
        TextField prenom= new TextField("", "prenom");
        TextField numtel= new TextField("", "numtel");
        TextField voitmat= new TextField("", "voitmat");
        TextField instamonnaie= new TextField("", "instamonnaie");
        TextField login= new TextField("", "login");
        TextField password= new TextField("", "password");
        TextField statut= new TextField("", "statut");


        Button btnValider = new Button("Ajouter");
        Button btnAfficher = new Button("Afficher Client");
        btnAfficher.addActionListener((evt) -> {
            new ListClientForm(previous).show();
        });
       btnValider.addActionListener((e) -> {
            
            try {
                
                if(nom.getText().equals("") || prenom.getText().equals("") || numtel.getText().equals("") || voitmat.getText().equals("")
                        ||instamonnaie.getText().equals("") || login.getText().equals("") || password.getText().equals("")
                        || statut.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    
                    //njibo idclient men session (current user)
                    Client a = new Client(String.valueOf(nom.getText()),
                                  String.valueOf(prenom.getText()),
                                  String.valueOf(numtel.getText()),
                                  String.valueOf(voitmat.getText()),
                                  String.valueOf(instamonnaie.getText()),
                                  String.valueOf(login.getText()),
                                  String.valueOf(password.getText()),
                                  String.valueOf(statut.getText()));

                    
                    System.out.println("data  client == "+a);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceClient.getInstance().addClient(a);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                   
                    //ba3d ajout net3adaw lel ListClientForm
                    //new ListClientForm(current).show();
                    
                    
                    //refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }   
            
        });
        
        addAll(nom,prenom,numtel,voitmat,instamonnaie,login,password,statut,btnValider,btnAfficher);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
