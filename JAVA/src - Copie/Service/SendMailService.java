/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Connection;
import java.util.Properties;
import javafx.event.ActionEvent;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utils.Connexion;

/**
 *
 * @author hp
 */
public class SendMailService {
      Connection cnx; 

    public SendMailService() {
        cnx = Connexion.getInstance().getConnection(); 
    }
    
      public void SendMail(String recepient) throws MessagingException {
        ServiceClient sc = new ServiceClient();
        //String sql = "Select nom_client from client where DATEDIFF(NOW(),date_naiss)=0)   + ";
       // System.out.println(sql);
        System.out.println("Entrain d'envoyer l'email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "esprit2022.test@gmail.com";
        //Your gmail password
        String password = "esprittest";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
      
 
        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient );

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
           
            message.setSubject("Bonjour Cher Client");
            String content="Bonjour cher client! Nous aimerons vous célébrer avec ce Coupon de Reduction de 20% , valable sur tout le site, pour Aujourd'hui";
    
            message.setContent(content, "text/html");
            return message;
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
}
