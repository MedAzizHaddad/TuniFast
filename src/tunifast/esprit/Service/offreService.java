/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Service;

import tunifast.esprit.Entitie.offre;
import tunifast.esprit.iservice.Iservice;
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
 * @author ASUS
 */
public class offreService implements Iservice<offre> {

    private Connection con;
    private Statement ste;

    public offreService() {
        con = DataBase.getInstance().getCnx();
    }

    @Override
    public void ajouter(offre a) throws SQLException {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `offre` (`idUser`, `code`, `solde`) VALUES (? , ? , ?);");
        PS.setInt(1, a.getIdoUser());
        PS.setInt(2, a.getCode());
        PS.setFloat(3, a.getSolode());
        PS.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `offre` WHERE  `offre`.`idoff` =?");
        PS.setInt(1, id);
        PS.executeUpdate();
    }

    @Override
    public void update(offre a, int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("UPDATE `offre` SET `idUser`=?,`code`=? ,`solde`=? WHERE `offre`.`idoff` =?");
        PS.setInt(1, a.getIdoUser());
        PS.setInt(2, a.getCode());
        PS.setFloat(3, a.getSolode());
        PS.setInt(4, id);
        PS.executeUpdate();
    }

    @Override
    public List<offre> readAll() throws SQLException {
        List<offre> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from offre");
        while (rs.next()) {
            int id = rs.getInt(1);
            int user = rs.getInt(2);
            int code = rs.getInt(3);
            float solode = rs.getFloat(4);
            offre a = new offre(id, user, code, solode);
            AL.add(a);
        }
        return AL;
    }

    public List<offre> Search(String Text) throws SQLException {
        List<offre> AL = new ArrayList<>();
        ste = con.createStatement();
        String Sqlrequt = "select * from offre where idoff LIKE '%" + Text + "%' or code LIKE '%" + Text + "%'";
        ResultSet rs = ste.executeQuery(Sqlrequt);

        while (rs.next()) {
            int id = rs.getInt(1);
            int user = rs.getInt(2);
            int code = rs.getInt(3);
            float solode = rs.getFloat(4);
            offre a = new offre(id, user, code, solode);
            AL.add(a);
        }
        return AL;
    }
    
    
     public List<offre> Search2(String Text)  {
         List<offre> AL = new ArrayList<>();
        try {
            
            ste = con.createStatement();
            String Sqlrequt = "select * from offre where idoff LIKE '%" + Text + "%' or code LIKE '%" + Text + "%'";
            ResultSet rs = ste.executeQuery(Sqlrequt);
            
            while (rs.next()) {
                int id = rs.getInt(1);
                int user = rs.getInt(2);
                int code = rs.getInt(3);
                float solode = rs.getFloat(4);
                offre a = new offre(id, user, code, solode);
                AL.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(offreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return AL;
    }
}
