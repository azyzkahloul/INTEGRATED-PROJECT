/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import java.io.IOException;



/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {
Form current;
    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
        current = this;
   
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("  AzyzKahloul", profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        
        getToolbar().addCommandToSideMenu("Home", null, ((evt) -> {
        }));
     
     getToolbar().addMaterialCommandToSideMenu("Admin ",FontImage.MATERIAL_SETTINGS, (e) -> {
            new AjoutAdminForm(res).show();

        });
     getToolbar().addMaterialCommandToSideMenu("Client ",FontImage.MATERIAL_SETTINGS, (e) -> {
            try {
                new AddClientForm(current).show();
            } catch (IOException ex) {
              //  Logger.getLogger(SideMenuBaseForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
     getToolbar().addMaterialCommandToSideMenu("Facture ",FontImage.MATERIAL_SETTINGS, (e) -> {
            try {
                new AddFactureForm(current).show();
            } catch (IOException ex) {
              //  Logger.getLogger(SideMenuBaseForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
     getToolbar().addMaterialCommandToSideMenu("Fourriére ",FontImage.MATERIAL_SETTINGS, (e) -> {
            new AjoutFourriereForm(res).show();

        });
     getToolbar().addMaterialCommandToSideMenu("Parking ",FontImage.MATERIAL_SETTINGS, (e) -> {
            new AjoutParkingForm(res).show();

        });
     getToolbar().addMaterialCommandToSideMenu("Reclamation ",FontImage.MATERIAL_SETTINGS, (e) -> {
            try {
                new AjoutReclamationForm(current).show();
            } catch (IOException ex) {
             //   Logger.getLogger(SideMenuBaseForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
       getToolbar().addMaterialCommandToSideMenu("Reponse ",FontImage.MATERIAL_SETTINGS, (e) -> {
            try {
                new AjoutReponseForm(current).show();
            } catch (IOException ex) {
             //   Logger.getLogger(SideMenuBaseForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
     //getToolbar().addMaterialCommandToSideMenu("Client ",FontImage.MATERIAL_DASHBOARD, (e) -> {
       //     new AddClientForm(current).show();

        //});
        //getToolbar().addMaterialCommandToSideMenu("Profile ",FontImage.MATERIAL_SETTINGS, (e) -> {
          //  new AddClientForm(current).show();
        //});
        //getToolbar().addMaterialCommandToSideMenu("  Profile", FontImage.MATERIAL_DASHBOARD,  e -> showOtherForm(res));
        //getToolbar().addMaterialCommandToSideMenu("  Parking", FontImage.MATERIAL_TRENDING_UP,  e -> showOtherForm(res));
        //getToolbar().addMaterialCommandToSideMenu("  Fourriere", FontImage.MATERIAL_ACCESS_TIME,  e -> showOtherForm(res));
        //getToolbar().addMaterialCommandToSideMenu("  Agent Service", FontImage.MATERIAL_SETTINGS,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
    
        
    
    //protected abstract void showOtherForm(Resources res);
}
}