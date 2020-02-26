/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.profile;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdk.nashorn.internal.ir.CallNode;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Entitie.Reservation;
import tunifast.esprit.Entitie.User;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Entitie.messages;
import tunifast.esprit.Service.AnnonceCrud;
import tunifast.esprit.Service.MessageCrud;
import tunifast.esprit.Service.UserCrud;
import tunifast.esprit.Utils.DataBase;
import tunifast.esprit.Utils.TuniFastUtil;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class ProfilePersController implements Initializable {

    @FXML
    private JFXListView<String> listContacts;
    @FXML
    private ListView<msgCell> listMessages;
    @FXML
    private JFXTextField txtMessage;
    @FXML
    private JFXButton btnMessage;
    private Pane target;
    @FXML
    private Tab tab1;
    @FXML
    private Tab profile;
   public void loadFxml(ActionEvent event) {
        try {
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("reclamation/userListReclamation.fxml"));
            target.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(ProfilePersController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        UserCrud u = new UserCrud();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                affContacts();
            }
        }, 0, 4000);

        listContacts.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                String selectedUsername = listContacts.getSelectionModel().getSelectedItem();
                int selectedId = u.getIduByUsername(selectedUsername);
                System.out.println(selectedId);
                TuniFastUtil.parSession(selectedId);
                affMessages(selectedId);
            }
        }
        );

    }
    
//    public void loadFxml(ActionEvent event) {
//        try {
//            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("reclamation/userListReclamation.fxml"));
//            mesReclamationsPane.getChildren().add(newLoadedPane);
//        } catch (IOException ex) {
//            Logger.getLogger(ProfilePersController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    public void test(){
        System.out.println("jo");
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("reclamation/userListReclamation.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("TuniFast");
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProfilePersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void affContacts() {
        UserCrud u = new UserCrud();
        UserSession us = UserSession.getInstance();
       // System.out.println(us.getIdUser() + "///////" + us.getParam());
        Connection cnx = DataBase.getInstance().getCnx();
        Statement st;
        ObservableList<String> data = FXCollections.observableArrayList();
        ArrayList<messages> result = new ArrayList<messages>();
        MessageCrud m = new MessageCrud();
        result = m.getContacts(us.getIdUser());
        for (int i = 0; i < result.size(); i++) {
            //  data.add(Integer.toString(result.get(i).getExp()));
            data.add(u.getUsernameByIdu(result.get(i).getExp()));
        }
        listContacts.getItems().setAll(data);
    }

    public void affMessages(int i) {
        UserSession us = UserSession.getInstance();
        UserCrud u = new UserCrud();
        //   System.out.println(us.getIdUser());
        ObservableList<String> data = FXCollections.observableArrayList();
        ArrayList<messages> result1 = new ArrayList<messages>();
        MessageCrud m1 = new MessageCrud();

        //------------------
        //--------------
        result1 = m1.getMessages(i, us.getIdUser());
        //  System.out.println(i +"---------+" + us.getIdUser());
        ObservableList<msgCell> msgType = FXCollections.observableArrayList();
        for (int j = 0; j < result1.size(); j++) {
            //  System.out.println(result1.get(j).getExp());
            String sender = u.getUsernameByIdu(result1.get(j).getExp());
            String tt = result1.get(j).getTime();
            msgCell msg = new msgCell(sender + "   sent at : " + tt, true);
            msgCell msg1 = new msgCell(result1.get(j).getContent(), false);
            msgType.add(0, msg1);
            msgType.add(0, msg);
            listMessages.setCellFactory(param -> new ListCell<msgCell>() {
                static final String ACTIVE_CLASS = "active";
                //     @Override

                protected void updateItem(msgCell item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null || item.getTxt() == null) {
                        setText(null);
                        getStyleClass().remove(ACTIVE_CLASS);
                    } else {
                        if (item.isDate()) {
                            setText(item.getTxt());
                        } else {
                            setMinWidth(0);
                            setMaxWidth(100);
                            setPrefWidth(70);
                            setWrapText(true);
                            setText(item.getTxt());
                        }
                        if (item.isDate() && !getStyleClass().contains(ACTIVE_CLASS)) {
                            getStyleClass().add(ACTIVE_CLASS);
                        } else {
                            getStyleClass().remove(ACTIVE_CLASS);
                        }
                    }
                }
            });

            listMessages.getItems().setAll(msgType);
            listMessages.getStylesheets().add(
                    this.getClass().getResource("activity.css").toExternalForm()
            );
            listMessages.scrollTo(listMessages.getItems().size());
        }
    }

    @FXML
    private void sendMessage(ActionEvent event) {
        UserSession us = UserSession.getInstance();
        MessageCrud ms = new MessageCrud();
//        System.out.println(us.getParam());
//        System.out.println(txtMessage.getText());
        ms.sendMessage(us.getIdUser(), us.getParam(), txtMessage.getText());
        affMessages(us.getParam());
        txtMessage.clear();
    }

    class msgCell {

        private final StringProperty contentProperty;
        private final BooleanProperty dateProperty;

        public msgCell(String txt, boolean date) {
            this.contentProperty = new SimpleStringProperty(txt);
            this.dateProperty = new SimpleBooleanProperty(date);
        }

        public String getTxt() {
            return contentProperty.get();
        }

        public StringProperty txtProperty() {
            return contentProperty;
        }

        public boolean isDate() {
            return dateProperty.getValue() != null
                    ? dateProperty.getValue()
                    : false;
        }

        public BooleanProperty activeProperty() {
            return dateProperty;
        }
    }
}
