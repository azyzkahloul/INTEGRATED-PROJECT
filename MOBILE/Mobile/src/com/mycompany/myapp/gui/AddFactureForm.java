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
import com.mycompany.myapp.entities.Facture;
import com.mycompany.myapp.services.ServiceFacture;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Azyz
 */
public class AddFactureForm extends Form{
    
    
    
    Form current;
    public AddFactureForm(Form previous) throws IOException {
        setTitle("Ajouter Facture");
        setLayout(BoxLayout.y());
        
        TextField nbheure = new TextField("","N Heure(s) : ");
        TextField pu= new TextField("", "Prix Unitaire");
        TextField total= new TextField("", "Total");
        TextField dateentrer= new TextField("", "Date");
        

        Button btnValider = new Button("Ajouter");
        //metier
        Button mail = new Button("Envoyer un e-mail",Image.createImage("/send.png"));

        Button btnAfficher = new Button("Afficher Facture");
           //metier
         mail.setTextPosition(Component.BOTTOM);

             mail.addActionListener(e -> new MessageForm(current).show());
        btnAfficher.addActionListener((evt) -> {
            new ListFactureForm(previous).show();
        });
       btnValider.addActionListener((e) -> {
            
            try {
                
                if(nbheure.getText().equals("") || pu.getText().equals("") || total.getText().equals("")     ) 
                {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    
                    //njibo idFacture men session (current user)
                    Facture a = new Facture(
                            String.valueOf(nbheure.getText()),
                            String.valueOf(pu.getText()),
                            String.valueOf(total.getText()),
                            String.valueOf(dateentrer.getText()));
                                  

                    
                    System.out.println("data  facture == "+a);
                    // à revoir
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceFacture.getInstance().addFacture(a);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                   
                    //ba3d ajout net3adaw lel ListFactureForm
                    
                    new ListFactureForm(current).show();
                    
        
                             

                    //
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }   
            
        });
           //metier
        addAll(nbheure,pu,total,dateentrer,btnValider,btnAfficher,mail);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
    
    
    
}
