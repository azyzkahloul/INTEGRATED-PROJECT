/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entite.Mail;
import javax.mail.Authenticator;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.activation.*; 
import javax.mail.*; 


/**
 * FXML Controller class
 *
 * @author IMNA
 */
public class ParmailController implements Initializable {
    @FXML
    private TextField txt_Email;
    
    @FXML
    private TextField txtCode;

    int randomCode ; 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void Send(MouseEvent event) throws MessagingException{
    
        Random rand= new Random();
    randomCode= rand.nextInt(999999);
    String subject = "reseting Code";
    String message = "Your reset code is "+randomCode;
    String user = "esprit2022.test@gmail.com";
    String pass = "esprittest";
    String to = txt_Email.getText();
    Mail.send(
    user,
    pass,
    to,
    subject,
    message
  );
    
    
    
    boolean sessionDebug = false ; 
    Properties pros = System.getProperties();
//        pros.put("mail.smtp.starttls.enable", "true");
//        pros.put("mail.smtp.starttls.required", "true");
//        pros.put("mail.smtp.auth", "true");
//        pros.put("mail.smtp.host", "smtp.gmail.com");
//        pros.put("mail.smtp.port", "587");
//        pros.setProperty("mail.smtp.ssl.enable", "true");
//       pros.setProperty("mail.smtp.ssl.socketFactory.class","DummySSLSocketFactory");
//        pros.setProperty("mail.smtp.ssl.socketFactory.fallback", "false");

//pros.put("mail.smtp.user",user); 
//pros.put("mail.smtp.host",host); 
//pros.put("mail.smtp.port", "2525"); 
//
//pros.put("mail.debug", "true"); 
//pros.put("mail.smtp.auth", "true"); 
//pros.put("mail.smtp.starttls.enable","true"); 
//pros.put("mail.smtp.EnableSSL.enable","true");

//pros.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
//pros.setProperty("mail.smtp.socketFactory.fallback", "false");   
//pros.setProperty("mail.smtp.port", "587");   
//pros.setProperty("mail.smtp.socketFactory.port", "587"); 

          java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        //Session mailSession = Session.getDefaultInstance(pros,null) ;
          Session mailSession = Session.getInstance(pros,
          new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
          }); 
    //Session mailSession = Session.getDefaultInstance(pros);
    mailSession.setDebug(sessionDebug);
    try {
    Message msg= new MimeMessage(mailSession);
    msg.setFrom(new InternetAddress(user));
//        InternetAddress [] address = {new InternetAddress(to)};
//    msg.setRecipients(Message.RecipientType.TO, address);
    msg.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to));
    
    msg.setSubject(subject);
    msg.setText(message);
//        Transport transport = mailSession.getTransport("smtp");
//        transport.connect("smtp.gmail.com",user, pass);
//        transport.sendMessage(msg, msg.getAllRecipients());
         Transport.send(msg);
        //transport.close();
        JOptionPane.showMessageDialog(null, "Code est envoyée à votre boite mail");
    
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
        
 }
    
    @FXML
    void Verify(MouseEvent event) {
                             Alert alert = new Alert (Alert.AlertType.ERROR);

    if (Integer.valueOf(txtCode.getText())==randomCode)
    {
         //ResetController resC = new ResetController(txt_Email.getText());
                try {
            alert.setContentText("Code vérifié veuillez saisir un nouveau mot de passe");
            Parent parent = FXMLLoader.load(getClass().getResource("Reset.fxml"));
            Scene scene = new Scene(parent);
            Stage consstage = new Stage();
            consstage.setScene(scene);
            consstage.initStyle(StageStyle.UTILITY);
            consstage.show();
            alert.setContentText("Code vérifié veuillez saisir un nouveau mot de passe");


                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }else {
    //JOptionPane.showMessageDialog(null, "code do not match");
    alert.setTitle("le code est invalide !!");
            alert.setHeaderText("Veuillez saisir le code correctement");
            alert.setContentText("Il faut consulter votre boite email pour récuperer le code de validation");
            alert.showAndWait();
    }
    }
    
}

