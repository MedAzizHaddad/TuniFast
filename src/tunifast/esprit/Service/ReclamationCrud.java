/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tunifast.esprit.Entitie.Reclamation;
import tunifast.esprit.Entitie.Reservation;
import tunifast.esprit.Entitie.User;
import tunifast.esprit.Utils.DataBase;

/**
 *
 * @author mohamedazizhaddad
 */
public class ReclamationCrud {
    
    Connection cnx;
    Statement st;
    
    public ReclamationCrud() {
        cnx = DataBase.getInstance().getCnx();
    }
    
    public void ajouterReclamation(Reclamation rec) {
        try {
            String requete2 = "INSERT INTO `reclamation`( `userReporter`, `userReported`,"
                    + " `dateReclamation`, `details`, `etatReclamation`) VALUES (?,?,now(),?,'en attente')";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, rec.getUserReporter());
            pst.setString(2, rec.getUserReported());
            pst.setString(3, rec.getDetails());
            
            pst.executeUpdate();
            System.out.println("reclamation créer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public ArrayList<Reclamation> UserConsRec(String us) {
        
        ArrayList<Reclamation> result = new ArrayList<Reclamation>();
        try {
            String requete3 = "SELECT * from reclamation "
                    + "WHERE userReporter=" + us + "  ";
            
            PreparedStatement pst2 = cnx.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
             Reclamation rec = new Reclamation();
            while (rs.next()) {
               
                rec.setIdReclamation(rs.getInt("idReclamation"));
                rec.setDateReclamation(rs.getString("dateReclamation"));
    
                
                rec.setDetails(rs.getString("details"));
                rec.setEtatUser(rs.getString("etatUser"));
                rec.setEtatReclamation(rs.getString("etatReclamation"));
                rec.setUserReporter(rs.getString("userReporter"));
                rec.setUserReported(rs.getString("userReported"));

             
                result.add(rec);
                
            }
            
        } catch (SQLException ex) {
            
        }
        
        return result;
    }
    
}
