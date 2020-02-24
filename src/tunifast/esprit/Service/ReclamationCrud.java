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
import java.util.logging.Level;
import java.util.logging.Logger;
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
                    + " `dateReclamation`, `details`, `etatReclamation` , `etatUser` ) VALUES (?,?,now(),?,'en attente','active')";
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
                    + "WHERE userReporter='" + us + " ' ";

            PreparedStatement pst2 = cnx.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Reclamation rec = new Reclamation();
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

    public ArrayList<Reclamation> adminConsRec() {

        ArrayList<Reclamation> result = new ArrayList<Reclamation>();
        try {
            String requete3 = "SELECT * from reclamation ";

            PreparedStatement pst2 = cnx.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Reclamation rec = new Reclamation();
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
    
    public String getEtatRecById(int id) {
        ArrayList<Reclamation> result = new ArrayList<Reclamation>();
        try {
            String requete3 = "SELECT * from reclamation "
                    + "WHERE idReclamation='" + id + " ' ";

            PreparedStatement pst2 = cnx.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setEtatReclamation(rs.getString("etatReclamation"));
                result.add(rec);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result.get(0).getEtatReclamation();
    }

    public Reclamation getRecById(int id) {
        ArrayList<Reclamation> result = new ArrayList<Reclamation>();
        try {
            String requete3 = "SELECT * from reclamation "
                    + "WHERE idReclamation='" + id + " ' ";

            PreparedStatement pst2 = cnx.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                Reclamation rec = new Reclamation();
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
            Logger.getLogger(ReclamationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result.get(0);
    }
    
    public void updateDetById(int id , String det){
         try {
            String requete2 = "UPDATE `reclamation` SET `details`='"+det+"'WHERE `idReclamation` ="+id+" ";
            PreparedStatement pst = cnx.prepareStatement(requete2);
          
    

            pst.executeUpdate();
            System.out.println("reclamation modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void setEtatUser(int idR){
         try {
            String requete2 = "UPDATE `reclamation` SET `etatUser`='Suspendu'WHERE `idReclamation` ="+idR+" ";
            PreparedStatement pst = cnx.prepareStatement(requete2);
         
            pst.executeUpdate();
            System.out.println("reclamation modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void setEtatRec(int idR){
         try {
            String requete2 = "UPDATE `reclamation` SET `etatReclamation`='validé'WHERE `idReclamation` ="+idR+" ";
            PreparedStatement pst = cnx.prepareStatement(requete2);
         
            pst.executeUpdate();
            System.out.println("reclamation modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
