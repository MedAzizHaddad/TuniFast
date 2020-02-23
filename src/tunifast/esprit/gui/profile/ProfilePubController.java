/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.profile;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tunifast.esprit.Entitie.User;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.MessageCrud;
import tunifast.esprit.Service.UserCrud;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class ProfilePubController implements Initializable {

    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXTextField txtmail;
    @FXML
    private JFXTextField txtTel;
    @FXML
    private JFXTextField txt1;
    @FXML
    private JFXTextField txt2;
    @FXML
    private JFXTextField txt3;
    @FXML
    private JFXTextField txt4;
    @FXML
    private HBox part3;
    @FXML
    private StackPane changingField;
    private JFXButton btn;
    @FXML
    private JFXButton btn2;
    @FXML
    private JFXButton btn1;
    public JFXTextArea text = new JFXTextArea();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtNom.setEditable(false);
        txtPrenom.setEditable(false);
        txtTel.setEditable(false);
        txtmail.setEditable(false);
        txt1.setEditable(false);
        txt2.setEditable(false);
        txt3.setEditable(false);
        txt4.setEditable(false);
        btn2.setDisable(true);
        User res = new User();
        UserCrud uc = new UserCrud();
        UserSession us = UserSession.getInstance();
        res = uc.getUserById(us.getParam());
        txtNom.setText(res.getNom());
        txtPrenom.setText(res.getPrenom());
        txtTel.setText(Integer.toString(res.getNumTel()));
        txtmail.setText(res.getMail());
        
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (btn1.getText().equals("Fermer")) {
                    // get a handle to the stage
        Stage stage = (Stage) btn1.getScene().getWindow();
        // do what you have to do
        stage.close();
                } 
            }
        });
        
    }

    @FXML
    private void messageHandler(ActionEvent event) {
        UserSession us = UserSession.getInstance();
        changingField.getChildren().clear();

        TextField textField = new TextField();
        textField.setPrefSize(200, 150);

        text.setPrefSize(200, 150);
        text.setPromptText("vous pouvez ecrire votre message ici ... ");
        text.setPadding(new Insets(20, 20, 20, 20));

        text.setStyle("-fx-font-size: 2em; -fx-control-inner-background:#000000; -fx-font-family: Zapf Chancery; "
                + "-fx-highlight-fill:#FFFF8D; -fx-highlight-text-fill: #000000; "
                + "-fx-text-fill: #FFFF8D; ");
        text.setWrapText(true);

        changingField.getChildren().addAll(text);

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                text.clear();
                btn1.setText("Fermer");
            }
        });
        text.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                if (text.getText().isEmpty()) {
                    btn1.setText("Fermer");
                    btn2.setDisable(true);
                } else {
                    btn1.setText("submit");
                    btn2.setDisable(false);
                }

            }
        });
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (btn1.getText().equals("Fermer")) {
                    // get a handle to the stage
        Stage stage = (Stage) btn1.getScene().getWindow();
        // do what you have to do
        stage.close();
                } else {
                      MessageCrud ms = new MessageCrud();
                    ms.sendMessage(us.getIdUser(), us.getParam(), text.getText());
                    AlertMaker.showSimpleAlert("message envoyé!", null);
                 
                }
            }
        });

    }

    @FXML
    private void appelerHandler(ActionEvent event) {
    }

    @FXML
    private void noterHandler(ActionEvent event) {
    }

    @FXML
    private void signalerHandler(ActionEvent event) {
    }

    @FXML
    private void clear(ActionEvent event) {

    }

    @FXML
    private void actionBtn(ActionEvent event) {
//         get a handle to the stage
//        System.out.println("ji");
//        System.out.println(text.getText());
//        Stage stage = (Stage) text.getScene().getWindow();
//         do what you have to do
//        stage.close();
    }

}
