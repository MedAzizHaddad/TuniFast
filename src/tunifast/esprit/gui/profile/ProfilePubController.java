/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.profile;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import tunifast.esprit.Entitie.Reclamation;
import tunifast.esprit.Entitie.User;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Entitie.rate;
import tunifast.esprit.Service.MessageCrud;
import tunifast.esprit.Service.ReclamationCrud;
import tunifast.esprit.Service.UserCrud;
import tunifast.esprit.Service.rateCrud;
import tunifast.esprit.Service.statserv;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class ProfilePubController implements Initializable {

    private JFXTextField txtNom;
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
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXTextField txtRole;
    @FXML
    private Label txtRate;
    @FXML
    private Rating rateDef;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
        txtUsername.setEditable(false);
        txtRole.setEditable(false);
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
            statserv ss = new statserv();
     txtRate.setText(new DecimalFormat("##.##").format(ss.getRateById(us.getParam())));
      //  txtRate.setText(Double.toString(ss.getRateById(us.getParam())));
        rateDef.setRating(ss.getRateById(us.getParam()));
        txtUsername.setText(res.getUsername());
        txtRole.setText(res.getRole());
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
        UserSession us = UserSession.getInstance();
        changingField.getChildren().clear();
      
        Rating rate = new Rating();
        rate.setRating(3);
        rate.setPadding(new Insets(53, 20, 53, 20));
        
        changingField.getChildren().addAll(rate);
        
      rate.ratingProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            System.out.println("Rating :- "+newValue);
        double nbrRate = (double) newValue ; // rate.getRating();
        rateCrud rat = new rateCrud();
        rate r = new rate(nbrRate,us.getParam());
        rat.ajouterRate(r);
          
        });
//        double nbrRate = rate.getRating();
//   
//        rateCrud rat = new rateCrud();
//        rate r = new rate(nbrRate,us.getParam());
//        rat.ajouterRate(r);
        
    }

    @FXML
    private void signalerHandler(ActionEvent event) {

        UserSession us = UserSession.getInstance();
        changingField.getChildren().clear();

        TextField textField = new TextField();
        textField.setPrefSize(200, 150);

        text.setPrefSize(200, 150);
        text.setPromptText("vous pouvez ecrire les details ici ");
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
//                      MessageCrud ms = new MessageCrud();
//                    ms.sendMessage(us.getIdUser(), us.getParam(), text.getText());
//                    AlertMaker.showSimpleAlert("message envoyé!", null);
                    ReclamationCrud recCrud = new ReclamationCrud();
                    Reclamation rec = new Reclamation();
        UserSession us = UserSession.getInstance();
        UserCrud uc = new UserCrud();
                    rec.setUserReporter(uc.getUsernameByIdu(us.getIdUser()));
                    rec.setUserReported(uc.getUsernameByIdu(us.getParam()));
               //     System.out.println(us.getIdUser() +" *************"+us.getParam());
                    rec.setDetails(text.getText());
                   
                    recCrud.ajouterReclamation(rec);
                    
                 Notifications notificationBuilder = Notifications.create()
                    .title("Congratulations ")
                    .text("You've successfully created your reclamation")
                    .graphic(new ImageView())
                    //.hideAfter(Duration.seconds(5)) eyyy la7dha njarbou
                    .position(Pos.TOP_RIGHT);
        
            notificationBuilder.darkStyle();
            notificationBuilder.show();
                      AlertMaker.showSimpleAlert(null, "reclamation  envoyé!");
                      AlertMaker.showSimpleAlert(null, "reclamation  envoyé!");

                }
            }
        });

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
