/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.project.gui;

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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class StartPageController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private AnchorPane espace;

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
    }

    
}
