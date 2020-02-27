/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.profile;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tunifast.esprit.Entitie.User;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.UserCrud;
import tunifast.esprit.Utils.TuniFastUtil;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class ProfileController implements Initializable {

    @FXML
    private JFXPasswordField txtMp1;
    @FXML
    private JFXPasswordField txtMp2;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXTextField txtMail;
    @FXML
    private JFXTextField txtTel;
    @FXML
    private JFXTextField txtRole;
    @FXML
    private JFXTextField txtSolde;
    @FXML
    private JFXButton btnUpgrade;
    @FXML
    private JFXButton btnCharger;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtUsername.setEditable(false);
        txtNom.setEditable(false);
        txtPrenom.setEditable(false);
        txtMail.setEditable(false);
        txtTel.setEditable(false);
        txtRole.setEditable(false);
        txtSolde.setEditable(false);

        UserSession us = UserSession.getInstance();
        UserCrud uC = new UserCrud();
        User u = new User();
        u = uC.getUserById(us.getIdUser());
           
        txtUsername.setText(u.getUsername());
        txtNom.setText(u.getNom());
        txtPrenom.setText(u.getPrenom());
        txtMail.setText(u.getMail());
        txtTel.setText(Integer.toString(u.getNumTel()));
        txtRole.setText(u.getRole());
     txtSolde.setText(Double.toString(u.getCompte()));
        
        
        
    }

    @FXML
    private void changerPw(ActionEvent event) {
        if (txtMp1.getText().isEmpty() || txtMp2.getText().isEmpty() ){
            AlertMaker.showErrorMessage("erreur" , "veuillez remplir les deux champs");
        } else {
            if (! txtMp1.getText().equals(txtMp2.getText()) || txtMp1.getText().length() < 4){
                AlertMaker.showErrorMessage("erreur", "les deux mots de passe saisis sont différents");
            } else {
                UserCrud uC =new UserCrud();
                UserSession us = UserSession.getInstance();
                uC.ModifierMdp(us.getIdUser(), txtMp1.getText());
                AlertMaker.showSimpleAlert("succé","mot de passe modifié");
            }
        }
    }

    @FXML
    private void callUpgrade(ActionEvent event) {
       TuniFastUtil.loadWindowMode1(getClass().getResource("upgrade/upgradeForm.fxml"), "formulade", null);
    }

}
