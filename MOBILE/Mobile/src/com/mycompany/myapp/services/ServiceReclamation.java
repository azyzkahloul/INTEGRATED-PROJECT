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
 * @author LENOVO
 */
public class ServiceReclamation {
    
     public ArrayList<Reclamation> reclamations;
    
    
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
    public boolean addReclamation(Reclamation fa) {
        
      
        
        
        
        String url =Statics.BASE_URL+"reclamation/new?objet="+fa.getObjet()+"&description="+fa.getDescription()+
                "&etat="+fa.getEtat();
        
        req.setUrl(url);
        // req.setPost(false);
       req.addArgument("Objet : ", fa.getObjet()+"");
       req.addArgument("Description : ", fa.getDescription()+"");
       req.addArgument("Etat : ", fa.getEtat()+"");
      
     
        
        
        
        

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
        req.setUrl(Statics.BASE_URL+"reclamation/");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                   reclamations = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        Reclamation e;
                        e = new Reclamation(
                               (int) Float.parseFloat(obj.get("id").toString()),
                                (String) obj.get("objet"),
                                (String) obj.get("description"),
                                (String) obj.get("etat")                                                              
                                
                        );
                        
                        reclamations.add(e);
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
        return reclamations;
    }
    
     
  //Detail Facture bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Reclamation DetailReclamation( int id_rec , Reclamation fa) {
        
    String url = Statics.BASE_URL+"reclamation/?"+id_rec;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                fa.setObjet(obj.get("objet").toString());
                fa.setDescription(obj.get("description").toString());
                fa.setEtat(obj.get("etat").toString());
               
            
                
                
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return fa;
        
        
    }
    
   //Delete 
    public boolean deleteReclamation(int id_rec ) {
        String url = Statics.BASE_URL+"reclamation/delete/"+id_rec;
        
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
    public boolean updateReclamation(Reclamation fa) {
        String url = Statics.BASE_URL +"reclamation/edit/"+fa.getId_rec()+"?objet="+fa.getObjet()+"&description="+
                fa.getDescription()+"&etat="+fa.getEtat();
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