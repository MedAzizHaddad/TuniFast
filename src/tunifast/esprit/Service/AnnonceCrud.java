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
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Entitie.User;
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
               User u = new User();
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
                u.setNom(rs.getString("nom"));
                a.setUser(u);
               res.add(a);
               
               }
           } catch (SQLException ex) {
               Logger.getLogger(AnnonceCrud.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           return res;
               } 
         
       public ArrayList<Annonce> ReadAnnonce2(int idan){
         
                  ArrayList<Annonce> res = new ArrayList<Annonce>();

                          
           try {
               DataBase db = new DataBase();
               String qu = "SELECT * FROM annonce INNER JOIN user ON annonce.idUser = user.idUser WHERE annonce.idAnnonce ="+idan+"";
               ResultSet rs = db.execQuery(qu);
               while (rs.next())
               { Annonce a = new Annonce();
               User u = new User();
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
                u.setNom(rs.getString("nom"));
                a.setUser(u);
                a.setPrix(rs.getDouble("prix"));
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
    
        
           
           public ArrayList<Annonce> getLastAnnoncePas(){
         
                  ArrayList<Annonce> res = new ArrayList<Annonce>();

                          
           try {
               DataBase db = new DataBase();
               String qu = "select *from annonce INNER JOIN user ON user.idUser = annonce.idUser WHERE type = 'passager' ORDER BY idAnnonce DESC LIMIT 1 ";
               ResultSet rs = db.execQuery(qu);
               while (rs.next())
               { Annonce a = new Annonce();
                User u = new User();
               u.setUsername(rs.getString("username")); 
               a.setIdAnnonce(rs.getInt("idAnnonce"));                   //Soit par label soit par indice 
                a.setIdUser(rs.getInt("idUser"));
                a.setLieuDepart(rs.getString("lieuDepart"));
                a.setLieuArrivee(rs.getString("lieuArrivee"));
                a.setDateAnnonce(rs.getString("dateAnnonce"));
                a.setHeureAnnonce(rs.getString("heureAnnonce"));
                a.setNbrPlaceDispo(rs.getInt("nbrPlaceDispo"));
                a.setNbPlaceReser(rs.getInt("NbPlaceReser"));
   a.setUser(u);
                a.setDateAnnPost(rs.getString("dateAnnPost"));
               res.add(a);
               
               }
           } catch (SQLException ex) {
               Logger.getLogger(AnnonceCrud.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           return res;
               } 
   
                public ArrayList<Annonce> getLastAnnonceChauf(){
         
                  ArrayList<Annonce> res = new ArrayList<Annonce>();

                          
           try {
               DataBase db = new DataBase();
               String qu = "select *from annonce INNER JOIN user ON user.idUser = annonce.idUser WHERE type = 'chauffeur' ORDER BY idAnnonce DESC LIMIT 1";
               ResultSet rs = db.execQuery(qu);
               while (rs.next())
               { Annonce a = new Annonce();
               User u = new User();
               u.setUsername(rs.getString("username"));
               a.setIdAnnonce(rs.getInt("idAnnonce"));                   //Soit par label soit par indice 
                a.setIdUser(rs.getInt("idUser"));
                a.setLieuDepart(rs.getString("lieuDepart"));
                a.setLieuArrivee(rs.getString("lieuArrivee"));
                a.setDateAnnonce(rs.getString("dateAnnonce"));
                a.setHeureAnnonce(rs.getString("heureAnnonce"));
                a.setNbrPlaceDispo(rs.getInt("nbrPlaceDispo"));
                a.setNbPlaceReser(rs.getInt("NbPlaceReser"));
                a.setUser(u);
                a.setDateAnnPost(rs.getString("dateAnnPost"));
               res.add(a);
               
               }
           } catch (SQLException ex) {
               Logger.getLogger(AnnonceCrud.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           return res;
               } 
   
    public void ajoutAnnonce (String dep , String arr , LocalDate date , LocalTime heure  , int place, int idUser){
        
        System.out.println(date + "hii " + heure);
          try {
            String requete2 = "INSERT INTO `annonce`( `lieuDepart` , `lieuArrivee` , `dateAnnonce`, `heureAnnonce` , `nbrPlaceDispo` , `idUser` , `dateAnnPost` , `type`)"
                    + " VALUES ('"+dep+"','"+arr+"' ,'"+date+"' , '"+heure+"',"+place+"," +idUser+" , now(), 'chauffeur')";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.executeUpdate();
            System.out.println("annonce créer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
                
             public ArrayList<Annonce> getAllChauAnnonceById(int idU){
    
                  ArrayList<Annonce> res = new ArrayList<Annonce>();
           try {
               DataBase db = new DataBase();
                 String qu = "SELECT * FROM `annonce` WHERE `nbrPlaceDispo` > 0 AND `dateAnnonce` > now() AND`type`='chauffeur' AND `idUser` ="+idU+"";
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
     public void updateAnnonce (Annonce an , int idA){
     
          try {
            String requete2 = "UPDATE `annonce` SET `lieuDepart`=? ,`lieuArrivee`=? ,`nbrPlaceDispo`=? , `dateAnnonce`=? ,  " +
" `heureAnnonce`= ? WHERE `idAnnonce` = "+idA+" ";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, an.getLieuDepart());
            pst.setString(2, an.getLieuArrivee());
             pst.setInt(3, an.getNbrPlaceDispo());
            pst.setString(4, an.getDateAnnonce());
            pst.setString(5, an.getHeureAnnonce());                                 
            pst.executeUpdate();
            System.out.println("annoce mdifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
         
         
         
     }           
}
