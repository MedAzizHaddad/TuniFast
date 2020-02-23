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
import tunifast.esprit.Entitie.messages;
import tunifast.esprit.Utils.DataBase;

/**
 *
 * @author mohamedazizhaddad
 */
public class MessageCrud {

    Connection cnx;
    Statement st;

    public MessageCrud() {
        cnx = DataBase.getInstance().getCnx();
    }

    public ArrayList<messages> getContacts(int idU) {

        ArrayList<messages> result = new ArrayList<messages>();
        try {
            String requete3 = "SELECT  exp, recep FROM messages WHERE exp= "+idU+" or recep = "+idU+" ORDER BY idMessage DESC";

            PreparedStatement pst2 = cnx.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                messages m = new messages();
                messages m1 = new messages();
                m.setExp(rs.getInt("exp"));
                m.setRecep(rs.getInt("recep"));
                m1.setExp(rs.getInt("recep"));
                m1.setRecep(rs.getInt("exp"));
                if (!result.contains(m) && !result.contains(m1)) {
                    if (m1.getExp() == 1) {
                        result.add(m);
                    } else {
                        result.add(m1);
                    }
                }

            }
        } catch (SQLException ex) {

        }

        return result;
    }
    
    
    public ArrayList<messages> getMessages(int u1 , int u2) {

        ArrayList<messages> result = new ArrayList<messages>();
        try {
            String requete3 = "SELECT * FROM messages WHERE (recep = "+u1+" and exp ="+u2+" ) "
                            + "or (recep ="+u2+" and exp = "+u1+") ORDER BY idMessage DESC";

            PreparedStatement pst2 = cnx.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                messages m = new messages();
                messages m1 = new messages();
                m.setExp(rs.getInt("exp"));
                m.setRecep(rs.getInt("recep"));
               m.setContent(rs.getString("content"));
               m.setTime(rs.getString("time"));
               result.add(m);

            }
        } catch (SQLException ex) {

        }

        return result;
    }
    
    public void sendMessage(int exp , int recep , String content){
        
           try {
            String requete2 = "INSERT INTO `messages`( `exp`, `recep`, `content`) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
    
            
            pst.setInt(1, exp);
            pst.setInt(2, recep);
            pst.setString(3, content);

            pst.executeUpdate();
            System.out.println("message envoyé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
