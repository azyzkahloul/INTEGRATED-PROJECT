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
import com.mycompany.myapp.entities.Fourriere;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Faycel
 */
public class ServiceFourriere {
      public static ServiceFourriere instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceFourriere getInstance() {
        if(instance == null )
            instance = new ServiceFourriere();
        return instance ;
        
        
    }
        
        public ServiceFourriere() {
        req = new ConnectionRequest();
        
    } 
        
        
        
        public void ajoutFourriere(Fourriere fourriere) {
        
        String url =Statics.BASE_URL+"fourriere/new?nomf="+fourriere.getNomf()+"&nbplace="+fourriere.getNbplace()+"&flatitude="+fourriere.getFlatitude()+"&flongtitude="+fourriere.getFlongtitude(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
        
        
    public ArrayList<Fourriere>affichageFourriere() {
        ArrayList<Fourriere> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"fourriere/";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapFourriere = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapFourriere.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Fourriere re = new Fourriere();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String nomf = obj.get("nomf").toString();
                        String nbplace = obj.get("nbplace").toString();
                        String flatitude = obj.get("flatitude").toString();
                        String flongtitude = obj.get("flongtitude").toString();
                   
                        
                      
                        re.setId((int)id);
                        re.setNomf(nomf);
                        re.setNbplace(nbplace);
                        re.setFlatitude(flatitude);
                        re.setFlongtitude(flongtitude);
                       
                       
                        
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
                
          
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
        
        
        
        
         public Fourriere DetailFourriere( int id , Fourriere fourriere) {
        
        String url = Statics.BASE_URL+"fourriere/?"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                fourriere.setNomf(obj.get("nomf").toString());
                fourriere.setNbplace(obj.get("nbplace").toString());
                fourriere.setFlatitude(obj.get("flatitude").toString());
                fourriere.setFlongtitude(obj.get("flongtitude").toString());
           
                
                
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return fourriere;
        
        
    }
    
        
        //Delete 
    public boolean delete(Fourriere fourriere) {
        String url = Statics.BASE_URL +"fourriere/delete/"+fourriere.getId();
        
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
    public boolean modifierFourriere( Fourriere fourriere) {
        String url = Statics.BASE_URL +"fourriere/edit/"+fourriere.getId()+"?nomf="+fourriere.getNomf()+"&nbplace="+fourriere.getNbplace()+"&flatitude="+fourriere.getFlatitude()+"&flongtitude="+fourriere.getFlongtitude(); 
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
