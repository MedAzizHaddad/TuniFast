/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.ui.addbook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class BookAddController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXTextField txtChauf;
    @FXML
    private JFXTextField txtDep;
    @FXML
    private JFXTextField txtArr;
    @FXML
    private JFXTextField txtDat;
    @FXML
    private JFXTextField txtHeure;
    @FXML
    private JFXButton btnReserver;
    @FXML
    private JFXButton btnFermer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
