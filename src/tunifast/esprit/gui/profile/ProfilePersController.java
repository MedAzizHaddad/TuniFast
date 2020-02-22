/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.profile;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import jdk.nashorn.internal.ir.CallNode;
import tunifast.esprit.Entitie.Reservation;
import tunifast.esprit.Entitie.User;
import tunifast.esprit.Entitie.messages;
import tunifast.esprit.Service.MessageCrud;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class ProfilePersController implements Initializable {

    @FXML
    private JFXListView<String> listContacts;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> data = FXCollections.observableArrayList();
        ArrayList<messages> result = new ArrayList<messages>();
        MessageCrud m = new MessageCrud();
        result = m.userExp(1);
        for (int i = 0; i < result.size(); i++) {
            data.add(Integer.toString(result.get(i).getExp()));
        }

        listContacts.getItems().setAll(data);
      listContacts.setOnMouseClicked( new EventHandler<MouseEvent>() {
     

            @Override
            public void handle(MouseEvent event) {
               
                

             

            }
        });
                
                
    }

    public void contactList() {
        //ArrayList<String> result = new ArrayList<String>();

    }

}
