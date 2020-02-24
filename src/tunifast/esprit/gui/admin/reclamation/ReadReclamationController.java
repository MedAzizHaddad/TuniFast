/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.admin.reclamation;

import tunifast.esprit.gui.profile.reclamation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import tunifast.esprit.Entitie.Reclamation;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.ReclamationCrud;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class ReadReclamationController implements Initializable {

    @FXML
    private JFXTextField txtDate;
    @FXML
    private JFXTextField txtUser1;
    @FXML
    private JFXTextField txtUser2;
    @FXML
    private JFXTextArea txtDet;
    @FXML
    private JFXTextField txtEtatRec;
    @FXML
    private JFXTextField txtEtatUser;
    private JFXButton btnSubmit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationCrud recC = new ReclamationCrud();
        Reclamation rec = new Reclamation();
        UserSession us = UserSession.getInstance();
        txtDate.setEditable(false);
            txtUser1.setEditable(false);

            txtUser2.setEditable(false);
            txtDet.setEditable(false);
            txtEtatRec.setEditable(false);
            txtEtatUser.setEditable(false);
        
            
        rec = recC.getRecById(us.getParam());
              txtDate.setText(rec.getDateReclamation());
            txtUser1.setText(rec.getUserReporter());

            txtUser2.setText(rec.getUserReported());
            txtDet.setText(rec.getDetails());
            txtEtatRec.setText(rec.getEtatReclamation());
            txtEtatUser.setText(rec.getEtatUser());
       
        
    }

    private void update(ActionEvent event) {
        ReclamationCrud recC = new ReclamationCrud();
        UserSession us = UserSession.getInstance();
        recC.updateDetById(us.getParam(), txtDet.getText());
        AlertMaker.showSimpleAlert("succé", "reclamation modifié");
    }

    @FXML
    private void close(ActionEvent event) {
            Stage stage = (Stage) txtDet.getScene().getWindow();
                    // do what you have to do
                    stage.close();
    }

}
