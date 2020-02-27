/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.profile.upgrade;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tunifast.esprit.Entitie.Upgrade;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.UpgradeCrud;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class UpgradeFormController implements Initializable {

    @FXML
    private JFXTextField txtCv;
    @FXML
    private JFXTextField txtExp;
    @FXML
    private JFXTextField txtTra;
    @FXML
    private JFXTextField txtDet;
    @FXML
    private JFXButton btnSub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void submit(ActionEvent event) {
        Upgrade up = new Upgrade();
        UpgradeCrud upC = new UpgradeCrud();
        UserSession us = UserSession.getInstance();
        up.setCv(txtCv.getText());
        up.setDetails(txtDet.getText());
        up.setExp(Integer.parseInt(txtExp.getText()));
        up.setTrajet(txtTra.getText());
        upC.ajouterUp(up);
        up.setIdUser(us.getIdUser());
        AlertMaker.showSimpleAlert("succé", "demande d'upgrade envoyé");
        
    }
    
}
