/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tunifast.esprit.Entitie.User;
import tunifast.esprit.Service.UserCrud;
import tunifast.esprit.Utils.DataBase;
import tunifast.esprit.Utils.TuniFastUtil;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class InscriptionController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField numTel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void signup(ActionEvent event) {

//        boolean test = true;
//        do {
//            test = testChamps();
//        } while (test != true);
//
        if (testChamps() == true) {
            System.out.println("ready");
            int i = Integer.parseInt(numTel.getText());
            // System.out.println("aziiiiz" + i );
            User u = new User(nom.getText(), prenom.getText(), mail.getText(), i, password.getText(), username.getText());
            UserCrud uc = new UserCrud();
            

            //--------------------
              if (uc.ajouterUser(u)) {
                  AlertMaker.showSimpleAlert("inscription terminé", "Welcome!");
                  Stage stage = (Stage) nom.getScene().getWindow();
        stage.close();
              }
        }
         
    }

    @FXML
    private void cancel(ActionEvent event) {
        try {
          //  Parent root = FXMLLoader.load(getClass().getResource("startPage.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("startPage.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();
            // to instanciate the db since the start of the app nad thread it to prevent the app from slow launchign
           
            new Thread(() -> {
                DataBase.getInstance();
            }).start();
            //      root.requestFocus();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean testChamps() {
        boolean testing = true;
        if (username.getText().isEmpty() || nom.getText().isEmpty() || prenom.getText().isEmpty()
                || numTel.getText().isEmpty() || mail.getText().isEmpty() || password.getText().isEmpty()) {
            AlertMaker.showErrorMessage("tous les champs sont obligatoires", null);
            return false;

        } else if (!isNumeric(numTel.getText())) {
            AlertMaker.showErrorMessage("veuillez verifier votre num tel", null);
            return false;
        } else if (username.getText().contains(" ")) {
            AlertMaker.showErrorMessage("veillez sairi un nome d'utilisateur valid", null);
            testing = false;
        } else if (!mail.getText().contains("@")) {
            AlertMaker.showErrorMessage("veuillez verifier votre email", null);
            testing = false;
        } else if (TuniFastUtil.testUnicité("user", "username", username.getText())) {
            AlertMaker.showErrorMessage("ce nom d'utilisateur est déja utilisé", "veuillez saisir un autre");
            testing = false;
        } else if (TuniFastUtil.testUnicité("user", "mail", mail.getText())) {
            AlertMaker.showErrorMessage("cet email est déja existant", "voous pouvez vous connectez ou utiliser une autre @mail");
        return false;
        } else if (TuniFastUtil.testUnicité("user", "numTel", numTel.getText())) {
            AlertMaker.showErrorMessage("ce numré du telephone deja utilisé", "seulement un seul compte par utilisateur");
            testing = false;
        } else if (username.getText().contains(" ")) {
            AlertMaker.showErrorMessage("veillez sairi un nome d'utilisateur valid", null);
            testing = false;
        } else if (!mail.getText().contains("@")) {
            AlertMaker.showErrorMessage("veuillez verifier votre email", null);
            testing = false;
        }

        // System.out.println("had => "+ testing );
        return testing;
    }

    public static boolean isNumeric(Object object) {
        if (object instanceof Integer) {
            return true;
        } else {
            String string = object.toString();

            try {
                Integer.parseInt(string);
            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }
}
