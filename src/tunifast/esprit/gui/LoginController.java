/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import tunifast.esprit.Utils.JavamailUtil;
import tunifast.esprit.Utils.JavamailUtil1;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunifast.esprit.Entitie.Profile;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.ProfileCrud;
import javafx.scene.web.WebEngine;
import javax.mail.event.MailEvent;
import tunifast.esprit.Service.UserCrud;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class LoginController implements Initializable {

    @FXML
    private JFXPasswordField pw;
    @FXML
    private JFXTextField email;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private StackPane root;

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

        if (UserTest.loginTest(email.getText(), pw.getText()).isEmpty()) {
            email.getStyleClass().add("wrong-credentials");
            pw.getStyleClass().add("wrong-credentials");
        } else if (UserTest.loginTest(email.getText(), pw.getText()).get(0).getRole().equals("passager")
                || UserTest.loginTest(email.getText(), pw.getText()).get(0).getRole().equals("chauffeur")) {
            int id = UserTest.loginTest(email.getText(), pw.getText()).get(0).getIdUser();
            String role = UserTest.loginTest(email.getText(), pw.getText()).get(0).getRole();
            UserSession us = UserSession.getInstance(id, role);
                 Stage stage = (Stage) pw.getScene().getWindow();
            stage.close();
            loadMain1();
        } else if (UserTest.loginTest(email.getText(), pw.getText()).get(0).getRole().equals("admin")) {
            int id = UserTest.loginTest(email.getText(), pw.getText()).get(0).getIdUser();
            String role = UserTest.loginTest(email.getText(), pw.getText()).get(0).getRole();
            UserSession us = UserSession.getInstance(id, role);
        Stage stage = (Stage) pw.getScene().getWindow();
            stage.close();
            loadMain3();
            
        }

    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {

        // get a handle to the stage
        Stage stage = (Stage) pw.getScene().getWindow();
        // do what you have to do
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
            Stage stage = new Stage(StageStyle.TRANSPARENT);
            stage.setTitle("TuniFast");
            stage.setScene(new Scene(parent));
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

    @FXML
    private void resetPassword(ActionEvent event) {
        System.out.println(1);
        if (email.getText().isEmpty()) {
            System.out.println(2);
            AlertMaker.showErrorMessage("erreur", "vous devez saisir votre email !");
        } else {

            try {
                String str = email.getText();
                JavamailUtil1 mail = new JavamailUtil1();
                String x = JavamailUtil1.sendMail(str);
                UserCrud userC = new UserCrud();
                userC.ModifierMdpUserInterfaceee(str, x);
            } catch (Exception ex) {
                Logger.getLogger(StartPageController0.class.getName()).log(Level.SEVERE, null, ex);
            }
            JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog0(root, Arrays.asList(btn), "succée !!", "votre mot de passe est reinitialisé et un email est envoyé ");

        }
    }
}
