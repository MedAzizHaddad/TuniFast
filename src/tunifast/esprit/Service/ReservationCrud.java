/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
    
     public void ResResAdd(int idAn, int idP, int nbPlARes, int montant){
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
    
}
