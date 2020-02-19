/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Service;

/**
 *
 * @author mohamedazizhaddad
 */

    public class PasServices {
    
      public  static void reserver(int idAn, int idP, int nbPlARes ) {
        int montant = calculMontant(idAn, idP, nbPlARes);
        AnnonceCrud ann = new AnnonceCrud();
        ReservationCrud res = new ReservationCrud();

        res.ResResAdd(idAn, idP, nbPlARes,montant);
       ann.AnnResAdd(idAn, nbPlARes);

    }
      
          public static int calculMontant(int idAn, int idP, int nbPlARes ) {
      
return 50;
    }
}
