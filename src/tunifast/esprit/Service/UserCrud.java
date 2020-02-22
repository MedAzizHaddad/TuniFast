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
import tunifast.esprit.Entitie.Reservation;
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
                System.out.println("hiii");
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

}
