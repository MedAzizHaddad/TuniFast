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
import java.util.logging.Level;
import java.util.logging.Logger;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Entitie.Upgrade;
import tunifast.esprit.Utils.DataBase;

/**
 *
 * @author mohamedazizhaddad
 */
public class UpgradeCrud {
    
           Connection cnx;
    Statement st;

    public UpgradeCrud() {
        cnx = DataBase.getInstance().getCnx();
    }
    
    public void ajouterUp (Upgrade up){
        try {
            String requete2 = "INSERT INTO `upgrade`( `cv`, `exp`, `trajet`, `details`, `idUser`, `DateTime`, `etat`) " //
                    + "VALUES ('"+up.getCv()+"',"+up.getExp()+",'"+up.getTrajet()+"','"+up.getDetails()+"',"+up.getIdUser()+",now(),'en attente')";
            PreparedStatement pst = cnx.prepareStatement(requete2);  //
            pst.executeUpdate();
            System.out.println("demande envoyé créer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    
        public ArrayList<Upgrade> getAllUpgrade(){
            
                ArrayList<Upgrade> res = new ArrayList<Upgrade>();

                          
           try {
               DataBase db = new DataBase();
               String qu = "SELECT * from upgrade";
               ResultSet rs = db.execQuery(qu);
               while (rs.next())
               { Upgrade u = new Upgrade();
                           //Soit par label soit par indice 
              u.setCv(rs.getString("cv"));
              u.setDateTime(rs.getDate("DateTime"));
              u.setDetails(rs.getString("details"));
              u.setEtat(rs.getString("etat"));
              u.setExp(rs.getInt("exp"));
              u.setIdUp(rs.getInt("idUp"));
              u.setIdUser(rs.getInt("idUser"));
              u.setTrajet(rs.getString("trajet"));
              
              
               res.add(u);
               
               }
           } catch (SQLException ex) {
               Logger.getLogger(AnnonceCrud.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           return res;
        }
        
        
    }
    
    

