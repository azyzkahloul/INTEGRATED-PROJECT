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
    public ArrayList<Client> clients;
    
    
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
    public boolean addClient(Client fa) {
        //http://127.0.0.1:8000/client/mobile/new?nom="azyz"&prenom="Kahloul"&numtel="26101992"&voitmat="tuns212"&login="azyzkahloul"&password="123456789Azyz
        String url =Statics.BASE_URL+"client/mobile/new?nom="+fa.getNom()+"&prenom="+fa.getPrenom()+
                "&numtel="+fa.getNumtel()+"&voitmat="+fa.getVoitmat()+"&login="+fa.getLogin()+"&password="+fa.getPassword();
        
        req.setUrl(url);
        // req.setPost(false);
       req.addArgument("Nom : ", fa.getNom()+"");
       req.addArgument("Prénom : ", fa.getPrenom()+"");
       req.addArgument("Numéro Tel : ", fa.getNumtel()+"");
       req.addArgument("Login : ", fa.getLogin()+"");
       req.addArgument("Password : ", fa.getPassword()+"");

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
        
        req.setUrl(Statics.BASE_URL+"client/mobile/");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                   clients = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        Client e;
                        e = new Client(
                               (int) Float.parseFloat(obj.get("id").toString()),
                                (String) obj.get("nom"),
                                (String) obj.get("prenom"),
                                (String) obj.get("numtel"),                                                              
                                (String) obj.get("voitmat"),
                                (String) obj.get("login"),
                                (String) obj.get("password")
                        );
                        
                        clients.add(e);
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
        return clients;
        
        
    }
   
  //Detail Facture bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Client DetailClient( int id_client , Client fa) {
        //http://127.0.0.1:8000/clientmobile/1
    String url = Statics.BASE_URL+"clientmobile/?"+id_client;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                fa.setNom(obj.get("nom").toString());
                fa.setPrenom(obj.get("prenom").toString());
                fa.setNumtel(obj.get("numtel").toString());
                fa.setVoitmat(obj.get("voitmat").toString());
                fa.setLogin(obj.get("login").toString());
                fa.setPassword(obj.get("password").toString());
                
                
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return fa;
        
        
    }
    
   //Delete 
    public boolean deleteClient(int id_client ) {
        //http://127.0.0.1:8000/client/4/deletemobile
        String url = Statics.BASE_URL+"client/"+id_client+"/deletemobile";
        
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
    public boolean updateClient(Client fa) {
        //http://127.0.0.1:8000/client/editmobile/1?nom="Kahloul"&prenom="Azyz"&numtel="26101992"&login="mouhanned"&password="smaoui
        String url = Statics.BASE_URL +"client/editmobile/"+fa.getId_client()+"?nom="+fa.getNom()+"&prenom="+
                fa.getPrenom()+"&numtel="+fa.getNumtel()+"&voitmat="+fa.getVoitmat()+"&login="+fa.getLogin()+"&password="+fa.getPassword();
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