/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Utils.TuniFastUtil;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class ToolbarController implements Initializable {

    @FXML
    private JFXButton btnreserved;
    @FXML
    private JFXToggleButton btnRole;
    @FXML
    private VBox rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void anList(ActionEvent event) {
        TuniFastUtil.loadWindow(getClass().getResource("pas/annonceList.fxml"), "reserver", null);
    }

    @FXML
    private void checkRole(ActionEvent event) {
        UserSession us = UserSession.getInstance();
        if (us.getRole().equals("passager")) {
            AlertMaker.showErrorMessage("you are not a driver!!", null);
            btnRole.setSelected(false);
        }

//        JFXButton btn = new JFXButton("Alright!");
//        AlertMaker.showMaterialDialog(null, null, Arrays.asList(btn), "Invalid Input", null);
        // btnRole.setSelected(false);
    }

    @FXML
    private void ResCons(ActionEvent event) {
        if (!btnRole.isSelected()) {
            TuniFastUtil.loadWindow(getClass().getResource("pas/ResConsPas.fxml"), "Consulter reservation des passager", null);
        } else {
            TuniFastUtil.loadWindow(getClass().getResource("chauf/ResConsChauf.fxml"), "Consulter reservation dans vos annoces", null);

        }

    }

    @FXML
    private void annoncer(ActionEvent event) {
        System.out.println(btnRole.isSelected());
    }

    @FXML
    private void profile(ActionEvent event) {
        TuniFastUtil.loadWindow(getClass().getResource("profile/profilePers.fxml"), "Consulter reservation dans vos annoces", null);

    }

}
