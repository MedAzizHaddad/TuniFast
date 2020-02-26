/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class StartPageController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private AnchorPane espace;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("login.fxml"));
            espace.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(StartPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void create(ActionEvent event) {
              try {
                  espace.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("inscription.fxml"));
            espace.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(StartPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
