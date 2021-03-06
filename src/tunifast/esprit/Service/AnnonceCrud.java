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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Utils.DataBase;

/**
 *
 * @author mohamedazizhaddad
 */
public class AnnonceCrud {
       Connection cnx;
    Statement st;

    public AnnonceCrud() {
        cnx = DataBase.getInstance().getCnx();
    }
    public ArrayList<Annonce> checkDataAll(){
    
                  ArrayList<Annonce> res = new ArrayList<Annonce>();
           try {
               DataBase db = new DataBase();
                 String qu = "SELECT * FROM annonce INNER JOIN user "
                + "ON annonce.idUser = user.idUser AND user.role = 'chauffeur'"
                + " AND annonce.dateAnnonce > now() AND `nbrPlaceDispo` > 0";
               ResultSet rs = db.execQuery(qu);
               while (rs.next())
               { Annonce a = new Annonce();
               a.setIdAnnonce(rs.getInt("idAnnonce"));                   //Soit par label soit par indice 
                a.setIdUser(rs.getInt("idUser"));
                a.setLieuDepart(rs.getString("lieuDepart"));
                a.setLieuArrivee(rs.getString("lieuArrivee"));
                a.setDateAnnonce(rs.getString("dateAnnonce"));
                a.setHeureAnnonce(rs.getString("heureAnnonce"));
                a.setNbrPlaceDispo(rs.getInt("nbrPlaceDispo"));
                a.setNbPlaceReser(rs.getInt("NbPlaceReser"));
                a.setDateAnnPost(rs.getString("dateAnnPost"));
               res.add(a);
               
               }
           } catch (SQLException ex) {
               Logger.getLogger(AnnonceCrud.class.getName()).log(Level.SEVERE, null, ex);
           }
           return res;
    }  
         public ArrayList<Annonce> checkDataAvaliPas(){
         
                  ArrayList<Annonce> res = new ArrayList<Annonce>();
           try {
               DataBase db = new DataBase();
                 String qu = "SELECT * FROM annonce INNER JOIN user "
                + "ON annonce.idUser = user.idUser AND user.role = 'chauffeur'"
                + " AND annonce.dateAnnonce > now() AND `nbrPlaceDispo` > 0";
               ResultSet rs = db.execQuery(qu);
               while (rs.next())
               { Annonce a = new Annonce();
               a.setIdAnnonce(rs.getInt("idAnnonce"));                   //Soit par label soit par indice 
                a.setIdUser(rs.getInt("idUser"));
                a.setLieuDepart(rs.getString("lieuDepart"));
                a.setLieuArrivee(rs.getString("lieuArrivee"));
                a.setDateAnnonce(rs.getString("dateAnnonce"));
                a.setHeureAnnonce(rs.getString("heureAnnonce"));
                a.setNbrPlaceDispo(rs.getInt("nbrPlaceDispo"));
                a.setNbPlaceReser(rs.getInt("NbPlaceReser"));
                a.setDateAnnPost(rs.getString("dateAnnPost"));
               res.add(a);
               
               }
           } catch (SQLException ex) {
               Logger.getLogger(AnnonceCrud.class.getName()).log(Level.SEVERE, null, ex);
           }
           return res;
               } 
    
     public void AnnResAdd(int idAn, int nbPlARes) {
        AnnonceCrud ann = new AnnonceCrud();
        ReservationCrud res = new ReservationCrud();

        try {
            String requete2 = "UPDATE `annonce` SET `nbrPlaceDispo`=nbrPlaceDispo - "+nbPlARes+",`nbPlaceReser`=nbPlaceReser + "+nbPlARes+" WHERE `idAnnonce` =  ? ";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setInt(1, idAn);
    

            pst.executeUpdate();
            System.out.println("annoce a jour apres reservation");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     public ArrayList<Annonce> ReadAnnonce(int idan){
         
                  ArrayList<Annonce> res = new ArrayList<Annonce>();

                          
           try {
               DataBase db = new DataBase();
               String qu = "SELECT * FROM annonce INNER JOIN user ON annonce.idUser = user.idUser WHERE annonce.idAnnonce ="+idan+"";
               ResultSet rs = db.execQuery(qu);
               while (rs.next())
               { Annonce a = new Annonce();
               a.setIdAnnonce(rs.getInt("idAnnonce"));                   //Soit par label soit par indice 
                a.setIdUser(rs.getInt("idUser"));
                a.setLieuDepart(rs.getString("lieuDepart"));
                a.setLieuArrivee(rs.getString("lieuArrivee"));
                a.setDateAnnonce(rs.getString("dateAnnonce"));
                a.setHeureAnnonce(rs.getString("heureAnnonce"));
                a.setNbrPlaceDispo(rs.getInt("nbrPlaceDispo"));
                a.setNbPlaceReser(rs.getInt("NbPlaceReser"));
                a.setNomUser(rs.getString("nom"));
                a.setDateAnnPost(rs.getString("dateAnnPost"));
               res.add(a);
               
               }
           } catch (SQLException ex) {
               Logger.getLogger(AnnonceCrud.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           return res;
               } 
         
           public ArrayList<Annonce> ReadAnnonceChauf(int iduser){
         
                  ArrayList<Annonce> res = new ArrayList<Annonce>();

                          
           try {
               DataBase db = new DataBase();
               String qu = "SELECT * from annonce WHERE idUser = "+iduser+" AND dateAnnonce > now()";
               ResultSet rs = db.execQuery(qu);
               while (rs.next())
               { Annonce a = new Annonce();
               a.setIdAnnonce(rs.getInt("idAnnonce"));                   //Soit par label soit par indice 
                a.setIdUser(rs.getInt("idUser"));
                a.setLieuDepart(rs.getString("lieuDepart"));
                a.setLieuArrivee(rs.getString("lieuArrivee"));
                a.setDateAnnonce(rs.getString("dateAnnonce"));
                a.setHeureAnnonce(rs.getString("heureAnnonce"));
                a.setNbrPlaceDispo(rs.getInt("nbrPlaceDispo"));
                a.setNbPlaceReser(rs.getInt("NbPlaceReser"));
                a.setDateAnnPost(rs.getString("dateAnnPost"));
               res.add(a);
               
               }
           } catch (SQLException ex) {
               Logger.getLogger(AnnonceCrud.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           return res;
               } 
    
     
}
