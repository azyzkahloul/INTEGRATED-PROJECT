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

import com.mycompany.myapp.entities.Facture;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Azyz
 */
public class ServiceFacture {
     public ArrayList<Facture> factures;
    
    
     //singleton 
    public static ServiceFacture instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceFacture getInstance() {
        if(instance == null )
            instance = new ServiceFacture();
        return instance ;
     }
   

 public ServiceFacture() {
        req = new ConnectionRequest(); 
    }
 
 //ajout 
    public boolean addFacture(Facture fa) {
        
        String url =Statics.BASE_URL+"facture/new?nbheure="+fa.getNbheure()+"&pu="+fa.getPu()+
                "&total="+fa.getTotal()+"&dateentrer="+fa.getDateentrer();
        
        req.setUrl(url);
        // req.setPost(false);
       req.addArgument("N Heure(s) : ", fa.getNbheure()+"");
       req.addArgument("Prix Unitaire : ", fa.getPu()+"");
       req.addArgument("Total : ", fa.getTotal()+"");
       req.addArgument("Date : ", fa.getDateentrer()+"");
      

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
    
    public ArrayList<Facture>showFacture() {
        req.setUrl(Statics.BASE_URL + "facture/");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                   factures = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        Facture e;
                        e = new Facture(
                               (int) Float.parseFloat(obj.get("id_facture").toString()),
                                (String) obj.get("nbheure"),
                                (String) obj.get("pu"),
                                (String) obj.get("total"),
                                (String) obj.get("dateentrer")                                                               
                                
                        );
                        
                        factures.add(e);
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
        return factures;
    }
    /*
        ArrayList<Facture> result = new ArrayList<>();
        
        String url =Statics.BASE_URL+"facture/";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapFacture = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapFacture.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Facture re = new Facture();
                        
                        //dima id fi codename one float 5outhouha
                        float id_facture = Float.parseFloat(obj.get("id_facture").toString());
                        
                        String nbheure = obj.get("nbheure").toString();
                        String pu = obj.get("pu").toString();
                        String total = obj.get("total").toString();
                        String dateentrer = obj.get("dateentrer").toString();
                  
                        
                      
                        re.setId_facture((int)id_facture);
                        re.setNbheure(nbheure);
                        re.setPu(pu);
                        re.setTotal(total);
                        re.setDateentrer(dateentrer);
           
                       
                        
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
         
        
    }*/
     
  //Detail Facture bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Facture DetailFacture( int id_facture , Facture fa) {
        
    String url = Statics.BASE_URL+"facture/?"+id_facture;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                fa.setNbheure(obj.get("nbheure").toString());
                fa.setPu(obj.get("pu").toString());
                fa.setTotal(obj.get("total").toString());
                fa.setDateentrer(obj.get("dateentrer").toString());
            
                
                
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return fa;
        
        
    }
    
   //Delete 
    public boolean deleteFacture(int id_facture ) {
        String url = Statics.BASE_URL+"facture/delete/"+id_facture;
        
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
    public boolean updateFacture(Facture fa) {
        String url = Statics.BASE_URL +"facture/"+fa.getId_facture()+"/edit?id_facture="+fa.getId_facture()+"&nbheure="+fa.getNbheure()+"&pu="+
                fa.getPu()+"&total="+fa.getTotal()+"&dateentrer="+fa.getDateentrer();
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