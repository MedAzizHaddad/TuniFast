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
import tunifast.esprit.Entitie.Reservation;
import tunifast.esprit.Entitie.User;
import tunifast.esprit.Utils.DataBase;

/**
 *
 * @author mohamedazizhaddad
 */
public class ReservationCrud {

    Connection cnx;
    Statement st;

    public ReservationCrud() {
        cnx = DataBase.getInstance().getCnx();
    }

    public void ResResAdd(int idAn, int idP, int nbPlARes, int montant) {
        try {
            String requete2 = "INSERT INTO `reservation`(`idReservation`, `idAnnonce`, `idUser`, `dateReservation`, `nbPlace`, `montant` , `etatReservation`) "
                    + "VALUES (?,?,?,now(),?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setInt(1, 0);
            pst.setInt(2, idAn);
            pst.setInt(3, idP);
            pst.setInt(4, nbPlARes);
            pst.setInt(5, montant);
            pst.setString(6, "non payé");

            pst.executeUpdate();
            System.out.println("reservation créer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Reservation> consMesResPa(int idP) {

        ArrayList<Reservation> result = new ArrayList<Reservation>();
        try {
            String requete3 = "SELECT * "
                    + "FROM annonce \n"
                    + "INNER JOIN reservation\n"
                    + "ON annonce.idAnnonce = reservation.idAnnonce\n"
                    + "WHERE reservation.idUser =" + idP + " AND annonce.dateAnnonce > now()  ";

            PreparedStatement pst2 = cnx.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Reservation p = new Reservation();
                p.setIdReservation(rs.getInt("idReservation"));
                p.setIdAnnonce(rs.getInt("idAnnonce"));
                p.setIdUser(rs.getInt("idUser"));
                p.setDateReservation(rs.getString("dateReservation"));
                p.setEtatReservation(rs.getString("etatReservation"));
                p.setLieuDepart(rs.getString("lieuDepart"));
                p.setLieuArrivee(rs.getString("lieuArrivee"));
                p.setDateAnnonce(rs.getString("dateAnnonce"));
                p.setHeureAnnonce(rs.getString("heureAnnonce"));
                p.setNbPlace(rs.getInt("nbPlace"));
                p.setMontant(rs.getInt("montant"));

                //Soit par label soit par indice 
                p.setIdUser(idP);
                ;
                // System.out.println(p);
                result.add(p);

            }

        } catch (SQLException ex) {

        }

        return result;
    }

    public void AnnulerResPas(int idAn, int idRs, int nbPl) {

        try {
            String requete1 = "UPDATE `reservation` SET `etatReservation`='annulé' WHERE `idReservation` = " + idRs + " ";
            PreparedStatement pst;
            pst = cnx.prepareStatement(requete1);
            pst.executeUpdate();
            //System.out.println("reservation annulé !");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String requete2 = "UPDATE `annonce` SET `nbrPlaceDispo`= nbrPlaceDispo + " + nbPl + " ,"
                    + "`nbPlaceReser`= nbPlaceReser - " + nbPl + "   WHERE `idAnnonce` = " + idAn + " ";
            PreparedStatement pst;
            pst = cnx.prepareStatement(requete2);
            pst.executeUpdate();
            System.out.println("reservation annulé !");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public ArrayList<Reservation> listResChaufAn(int idAn) { 
        ArrayList<Reservation> result = new ArrayList<Reservation>();
        try {
 
              DataBase db = new DataBase();
               String qu =  "SELECT * FROM reservation "
                    + "INNER JOIN user ON user.idUser = reservation.idUser "
                    + "INNER JOIN annonce ON annonce.idAnnonce = reservation.idAnnonce "
                    + "WHERE reservation.idAnnonce = " + idAn + " "
                    + "AND annonce.dateAnnonce > now()  ";
               ResultSet rs = db.execQuery(qu);
               
            while (rs.next()) {
                User a = new User();
                Reservation p = new Reservation();
                p.setIdReservation(rs.getInt("idReservation"));
                p.setIdAnnonce(rs.getInt("idAnnonce"));
                p.setIdUser(rs.getInt("idUser"));
                p.setDateReservation(rs.getString("dateReservation"));
                p.setEtatReservation(rs.getString("etatReservation"));
                p.setLieuDepart(rs.getString("lieuDepart"));
                p.setLieuArrivee(rs.getString("lieuArrivee"));
                p.setDateAnnonce(rs.getString("dateAnnonce"));
                p.setHeureAnnonce(rs.getString("heureAnnonce"));
                p.setNbPlace(rs.getInt("nbPlace"));
                p.setMontant(rs.getInt("montant"));
                a.setNom(rs.getString("nom"));
                p.setUser(a);
                result.add(p);

            }

        } catch (SQLException ex) {

        }

        return result;
    }

    public ArrayList<Reservation> resUserDetail(int idU) {

        ArrayList<Reservation> result = new ArrayList<Reservation>();
       
        try {
            String requete3 = "SELECT * FROM reservation "
                    + "INNER JOIN USER ON user.idUser = reservation.idUser"
                    + " WHERE idReservation  = " + idU + " ";

            PreparedStatement pst2 = cnx.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            User u = new User();
            Reservation res = new Reservation();
            while (rs.next()) {
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setNumTel(rs.getInt("numTel"));
                u.setMail(rs.getString("mail"));
                res.setUser(u);
                res.setDateReservation(rs.getString("dateReservation"));
                res.setNbPlace(rs.getInt("nbPlace"));
                res.setEtatReservation(rs.getString("etatReservation"));
                result.add(res);

            }

        } catch (SQLException ex) {

        }

        return result;

    }
    
public int test(int x){
    return x*x;
}
}
