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
import java.util.ArrayList;
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
import tunifast.esprit.Entitie.Profile;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.ProfileCrud;

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
         ArrayList<Profile> p = new ArrayList<>();
        Auth UserTest = new Auth();
         ProfileCrud pc = new ProfileCrud();
      
        if (UserTest.loginTest(getTel(), getPw()).isEmpty()) {
           tel.getStyleClass().add("wrong-credentials");
            pw.getStyleClass().add("wrong-credentials");
        } else if(UserTest.loginTest(getTel(), getPw()).get(0).getRole().equals("passager")
                || UserTest.loginTest(getTel(), getPw()).get(0).getRole().equals("chauffeur") ) {
            int id =UserTest.loginTest(getTel(), getPw()).get(0).getIdUser() ;
            String role = UserTest.loginTest(getTel(), getPw()).get(0).getRole() ;
            UserSession us = UserSession.getInstance(id , role );
               closeStage();
                loadMain1();  
        } else if(UserTest.loginTest(getTel(), getPw()).get(0).getRole().equals("admin") ) {
            int id =UserTest.loginTest(getTel(), getPw()).get(0).getIdUser() ;
            String role = UserTest.loginTest(getTel(), getPw()).get(0).getRole() ;
            UserSession us = UserSession.getInstance(id , role );
               closeStage();
                loadMain3();  
        }
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {

         Stage stage = (Stage) pw.getScene().getWindow();
        stage.close();
    }
    
    
    private void closeStage() {
      //  ((Stage) tel.getScene().getWindow()).close();
    }

    void loadMain1() {
       
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("acceuil.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("TuniFast");
            stage.setScene(new Scene(parent));
           stage.setResizable(false);
            stage.show();
            //**.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    void loadMain2() {
       
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("acceuilMode2.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("TuniFast");
            stage.setScene(new Scene(parent , 1000 , 700 ));
            stage.show();
            //**.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    
    void loadMain3() {
       
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("admin/adminAcceuil.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("TuniFast");
            stage.setScene(new Scene(parent));
           stage.setResizable(false);
            stage.show();
            //**.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
}
