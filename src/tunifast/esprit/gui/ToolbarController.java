/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunifast.esprit.Utils.TuniFastUtil;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class ToolbarController implements Initializable {

    @FXML
    private JFXButton btnreserved;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void anList(ActionEvent event) {
        TuniFastUtil.loadWindow(getClass().getResource("pas/annonceList.fxml"), "reserver" , null);
    }

 
}
