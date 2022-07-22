package Entite;

import Entite.Parking;
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
import Service.SeviceParking;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author malek
 */
public class PDFParking {
      public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        SeviceParking m=new SeviceParking();
        List<Parking> list=m.afficherParking();    
        document.add(new Paragraph("La liste des Parkings :"));
        document.add(new Paragraph("     "));
         for(Parking u:list)
        {
         //  Client userRole = m.rechercherParEmail(u);
        document.add(new Paragraph("nom :"+u.getNomp()));
        document.add(new Paragraph("nombre de place:"+u.getNbplace()));
        document.add(new Paragraph("Adresse :"+u.getAdresse()));
        document.add(new Paragraph("Description:"+u.getDescription()));


        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }

  
    
       
    
}
    


