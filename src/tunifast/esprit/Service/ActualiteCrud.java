package tunifast.esprit.Service;

import tunifast.esprit.Entitie.Actualite;
import tunifast.esprit.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ActualiteCrud {

    Connection cn2;
    Statement st;

    public ActualiteCrud() {
        cn2 = DataBase.getInstance().getCnx();
    }

    public void ajouterActualite(Actualite a) {
        try {
            String requete = "INSERT INTO actualite (idUser,lieuActualite,dateActualite,heureActualite,Actualite,typeActualite) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete);
            pst.setInt(1, a.getIdUser());
            pst.setString(2, a.getLieuActualite());
            pst.setString(3, a.getDateActualite());
            pst.setString(4, a.getHeureActualite());
            pst.setString(5, a.getActualite());
            pst.setInt(6, a.getTypeActualite());
            pst.executeUpdate();
            System.out.println("Actualite ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ModifierActualite(Actualite a, String actualite, int typaA) {
        try {
            String requete = "UPDATE actualite SET Actualite=? ,typeActualite=? WHERE idActualite=? ";
            PreparedStatement pst = cn2.prepareStatement(requete);
            pst.setString(1, actualite);
            pst.setInt(2, typaA);
            pst.setInt(3, a.getIdActualite());
            pst.executeUpdate();
            System.out.println("Actualite Modifier avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SupprimerActualite(Actualite a) {
        try {
            String requete = "DELETE FROM actualite WHERE idActualite=? ";
            PreparedStatement pst = cn2.prepareStatement(requete);
            pst.setInt(1, a.getIdActualite());
            pst.executeUpdate();
            System.out.println("Actualite supprimer avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Actualite> afficherActualite() {

        ArrayList<Actualite> act = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM actualite ORDER BY dateActualite DESC, heureActualite";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Actualite a = new Actualite();
                a.setIdActualite(rs.getInt("IdActualite"));                   //Soit par label soit par indice 
                a.setIdUser(rs.getInt("IdUser"));
                a.setLieuActualite(rs.getString("LieuActualite"));
                a.setDateActualite(rs.getString("DateActualite"));
                a.setHeureActualite(rs.getString("HeureActualite"));
                a.setActualite(rs.getString("Actualite"));
                a.setTypeActualite(rs.getInt("TypeActualite"));
                act.add(a);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return act;
    }

    public List<Actualite> DayActualite(String Day) {

        ArrayList<Actualite> act = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM actualite WHERE dateActualite=? ORDER BY heureActualite  ";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            pst2.setString(1, Day);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Actualite a = new Actualite();
                a.setIdActualite(rs.getInt("IdActualite"));                   //Soit par label soit par indice 
                a.setIdUser(rs.getInt("IdUser"));
                a.setLieuActualite(rs.getString("LieuActualite"));
                a.setDateActualite(rs.getString("DateActualite"));
                a.setHeureActualite(rs.getString("HeureActualite"));
                a.setActualite(rs.getString("Actualite"));
                a.setTypeActualite(rs.getInt("TypeActualite"));
                act.add(a);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return act;
    }

    public List<Actualite> HighActualite() {

        ArrayList<Actualite> act = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM actualite ORDER BY typeActualite, dateActualite ";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Actualite a = new Actualite();
                a.setIdActualite(rs.getInt("IdActualite"));                   //Soit par label soit par indice 
                a.setIdUser(rs.getInt("IdUser"));
                a.setLieuActualite(rs.getString("LieuActualite"));
                a.setDateActualite(rs.getString("DateActualite"));
                a.setHeureActualite(rs.getString("HeureActualite"));
                a.setActualite(rs.getString("Actualite"));
                a.setTypeActualite(rs.getInt("TypeActualite"));
                act.add(a);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return act;
    }

    public ObservableList<Actualite> afficher(Actualite A) throws SQLException {

        ObservableList<Actualite> arr = FXCollections.observableArrayList();
        st = cn2.createStatement();
        ResultSet rs = st.executeQuery("select * from actualite");
        while (rs.next()) {
            arr.add(new Actualite(rs.getInt("idActualite"), rs.getInt("idUser"), rs.getString("lieuActualite"), rs.getString("dateActualite"), rs.getString("heureActualite"), rs.getString("Actualite"), rs.getInt("typeActualite")));

        }
        return arr;

    }

    public void supprimer(int id_Evenement) throws SQLException {

        st = cn2.createStatement();
        String q = "delete from actualite where idActualite= " + id_Evenement;
        PreparedStatement pre = cn2.prepareStatement(q);
        st.executeUpdate(q);
        System.out.println("tu as bien supprimé");
    }

    public void modifier(Actualite A, int id) throws SQLException {

        try {
            if ((A.getLieuActualite() != "") && (A.getDateActualite() != "") && (A.getHeureActualite() != "") && (A.getActualite() != "") && (A.getTypeActualite() != 0)) {
                String query = "update actualite set lieuActualite='" + A.getLieuActualite() + "',dateActualite='" + A.getDateActualite() + "',heureActualite='" + A.getHeureActualite() + "',Actualite='" + A.getActualite() + "' where actualite.idActualite=" + id;

                st = cn2.createStatement();
                st.executeUpdate(query);

                System.out.println("bien modifiée");

            } else {
                System.out.println("tu dois inserer tous les elements");
            }
        } catch (SQLException ex) {

        }

    }

}
