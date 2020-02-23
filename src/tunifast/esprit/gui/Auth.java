/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui;

/**
 *
 * @author mohamedazizhaddad
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class Auth {

      Connection cnx;
    Statement st;

    public Auth() {
        cnx = DataBase.getInstance().getCnx();
    }

    public ArrayList<User> loginTest(String log, String pwd) {
        ArrayList<User> userList = new ArrayList<>();
        try {
            String requete3 = " SELECT * FROM `user` WHERE `mail` = ? AND `password` = ?";
            PreparedStatement pst2 = cnx.prepareStatement(requete3);
            pst2.setString(1, log);
            pst2.setString(2, pwd);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setIdUser(rs.getInt("idUser"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setRole(rs.getString("role"));
                userList.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userList;

    }

}
