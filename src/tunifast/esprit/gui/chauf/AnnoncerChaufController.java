/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.chauf;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.omg.CORBA.DATA_CONVERSION;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.AnnonceCrud;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class AnnoncerChaufController implements Initializable {
   private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd");

    @FXML
    private JFXTextField txtDep;
    @FXML
    private JFXTextField txtArr;
    @FXML
    private JFXDatePicker txtDate;
    @FXML
    private JFXTimePicker txtHeu;
    @FXML
    private ComboBox<Integer> cbNbPlaces;
    @FXML
    private JFXButton btnAnnoncer;
    @FXML
    private JFXButton btnFermer;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
//        ObservableList<Integer> list = FXCollections.observableArrayList(1,2,3,4);
//        ComboBox<String> cbNbPlaces =  ComboBox<String>();
      cbNbPlaces.setItems(observableArrayList(1,2,3,4));
    }    

    @FXML
    private void annoncer(ActionEvent event) {
        System.out.println(txtDep.getText());
        System.out.println(txtArr.getText());
        System.out.println(txtDate.getValue());
        System.out.println(txtHeu.getValue());
        System.out.println(cbNbPlaces.getSelectionModel().getSelectedItem());
        if ((txtDep.getText().isEmpty()) || txtArr.getText().isEmpty()){
            AlertMaker.showErrorMessage("tous les champs sont oblogatoirs", "veuilez remplir tous les champs svp");
        } else {
            AnnonceCrud anC = new AnnonceCrud();
            UserSession us = UserSession.getInstance();
            anC.ajoutAnnonce(txtDep.getText(), txtArr.getText() , txtDate.getValue(), txtHeu.getValue(), cbNbPlaces.getSelectionModel().getSelectedItem() , us.getIdUser() );
            AlertMaker.showSimpleAlert("succé", "annonce crée");
//          String s = DATE_FORMAT(txtDate.getValue());
//          String s = txtDate.getValue("dd-MMM-yy");
//          String  s = DATE_FORMAT.format(txtDate);
//            System.out.println("s");
       
        }
    }

    @FXML
    private void fermer(ActionEvent event) {
    }
    
}
