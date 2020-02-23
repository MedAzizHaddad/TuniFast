/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.pas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.AnnonceCrud;
import tunifast.esprit.Service.PasServices;
import tunifast.esprit.Service.ReservationCrud;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class AnnonceReadController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXTextField txtChauf;
    @FXML
    private JFXTextField txtDep;
    @FXML
    private JFXTextField txtArr;
    @FXML
    private JFXTextField txtDat;
    @FXML
    private JFXTextField txtHeure;
    @FXML
    private JFXButton btnReserver;
    @FXML
    private JFXButton btnFermer;
    @FXML
    private JFXTextField txtPlaces;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtChauf.setEditable(false);
        txtDat.setEditable(false);

        txtDep.setEditable(false);
        txtArr.setEditable(false);
        txtHeure.setEditable(false);
        txtPlaces.setEditable(false);

        UserSession us = new UserSession().getInstance();
        AnnonceCrud an = new AnnonceCrud();
        ArrayList<Annonce> rs = new ArrayList<Annonce>();

        rs = an.ReadAnnonce(us.getParam());
        txtChauf.setText(rs.get(0).getUser().getNom());
        System.out.println(rs.get(0).getUser().getNom());
        txtDat.setText(rs.get(0).getDateAnnonce());
        txtDep.setText(rs.get(0).getLieuDepart());
        txtArr.setText(rs.get(0).getLieuArrivee());
        txtHeure.setText(rs.get(0).getHeureAnnonce());
        txtPlaces.setText(Integer.toString(rs.get(0).getNbrPlaceDispo()));
    }

    @FXML
    private void reserver(ActionEvent event) {

        }
    

    @FXML
    private void fermer(ActionEvent event) {
          // get a handle to the stage
    Stage stage = (Stage) txtDep.getScene().getWindow();
    // do what you have to do
    stage.close();
    }

}
