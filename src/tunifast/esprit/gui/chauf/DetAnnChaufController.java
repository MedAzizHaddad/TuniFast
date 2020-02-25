/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.chauf;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Entitie.Reclamation;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.AnnonceCrud;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class DetAnnChaufController implements Initializable {

    @FXML
    private JFXTextField txtDateAn;
    @FXML
    private JFXTextField txtDep;
    @FXML
    private JFXTextField txtArr;
    @FXML
    private JFXTextField txtDat;
    @FXML
    private JFXTextField txtHeure;
    @FXML
    private JFXTextField txtPlacesDisp;
    @FXML
    private JFXTextField txtPlacesRes;
    @FXML
    private JFXButton btnFermer;
    @FXML
    private JFXButton btnModifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        UserSession us = new UserSession().getInstance();
        AnnonceCrud anC = new AnnonceCrud();
        ArrayList<Annonce> rs = new ArrayList<Annonce>();

        rs = anC.ReadAnnonce(us.getParam());
        Annonce an = rs.get(0);
        if (an.getNbPlaceReser() > 0) {
            
            txtDep.setEditable(false);
            txtArr.setEditable(false);
            txtDat.setEditable(false);
            txtHeure.setEditable(false);
            txtPlacesDisp.setEditable(false);
            txtPlacesRes.setEditable(false);
            btnModifier.setDisable(true);
        }
    
        txtDateAn.setEditable(false);
        txtDateAn.setText(an.getDateAnnPost());
        txtDep.setText(an.getLieuDepart());
        txtArr.setText(an.getLieuArrivee());
        txtDat.setText(an.getDateAnnonce());
        txtHeure.setText(an.getHeureAnnonce());
        txtPlacesDisp.setText(Integer.toString(an.getNbrPlaceDispo()));
        txtPlacesRes.setText(Integer.toString(an.getNbPlaceReser()));
    }

    @FXML
    private void modifier(ActionEvent event) {
         UserSession us = new UserSession().getInstance();
        AnnonceCrud anC = new AnnonceCrud();
        ArrayList<Annonce> rs = new ArrayList<Annonce>();

        rs = anC.ReadAnnonce(us.getParam());
        Annonce an = rs.get(0);
     
        Annonce an2 = new Annonce();
        
        an2.setDateAnnonce(txtDat.getText());
        an2.setHeureAnnonce(txtHeure.getText());
        an2.setLieuDepart(txtDep.getText());
        an2.setLieuArrivee(txtArr.getText());
        an2.setNbrPlaceDispo(Integer.parseInt(txtPlacesDisp.getText()));
        anC.updateAnnonce(an2, an.getIdAnnonce());
        AlertMaker.showSimpleAlert("succée", "annonce modifier");
        
    }

    @FXML
    private void fermer(ActionEvent event) {
    }

}
