/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.admin.offre;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import tunifast.esprit.Entitie.offre;
import tunifast.esprit.Service.offreService;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class az implements Initializable {

    @FXML
    private JFXTextField code;
    @FXML
    private JFXTextField solde;
    @FXML
    private ComboBox<String> user;
    @FXML
    private TableView<offre> tabview;
    @FXML
    private TableColumn<offre, String> c2;
    @FXML
    private TableColumn<offre, String> c3;
    @FXML
    private TableColumn<offre, String> c4;
    @FXML
    private TableColumn<offre, String> c1;
    @FXML
    private JFXTextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
          
            offreService servoffre = new offreService();
            List<offre> list = servoffre.readAll();
            System.out.println(list);
            ObservableList<offre> cls = FXCollections.observableArrayList();
            for (offre aux : list) {
                cls.add(new offre(aux.getIdoff(), aux.getIdoUser(), aux.getCode(), aux.getSolode()));
            }

            c1.setCellValueFactory(new PropertyValueFactory<>("idoff"));
            c2.setCellValueFactory(new PropertyValueFactory<>("idoUser"));
            c3.setCellValueFactory(new PropertyValueFactory<>("code"));
            c4.setCellValueFactory(new PropertyValueFactory<>("solode"));

            tabview.setItems(cls);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
    }

    @FXML
    private void Modifer(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

    @FXML
    private void userid(MouseEvent event) {
    }

    @FXML
    private void GetData(MouseEvent event) {
    }

    @FXML
    private void Search(KeyEvent event) {
    }

    @FXML
    private void semail(ActionEvent event) {
    }
    
}
