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
import com.mycompany.myapp.entities.Client;
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
public class ServiceClient{
    
    //singleton 
    public static ServiceClient instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceClient getInstance() {
        if(instance == null )
            instance = new ServiceClient();
        return instance ;
    }
    
    public ServiceClient() {
        req = new ConnectionRequest(); 
    }
    
    
    //ajout 
    public boolean addClient(Client cl) {
        
        String url =Statics.BASE_URL+"addClient?nom="+cl.getNom()+"&prenom="+cl.getPrenom()+
                "&numtel"+cl.getNumtel()+"&voitmat"+cl.getVoitmat()+"&instamonnaie"+cl.getInstamonnaie()+
                "&login"+cl.getLogin()+"&password"+cl.getPassword()+"&statut"+cl.getStatut();
        
        req.setUrl(url);
        // req.setPost(false);
       req.addArgument("nom", cl.getNom()+"");
       req.addArgument("prenom", cl.getPrenom()+"");
       req.addArgument("numtel", cl.getNumtel()+"");
       req.addArgument("voitmat", cl.getVoitmat()+"");
       req.addArgument("instamonnaie", cl.getInstamonnaie()+"");
       req.addArgument("login", cl.getLogin()+"");
       req.addArgument("password", cl.getPassword()+"");
       req.addArgument("statut", cl.getStatut()+"");

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
    
    public ArrayList<Client>showClient() {
        ArrayList<Client> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"allavis";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapClient = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapClient.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Client re = new Client();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("nom").toString();
                        String prenom = obj.get("prenom").toString();
                        String numtel = obj.get("numtel").toString();
                        String voitmat = obj.get("voitmat").toString();
                        String instamonnaie = obj.get("instamonnaie").toString();
                        String login = obj.get("login").toString();
                        String password = obj.get("password").toString();
                        String statut = obj.get("statut").toString();

                        re.setId((int)id);
                        re.setNom(nom);
                        re.setPrenom(prenom);
                        re.setNumtel(numtel);
                        re.setVoitmat(voitmat);
                        re.setInstamonnaie(instamonnaie);
                        re.setLogin(login);
                        re.setPassword(password);
                        re.setStatut(statut);
                        
                        result.add(re);
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
     
    
    //Detail Client bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Client DetailClient( int id , Client cl) {
        
        String url = Statics.BASE_URL+"showid/"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                cl.setNom(obj.get("nom").toString());
                cl.setPrenom(obj.get("prenom").toString());
                cl.setPrenom(obj.get("numtel").toString());
                cl.setPrenom(obj.get("voitmat").toString());
                cl.setPrenom(obj.get("instamonnaie").toString());
                cl.setPrenom(obj.get("login").toString());
                cl.setPrenom(obj.get("password").toString());
                cl.setPrenom(obj.get("statut").toString());

            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);  
           
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return cl;
    }
    
    
    //Delete 
    public boolean deleteClient(int id ) {
        String url = Statics.BASE_URL +"deleteid/"+id;
        
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
    public boolean updateClient(Client cl) {
        String url = Statics.BASE_URL +"updateJSON?id="+cl.getId()+"&nom="+cl.getNom()+"&prenom="+
                cl.getPrenom()+"&numtel"+cl.getNumtel()+"&voitmat"+cl.getVoitmat()+"&instamonnaie"+
                cl.getInstamonnaie()+"&login"+cl.getLogin()+"&password"+cl.getPassword()+
                "&statut"+cl.getStatut();
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