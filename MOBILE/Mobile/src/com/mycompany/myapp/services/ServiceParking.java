/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Parking;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author malek
 */
public class ServiceParking {
     public static ServiceParking instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceParking getInstance() {
        if(instance == null )
            instance = new ServiceParking();
        return instance ;
    }
    
    
    
    public ServiceParking() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutParking(Parking parking) {
        
        
        
        String url =Statics.BASE_URL+"parking/new?nomp="+parking.getNomp()+"&nbplace="+parking.getNbplace()+"&adresse="+parking.getAdresse()+"&description="+parking.getDescription();        
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
 //affichage
    
    public ArrayList<Parking>affichageParkings() {
        ArrayList<Parking> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"parking/";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapParkings = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapParkings.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Parking p = new Parking();
                        
                       //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String nomp = obj.get("nomp").toString();
                        
                        String nbplace = obj.get("nbplace").toString();
                        String adresse = obj.get("adresse").toString();
                        String description = obj.get("description").toString();

                        p.setId((int)id);
                        p.setNomp(nomp);
                        p.setNbplace(nbplace);
                        p.setAdresse(adresse);
                        p.setDescription(description);
                        //Date 
                    
                        
                        //insert data into ArrayList result
                        result.add(p);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
                
          
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    
    //Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Parking DetailParking( int id , Parking p) {
        
        String url = Statics.BASE_URL+"parking/?"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                p.setNomp(obj.get("nom").toString());
                p.setNbplace(obj.get("prenom").toString());
                p.setAdresse(obj.get("adresse").toString());
                p.setDescription(obj.get("description").toString());
              
                
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return p;
        
        
    }
    
    
    //Delete 
    public boolean deleteParking(Parking parking) {
        String url = Statics.BASE_URL +"parking/delete/"+parking.getId();
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
    //Update 
    public boolean modifierParking(Parking p) {
        String url = Statics.BASE_URL +"parking/edit/"+p.getId()+"?nomp="+p.getNomp()+"&nbplace="+p.getNbplace()+"&adresse="+p.getAdresse()+"&description="+p.getDescription(); 
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
}
