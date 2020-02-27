/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Service;

import tunifast.esprit.Entitie.rate;
import tunifast.esprit.Entitie.stat;
import tunifast.esprit.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Youssef
 */
public class rateCrud {

    Connection cn3;
    Statement st;

    public rateCrud() {
        cn3 = DataBase.getInstance().getCnx();
    }

    public void ajouterRate(rate r) {
        statserv ss = new statserv();
        ss.remplir();
        try {
            String requete = "INSERT INTO rate (idRate,nbrRate,feedback,idUser,idAnnonce) VALUES (?,?,?,?,?)";
            PreparedStatement pst3 = cn3.prepareStatement(requete);
            pst3.setInt(1, r.getIdRate());
            pst3.setDouble(2, r.getNbrRate());
            pst3.setString(3, r.getFeedback());
            pst3.setInt(4, r.getIdUser());
            pst3.setInt(5, r.getIdAnnonce());
            pst3.executeUpdate();

            String requete1 = "SELECT nbrRate FROM rate WHERE idUser = ?";
            PreparedStatement pst4 = cn3.prepareStatement(requete1);
            pst4.setInt(1, r.getIdUser());

            System.out.println("Rate ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        ///////////////////rating
        float moyenne = 0;
        int numb = 0;
        try {

            String requete = "SELECT nbrRate FROM rate WHERE idUser = ? ";
            PreparedStatement pst2 = cn3.prepareStatement(requete);
            pst2.setInt(1, r.getIdUser());
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                int AA;
                AA = rs.getInt("nbrRate");
                moyenne = moyenne + AA;
                numb = numb + 1;

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        if (numb != 0) {
            moyenne = moyenne / numb;
        }
        System.out.println(moyenne);

        PreparedStatement pst3;
        try {
            String requete2 = "UPDATE stat SET moyRate=? WHERE idUser=? ";
            pst3 = cn3.prepareStatement(requete2);
            pst3.setFloat(1, moyenne);
            pst3.setInt(2, r.getIdUser());
            pst3.executeUpdate();
            System.out.println("Done");

        } catch (SQLException ex) {
        }

        //////////////////////
    }

    public void ModifierRate(rate r, int nr, String fb) {
        try {
            String requete = "UPDATE rate SET nbrRate=? ,feedback=?  WHERE idAnnonce=? ";
            PreparedStatement pst3 = cn3.prepareStatement(requete);
            pst3.setInt(1, nr);
            pst3.setString(2, fb);
            pst3.setInt(3, r.getIdAnnonce());
            pst3.executeUpdate();
            System.out.println("Rate Modifier avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SupprimerRate(rate r) {
        try {
            String requete = "DELETE FROM rate WHERE idAnnonce=? ";
            PreparedStatement pst3 = cn3.prepareStatement(requete);
            pst3.setInt(1, r.getIdAnnonce());
            pst3.executeUpdate();
            System.out.println("rate supprimer avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<rate> afficherRate() {

        ArrayList<rate> rat = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM rate";
            PreparedStatement pst3 = cn3.prepareStatement(requete3);
            ResultSet rs = pst3.executeQuery();

            while (rs.next()) {
                rate r = new rate();
                r.setIdRate(rs.getInt("IdRate"));
                r.setNbrRate(rs.getInt("NbrRate"));
                r.setFeedback(rs.getString("Feedback"));
                r.setIdUser(rs.getInt("IdUser"));
                r.setIdAnnonce(rs.getInt("IdAnnonce"));
                rat.add(r);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rat;
    }
}
