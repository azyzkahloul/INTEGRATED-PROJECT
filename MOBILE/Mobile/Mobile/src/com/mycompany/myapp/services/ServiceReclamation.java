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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Lenovo
 */
public class ServiceReclamation{
    
    //singleton 
    public static ServiceReclamation instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceReclamation getInstance() {
        if(instance == null )
            instance = new ServiceReclamation();
        return instance ;
    }
    
    public ServiceReclamation() {
        req = new ConnectionRequest(); 
    }
    
    
    //ajout 
    public boolean addReclamation(Reclamation rec) {
        
        String url =Statics.BASE_URL+"addReclamation?objet="+rec.getObjet()+"&description="+rec.getDescription()+
                "&etat"+rec.getEtat();
        
        req.setUrl(url);
        // req.setPost(false);
       req.addArgument("objet", rec.getObjet()+"");
       req.addArgument("description", rec.getDescription()+"");
       req.addArgument("etat", rec.getEtat()+"");

       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                boolean resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = true;
        return resultOK;
        
  
    }
    
    
    //affichage
    
    public ArrayList<Reclamation>showReclamation() {
        ArrayList<Reclamation> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"allavis";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamation = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamation.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Reclamation rec = new Reclamation();
                        float id_rec = Float.parseFloat(obj.get("id_rec").toString());
                        String objet = obj.get("objet").toString();
                        String description = obj.get("description").toString();
                        String etat = obj.get("etat").toString();

                        rec.setId_rec((int)id_rec);
                        rec.setObjet(objet);
                        rec.setDescription(description);
                        rec.setEtat(etat);
                       
                        
                        result.add(rec);
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        System.out.println(result);
        return result;    
        
    }
     
    
    //Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Reclamation DetailReclamation( int id_rec , Reclamation recl) {
        
        String url = Statics.BASE_URL+"showid/"+id_rec;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                recl.setObjet(obj.get("objet").toString());
                recl.setDescription(obj.get("description").toString());
                recl.setEtat(obj.get("etat").toString());

            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);  
           
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return recl;
    }
    
    
    //Delete 
    public boolean deleteReclamation(int id_rec ) {
        String url = Statics.BASE_URL +"deleteid_rec/"+id_rec;
        
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
    public boolean updateReclamation(Reclamation recl) {
        String url = Statics.BASE_URL +"updateJSON?id="+recl.getId_rec()+"&objet="+recl.getObjet()+"&description="+
                recl.getDescription()+"&etat"+recl.getEtat();
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