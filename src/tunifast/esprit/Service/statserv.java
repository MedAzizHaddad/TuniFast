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
public class statserv {

    Connection cn2;
    Statement st;

    public statserv() {

        cn2 = DataBase.getInstance().getCnx();

    }

    public void remplir() {
        try {
            String requete = "INSERT INTO stat (idUser) SELECT (idUser) FROM user ";
            PreparedStatement pst = cn2.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("remplissage avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<stat> mostrated() {

        ArrayList<stat> act = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM stat ORDER BY moyRate DESC";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                stat s = new stat();
                s.setIdUser(rs.getInt("IdUser"));                   //Soit par label soit par indice 
                s.setMoyRate(rs.getFloat("MoyRate"));
                act.add(s);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return act;
    }
    
    public double getRateById(int id){
       // SELECT`moyRate`FROM stat WHERE `idUser` = 
         Double d = null ;
        try {
            String requete3 = "SELECT`moyRate`FROM stat WHERE `idUser` = "+id+"";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            
          
          while (rs.next()) {
                d = rs.getDouble("moyRate");
                System.out.println("aazii" + d);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return d;
  
    }
    
}
