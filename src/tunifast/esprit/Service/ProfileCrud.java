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
import tunifast.esprit.Entitie.Profile;
import tunifast.esprit.Utils.DataBase;

/**
 *
 * @author mohamedazizhaddad
 */
public class ProfileCrud {
    
        Connection cnx;
    Statement st;

    public ProfileCrud() {
        cnx = DataBase.getInstance().getCnx();
    }
 public  ArrayList<Profile> profileCheck(int i){
         ArrayList<Profile> profiles = new ArrayList<>();
        try {
            String requete3 = " SELECT * FROM `profile` WHERE `idUser` = "+ i +"";
            PreparedStatement pst2 = cnx.prepareStatement(requete3);

            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
               Profile p = new Profile();
                p.setMode(rs.getString("mode"));
                p.setStyle(rs.getString("style"));
            
                profiles.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return profiles;
 
    }

    
}
