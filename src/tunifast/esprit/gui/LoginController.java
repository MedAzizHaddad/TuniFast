/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import tunifast.esprit.Entitie.UserSession;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField tel;
    @FXML
    private JFXPasswordField pw;

    public String getTel() {
        return tel.getText();
    }

    public String getPw() {
        return pw.getText();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        Auth UserTest = new Auth();
        if (UserTest.loginTest(getTel(), getPw()).isEmpty()) {
           tel.getStyleClass().add("wrong-credentials");
            pw.getStyleClass().add("wrong-credentials");
        } else if(UserTest.loginTest(getTel(), getPw()).get(0).getRole().equals("passager")) {
            int id =UserTest.loginTest(getTel(), getPw()).get(0).getIdUser() ;
            String role = UserTest.loginTest(getTel(), getPw()).get(0).getRole() ;
            UserSession us = UserSession.getInstace(id , role );
            
            closeStage();
            loadMain();
            
            
        }
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {

        System.exit(0);
    }
    
    
    private void closeStage() {
        ((Stage) tel.getScene().getWindow()).close();
    }

    void loadMain() {
       
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("acceuil.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("TuniFast");
            stage.setScene(new Scene(parent));
            stage.show();
            //**.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
}
