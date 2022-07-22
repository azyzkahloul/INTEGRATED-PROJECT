package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Admin;
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
public class ServiceAdmin {
    
    public static ServiceAdmin instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceAdmin getInstance() {
        if(instance == null )
            instance = new ServiceAdmin();
        return instance ;
    }
    
    
    
    public ServiceAdmin() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutAdmin(Admin admin) {
        
        String url =Statics.BASE_URL+"admin/new?nom="+admin.getNom()+"&prenom="+admin.getPrenom()+"&numtel="+admin.getNumtel()+"&login="+admin.getLogin()+"&password="+admin.getPassword(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
 //affichage
    
    public ArrayList<Admin>affichageAdmins() {
        ArrayList<Admin> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"admin/";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapAdmins = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapAdmins.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Admin re = new Admin();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String nom = obj.get("nom").toString();
                        String prenom = obj.get("prenom").toString();
                        String numtel = obj.get("numtel").toString();
                        String login = obj.get("login").toString();
                        String password = obj.get("password").toString();
                        
                      
                        re.setId((int)id);
                        re.setNom(nom);
                        re.setPrenom(prenom);
                        re.setNumtel(numtel);
                        re.setLogin(login);
                        re.setPassword(password);
                       
                        
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
    
    
    
    //Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Admin DetailAdmin( int id , Admin admin) {
        
        String url = Statics.BASE_URL+"admin/?"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                admin.setNom(obj.get("nom").toString());
                admin.setPrenom(obj.get("prenom").toString());
                admin.setNumtel(obj.get("numtel").toString());
                admin.setLogin(obj.get("login").toString());
                admin.setPassword(obj.get("password").toString());
                
                
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return admin;
        
        
    }
    
    
    //Delete 
    public boolean deleteAdmin(Admin admin) {
        String url = Statics.BASE_URL +"admin/delete/"+admin.getId();
        
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
    public boolean modifierAdmin(Admin admin) {
        String url = Statics.BASE_URL +"admin/edit/"+admin.getId()+"?nom="+admin.getNom()+"&prenom="+admin.getPrenom()+"&numtel="+admin.getNumtel()+"&login="+admin.getLogin()+"&password="+admin.getPassword(); 
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