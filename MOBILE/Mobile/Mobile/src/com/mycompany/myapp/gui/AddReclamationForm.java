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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class AddReclamationForm extends Form{

    public AddReclamationForm(Form previous) {
        setTitle("Ajouter nouveau reclamation");
        setLayout(BoxLayout.y());
        
        TextField objet = new TextField("","objet");
        TextField description= new TextField("", "description");
        TextField etat= new TextField("", "etat");


        Button btnValider = new Button("Ajouter");
        Button btnAfficher = new Button("Afficher Reclamation");
        btnAfficher.addActionListener((evt) -> {
            new ListReclamationForm(previous).show();
        });
       btnValider.addActionListener((e) -> {
            
            try {
                
                if(objet.getText().equals("") || description.getText().equals("") || etat.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    
                    //njibo idReclamation men session (current user)
                    Reclamation rec = new Reclamation(String.valueOf(objet.getText()),
                                  String.valueOf(description.getText()),
                                  String.valueOf(etat.getText()));

                    
                    System.out.println("data  reclamation == "+rec);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceReclamation.getInstance().addReclamation(rec);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                   
                    //ba3d ajout net3adaw lel ListReclamationForm
                    //new ListReclamationForm(current).show();
                    
                    
                    //refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }   
            
        });
        
        addAll(objet,description,etat,btnValider,btnAfficher);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
