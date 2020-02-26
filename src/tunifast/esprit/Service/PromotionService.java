/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Service;

import tunifast.esprit.Entitie.Promotion;
import tunifast.esprit.Entitie.offre;
import tunifast.esprit.iservice.Iservice;
import tunifast.esprit.Utils.DataBase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class PromotionService implements Iservice<Promotion> {

    private Connection con;
    private Statement ste;

    public PromotionService() {
        con = DataBase.getInstance().getCnx();
    }

    @Override
    public void ajouter(Promotion a) throws SQLException {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `promotion` (`dateD`,`dateF`, `reduction`) VALUES (?, ?, ?);");
        PS.setDate(1, convertUtilToSql(a.getDateD()));
        PS.setDate(2, convertUtilToSql(a.getDateF()));
        PS.setFloat(3, a.getReduction());
        PS.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `promotion` WHERE `idPro`=?");
        PS.setInt(1, id);
        PS.executeUpdate();
    }

    @Override
    public void update(Promotion a, int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("UPDATE `promotion` SET `dateD`=?,`dateF`=? ,`reduction`=? WHERE `idPro`=?");
        PS.setDate(1, convertUtilToSql(a.getDateD()));
        PS.setDate(2, convertUtilToSql(a.getDateF()));
        PS.setFloat(3, a.getReduction());
        PS.setInt(4, id);
        PS.executeUpdate();
    }

    @Override
    public List<Promotion> readAll() throws SQLException {
        List<Promotion> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from promotion");
        while (rs.next()) {
            int id = rs.getInt(1);
            Date DaD = rs.getDate(2);
            Date DaF = rs.getDate(3);
            float Reduction = rs.getFloat(4);
            Promotion a = new Promotion(id, DaD, DaF, Reduction);

            AL.add(a);
        }
        return AL;
    }

    public List<Promotion> Search(String Text) throws SQLException {
        List<Promotion> AL = new ArrayList<>();
        ste = con.createStatement();
        String Sqlrequt = "select * from promotion where idPro LIKE '%" + Text + "%' or reduction LIKE '%" + Text + "%'";
        ResultSet rs = ste.executeQuery(Sqlrequt);

        while (rs.next()) {
            int id = rs.getInt(1);
            Date DaD = rs.getDate(2);
            Date DaF = rs.getDate(3);
            float Reduction = rs.getFloat(4);
            Promotion a = new Promotion(id, DaD, DaF, Reduction);

            AL.add(a);
        }
        return AL;
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

public List<Promotion> getAllPromos() {
    
    List<Promotion> AL = new ArrayList<>();
    
        try {
            ste = con.createStatement();
            
            ResultSet rs = ste.executeQuery("select * from promotion");
            while (rs.next()) {
                int id = rs.getInt(1);
                Date DaD = rs.getDate(2);
                Date DaF = rs.getDate(3);
                float Reduction = rs.getFloat(4);
                Promotion a = new Promotion(id, DaD, DaF, Reduction);
                
                AL.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PromotionService.class.getName()).log(Level.SEVERE, null, ex);
        }
                    return AL;
    }

}
