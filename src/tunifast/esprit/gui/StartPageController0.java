/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui;

import com.jfoenix.controls.JFXButton;
import tunifast.esprit.Entitie.UserSession;
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

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class StartPageController0 implements Initializable {

    @FXML
    private JFXButton btnLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void login(ActionEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent parent = loader.load();
            Stage stage = null;
                stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("login");
            stage.setScene(new Scene(parent));
            stage.show();
            //setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    

    @FXML
    private void inscription(ActionEvent event) {
        
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription.fxml"));
            Parent parent = loader.load();
            Stage stage = null;
                stage = new Stage(StageStyle.TRANSPARENT);
            stage.setTitle("inscription");
            stage.setScene(new Scene(parent));
            stage.show();
            //setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
