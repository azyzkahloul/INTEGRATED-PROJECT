/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.services.ServiceReponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import com.codename1.ui.FontImage;
/**
 *
 * @author Lenovo
 */
public class AjoutReponseForm extends Form {
    
   
    
    Form current;
    public AjoutReponseForm(Form previous) throws IOException {
        setTitle("Ajouter Reponse");
        setLayout(BoxLayout.y());
        
        TextField rps = new TextField("","Rps : ");

        

        Button btnValider = new Button("Ajouter");
       
        //Button mail = new Button("Envoyer un e-mail",Image.createImage("/send.png"));

        Button btnAfficher = new Button("Afficher Reponse");
           //metier
        // mail.setTextPosition(Component.BOTTOM);

          //   mail.addActionListener(e -> new MessageForm(current).show());
        btnAfficher.addActionListener((evt) -> {
            new ListReponseForm(previous).show();
        });
       btnValider.addActionListener((e) -> {
            
            try {
                
                if(rps.getText().equals("") ) 
                {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    
                    //njibo idFacture men session (current user)
                    Reponse a = new Reponse(
                            String.valueOf(rps.getText())
                    );
                          
                                  

                    
                    System.out.println("data  reponse == "+a);
                    // à revoir
                    
                    //appelle methode ajouterReponse mt3 service Reponse bch nzido données ta3na fi base 
                    ServiceReponse.getInstance().addReponse(a);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                   
                    //ba3d ajout net3adaw lel ListFactureForm
                    
                    new ListReponseForm(current).show();
                    
        
                             

                    //
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }   
            
        });
           //metier
        addAll(rps,btnValider,btnAfficher);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }


   
    
    
    
    
}
