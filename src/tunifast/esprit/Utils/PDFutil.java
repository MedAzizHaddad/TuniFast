/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Utils;

/**
 *
 * @author mohamedazizhaddad
 */

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import tunifast.esprit.Entitie.User;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Dimassi Abdelhak
 */
public class PDFutil {

    Connection cn2;
    Statement ste;

    public PDFutil() {
        cn2 = DataBase.getInstance().getCnx();
    }

    public void listUtilisateurs() throws SQLException, FileNotFoundException, DocumentException, IOException {
        Document doc = new Document();

        ste = cn2.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM user");
        PdfWriter.getInstance(doc, new FileOutputStream("./Liste Utilisateurs.pdf"));

        doc.open();
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph("  Liste des utilisateurs:  "));
        doc.add(new Paragraph("   "));

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        PdfPCell cell;

        cell = new PdfPCell(new Phrase("ID Utilisateur", FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nom", FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Prenom", FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Mot de passe", FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Sexe", FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Mail", FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Telephone", FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Role", FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);

        while (rs.next()) {

            User u = new User();
            u.setIdUser(rs.getInt("idUser"));                   //Soit par label soit par indice 
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setPassword(rs.getString("password"));
            u.setSexe(rs.getString("sexe"));
            u.setMail(rs.getString("mail"));
            u.setNumTel(rs.getInt("numTel"));
            u.setRole(rs.getString("role"));

            /*cell = new PdfPCell(new Phrase(String.valueOf(a.getId()),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.white);
               table.addCell(cell);*/
            
            cell = new PdfPCell(new Phrase(String.valueOf(u.getIdUser()), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.white);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(u.getNom(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.white);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(u.getPrenom(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.white);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(u.getPassword(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.white);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(u.getSexe(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.white);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(u.getMail(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.white);
            table.addCell(cell);

            String num  = Integer.toString(u.getNumTel());
            
            cell = new PdfPCell(new Phrase(num , FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.white);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(u.getRole(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.white);
            table.addCell(cell);
        }
        doc.add(table);
        doc.close();
        Desktop.getDesktop().open(new File("./Liste Utilisateurs.pdf"));
    }
}
