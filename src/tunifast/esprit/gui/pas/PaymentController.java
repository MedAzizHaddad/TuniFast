/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.pas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Entitie.Promotion;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Entitie.offre;
import tunifast.esprit.Service.AnnonceCrud;
import tunifast.esprit.Service.PromotionService;
import tunifast.esprit.Service.ReservationCrud;
import tunifast.esprit.Service.UserCrud;
import tunifast.esprit.Service.offreService;
import tunifast.esprit.Service.paymentCrud;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class PaymentController implements Initializable {

    
    @FXML
    private JFXTextField txtPri;
    @FXML
    private JFXTextField txtPriT;
    @FXML
    private JFXTextField txtPromo;
    @FXML
    private JFXTextField txtOffre;
    @FXML
    private JFXTextField txtSolde;
    @FXML
    private JFXButton pay;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txtPri.setEditable(true);
txtPriT.setEditable(true);
  txtPromo.setEditable(true);
   txtOffre.setEditable(true);
   txtSolde.setEditable(true);
        
        ArrayList<Annonce> res = new ArrayList<Annonce>();
         UserSession us = new UserSession().getInstance();
        AnnonceCrud anC = new AnnonceCrud();
      
         res = anC.ReadAnnonce2(us.getParam());
         Annonce a = res.get(0);
         
         PromotionService ps = new PromotionService();
    
             List<Promotion> AL = new ArrayList<>();
           AL = ps.getAllPromos();
      
  txtPri.setText(Double.toString(a.getPrix()));
  Double x = a.getPrix() * us.getParam2();
 txtPriT.setText(Double.toString(x));
  String promo = String.valueOf(AL.get(0).getReduction());
  txtPromo.setText(promo);
        UserCrud uc = new UserCrud();
        double y = uc.getCompteById(us.getIdUser());
        txtSolde.setText(Double.toString(y));
        pay.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
        System.out.println("action");
        double solde = Double.parseDouble(txtSolde.getText()); ;
        double payment = Double.parseDouble(txtPriT.getText()); ;
        double offre = 0 ;
        
       if (! txtOffre.getText().isEmpty()){
            List<offre> AL = new ArrayList<>();
            offreService os = new offreService();
            AL = os.Search2(txtOffre.getText());
          //  offre o = AL.get(0);
           offre =  AL.get(0).getSolode();
       }
       if (payment > solde + offre ){
           AlertMaker.showErrorMessage("solde insuffisant", "veuillez recharcgé votre compte ");
                    Stage stage = (Stage) txtPri.getScene().getWindow();
        stage.close();
       }
      else if (offre > 0 ){
           if (offre > payment){
                paymentCrud pC = new paymentCrud();
          pC.payer(us.getIdUser(), a.getIdUser(), payment, offre);
               System.out.println("payent offre");
                 AlertMaker.showSimpleAlert("succée", "payement effecuté ");
                 Stage stage = (Stage) txtPri.getScene().getWindow();
        stage.close();
           } else {
                paymentCrud pC = new paymentCrud();
          pC.payer(us.getIdUser(), a.getIdUser(), payment, offre);
               payment = payment - offre;
               solde = solde - payment;
               System.out.println("offre = 0");
               System.out.println("change payùent");
                 AlertMaker.showSimpleAlert("succée", "payement effecuté ");
                 Stage stage = (Stage) txtPri.getScene().getWindow();
        stage.close();
               
           }
       } else {
          paymentCrud pC = new paymentCrud();
          pC.payer(us.getIdUser(), a.getIdUser(), payment, offre);
           System.out.println("payment solde");
           AlertMaker.showSimpleAlert("succée", "payement effecuté ");
                 Stage stage = (Stage) txtPri.getScene().getWindow();
        stage.close();
       }
       
        

    }
});
//        
//  
         
         
     //   System.out.println(us.getParam());
    }    

    @FXML
    private void payer(ActionEvent event) {
        
    }
    
}
