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
import tunifast.esprit.Entitie.User;
import tunifast.esprit.Utils.DataBase;

/**
 *
 * @author mohamedazizhaddad
 */
public class UserCrud {

    Connection cnx;
    Statement st;

    public UserCrud() {
        cnx = DataBase.getInstance().getCnx();
    }

    public String getUsernameByIdu(int idU) {
        User u = new User();
        try {
            String requete2 = "SELECT username FROM user WHERE idUser = " + idU + " ";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                u.setUsername(rs.getString("username"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u.getUsername();
    }

    public int getIduByUsername(String username) {
        User u = new User();

        try {
            String requete2 = "SELECT idUser FROM user WHERE username = '" + username + "' ";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                u.setIdUser(rs.getInt("idUser"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u.getIdUser();
    }

    public boolean fetchColExist(String tab, String col, String val) {
        ArrayList<String> testList = new ArrayList<>();
        try {
            String requete3 = " SELECT * FROM `" + tab + "` WHERE `" + col + "` = ?";
            PreparedStatement pst2 = cnx.prepareStatement(requete3);
            pst2.setString(1, val);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                String t = "ok";
                testList.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (testList.isEmpty()) {

            return true;
        } else {

            return false;
        }

    }

    public boolean ajouterUser(User u) {
        try {
            String requete = "INSERT INTO user(username,nom,prenom,mail,numTel,password,role) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getNom());
            pst.setString(3, u.getPrenom());
            pst.setString(4, u.getMail());
            pst.setInt(5, u.getNumTel());
            pst.setString(6, u.getPassword());
            pst.setString(7, "passager");
            pst.executeUpdate();
        } catch (SQLException ex) {
            if (ex.getMessage().contains("Duplicata")) {
                System.out.println("User existe deja!");
                return false;
            } else {
                System.out.println(ex.getMessage());
            }
        }
        return true;
    }

    public ArrayList<User> getAllUsers() {

        ArrayList<User> result = new ArrayList<User>();

        try {
            String requete3 = "SELECT * FROM user ";

            PreparedStatement pst2 = cnx.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setNumTel(rs.getInt("numTel"));
                u.setMail(rs.getString("mail"));
                u.setIdUser(rs.getInt("idUser"));
                u.setPassword(rs.getString("password"));
                u.setUsername(rs.getString("username"));
                u.setSexe(rs.getString("sexe"));
                u.setRole(rs.getString("role"));

                result.add(u);

            }

        } catch (SQLException ex) {

        }

        return result;

    }

    public String listUtilisateurs() {
        String mail = "";
        String requete = "SELECT * FROM user";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()) {
                mail += "\n\n";
                User u = new User();
                u.setIdUser(rs.getInt("idUser"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setPassword(rs.getString("password"));
                u.setSexe(rs.getString("sexe"));
                u.setMail(rs.getString("mail"));
                u.setNumTel(rs.getInt("numTel"));
                u.setRole(rs.getString("role"));

                mail += "L'utilisateur numero " + u.getIdUser() + " :";
                mail += "\n  Nom = " + u.getNom();
                mail += "\n  Prenom = " + u.getPrenom();
                mail += "\n  Mot de passe = " + u.getPassword();
                mail += "\n  Sexe = " + u.getSexe();
                mail += "\n  Mail = " + u.getMail();
                mail += "\n  Telephone = " + u.getNumTel();
                mail += "\n  Role = " + u.getRole();

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mail;
    }

    public void ModifierMdpUserInterfaceee(String maill, String mdpN) {
        try {
            String requetee = "UPDATE user SET password=? WHERE mail=?";
            PreparedStatement pstt = cnx.prepareStatement(requetee);
            pstt.setString(1, mdpN);
            pstt.setString(2, maill);
            pstt.executeUpdate();

            System.out.println("User modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public User getUserById(int idU) {
        User u = new User();
        try {
            String requete3 = "SELECT * FROM user WHERE `idUser` = " + idU + " ";

            PreparedStatement pst2 = cnx.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            
            while (rs.next()) {

                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setNumTel(rs.getInt("numTel"));
                u.setMail(rs.getString("mail"));
                u.setIdUser(rs.getInt("idUser"));
                u.setPassword(rs.getString("password"));
                u.setUsername(rs.getString("username"));
                u.setSexe(rs.getString("sexe"));
                u.setRole(rs.getString("role"));
                u.setCompte(rs.getDouble("compte"));
                u.setEtat(rs.getString("etat"));

            }

        } catch (SQLException ex) {

        }
      return u ;
    }
    
    public void suspendre(int idU){
         try {
            String requetee = "UPDATE user SET etat='suspendu' WHERE idUser="+idU+"";
            PreparedStatement pstt = cnx.prepareStatement(requetee);
   
            pstt.executeUpdate();

            System.out.println("User suspendu !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public double getCompteById(int id){
         User u = new User();

        try {
            String requete2 = "SELECT compte FROM user WHERE idUser = '" + id + "' ";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                u.setCompte(rs.getDouble("compte"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u.getCompte();
    }
    public void ModifierMdp(int id, String mdpN) {
        try {
            String requetee = "UPDATE user SET password=? WHERE idUser=?";
            PreparedStatement pstt = cnx.prepareStatement(requetee);
            pstt.setString(1, mdpN);
            pstt.setInt(2, id);
            pstt.executeUpdate();

            System.out.println("User modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
