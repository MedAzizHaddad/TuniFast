/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Service;

import tunifast.esprit.Entitie.offre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tunifast.esprit.Utils.DataBase;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author ASUS
 */
public class UserService {
    private Connection con;
    private Statement ste;

    public UserService() {
        con = DataBase.getInstance().getCnx();
    }
    
      public List<Integer> Getiduser() throws SQLException {
        List<Integer> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select idUser from user");
        while (rs.next()) {
            int id = rs.getInt(1);
            AL.add(id);
        }
        return AL;
    }
}
