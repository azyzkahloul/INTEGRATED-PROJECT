package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public class ServiceReponse {
    
     public ArrayList<Reponse> reponses;
    
    
     //singleton 
    public static ServiceReponse instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceReponse getInstance() {
        if(instance == null )
            instance = new ServiceReponse();
        return instance ;
     }
   

 public ServiceReponse() {
        req = new ConnectionRequest(); 
    }
 
 //ajout 
    public boolean addReponse(Reponse fa) {
        
      
        
        
        
        String url =Statics.BASE_URL+"Reponse/new?rps="+fa.getRps();
        
        req.setUrl(url);
        // req.setPost(false);
       req.addArgument("Rps : ", fa.getRps()+"");
      
     
        
        
        
        

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
    
    public ArrayList<Reponse>showReponse() {
        req.setUrl(Statics.BASE_URL+"reponse/");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                   reponses = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        Reponse e;
                        e = new Reponse(
                               (int) Float.parseFloat(obj.get("id").toString()),
                                (String) obj.get("rps")                                                           
                                
                        );
                        
                        reponses.add(e);
                    }

                } catch (IOException ex) {
                    System.out.println("montage vide");
                }

                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return reponses;
    }
    
     
  //Detail Facture bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Reponse DetailReponse( int id , Reponse fa) {
        
    String url = Statics.BASE_URL+"reponse/?"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                fa.setRps(obj.get("rps").toString());
               
            
                
                
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return fa;
        
        
    }
    
   //Delete 
    public boolean deleteReponse(int id ) {
        String url = Statics.BASE_URL+"reponse/delete/"+id;
        
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
    public boolean updateReponse(Reponse fa) {
        String url = Statics.BASE_URL +"reponse/edit/"+fa.getId()+"?rps="+fa.getRps();
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