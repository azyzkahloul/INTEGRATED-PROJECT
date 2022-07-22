/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;
import Entite.Admin;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import Service.AdminService;

/**
 *
 * @author malek
 */
public class PDFAdmin {
        public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        AdminService m=new AdminService();
        List<Admin> list=m.afficherAdmin();    
        document.add(new Paragraph("La liste des Admins :"));
        document.add(new Paragraph("     "));
         for(Admin u:list)
        {
         //  Client userRole = m.rechercherParEmail(u);
        document.add(new Paragraph("nom :"+u.getNom()));
        document.add(new Paragraph("prenom :"+u.getPrenom()));
        document.add(new Paragraph("login :"+u.getLogin()));
        document.add(new Paragraph("password:"+u.getPassword()));
        document.add(new Paragraph("password:"+u.getNumtel()));

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }

  
    
       
}
