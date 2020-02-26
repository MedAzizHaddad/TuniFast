/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import tunifast.esprit.Utils.DataBase;

/**
 *
 * @author mohamedazizhaddad
 */
public class paymentCrud {
     Connection cnx;
    Statement st;

    public paymentCrud() {
        cnx = DataBase.getInstance().getCnx();
    }
    
    public void payer (int id1 , int id2 , double prix , double offre ){
        System.out.println(id1);
        System.out.println(id2);
        System.out.println(prix);
        System.out.println(offre);
        if (offre == 0 ) {
            soustractionSolde(id1, prix);
            additionSolde(id2, prix);
            
        } else {
            if (offre > prix){
                soustractionOffre(id1, prix);
                additionSolde(id2, prix);
                
                
            } else {
                 double x = prix - offre ;
                 setOffreA0(id1);
                 soustractionSolde(id1, x);
                 additionSolde(id2, prix);
            }
        }
        
        
     
    }
    
    public void additionSolde (int id , double prix ){
        
        
                try {
            String requete2 = "UPDATE `user` SET `compte`= `compte` + "+prix+"  WHERE `idUser` ="+id+" ";
            PreparedStatement pst = cnx.prepareStatement(requete2);
          
    

            pst.executeUpdate();
            System.out.println("solde ++");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void soustractionSolde (int id , double prix){
           try {
            String requete2 = "UPDATE `user` SET `compte`= `compte` - "+prix+"  WHERE `idUser` ="+id+" ";
            PreparedStatement pst = cnx.prepareStatement(requete2);
          
    

            pst.executeUpdate();
            System.out.println("solde --");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void soustractionOffre (int id , double prix){
        
           try {
            String requete2 = "UPDATE `offre` SET `solde`= `solde` - "+prix+"  WHERE `idUser` ="+id+" ";
            PreparedStatement pst = cnx.prepareStatement(requete2);
          
    

            pst.executeUpdate();
            System.out.println("offre --");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
public void setOffreA0 (int id){
        
           try {
            String requete2 = "UPDATE `offre` SET `solde`= 0   WHERE `idUser` ="+id+" ";
            PreparedStatement pst = cnx.prepareStatement(requete2);
          
    

            pst.executeUpdate();
            System.out.println("offre --");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
