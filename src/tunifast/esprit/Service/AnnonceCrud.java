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
    public void checkDataAll(){
           try {
               DataBase db = new DataBase();
               String qu = "SELECT * FROM annonce";
               ResultSet  rs = db.execQuery(qu);
               while (rs.next()){
                   int id = rs.getInt("idAnnonce");
                   System.out.println(id);
               }  } catch (SQLException ex) {
               Logger.getLogger(AnnonceCrud.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    public void checkDataAvaliPas(){
       ArrayList<Annonce>  result = new ArrayList<Annonce>() ;
       ResultSet  rs = null ;
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        DataBase db = new DataBase();
        String qu = "SELECT * FROM annonce INNER JOIN user "
                + "ON annonce.idUser = user.idUser AND user.role = 'chauffeur'"
                + " AND annonce.dateAnnonce > now() AND `nbrPlaceDispo` > 0";
           
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
}
