/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.admin.offre;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tunifast.esprit.Service.offreService;
import tunifast.esprit.Entitie.offre;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import tunifast.esprit.Service.UserService;
import java.util.Arrays;
import java.util.Set;
import tunifast.esprit.Utils.email;
import java.time.LocalDate;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class OffreController implements Initializable {

    @FXML
    private ComboBox<Integer> user;

    @FXML
    private JFXTextField code;

    @FXML
    private JFXTextField solde;

    @FXML
    private JFXTextField search;

    @FXML
    private TableView<offre> tabview;

    @FXML
    private TableColumn<offre, String> c1;

    @FXML
    private TableColumn<offre, String> c2;

    @FXML
    private TableColumn<offre, String> c3;

    @FXML
    private TableColumn<offre, String> c4;

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
    public void Ajouter() {

//        int User = user.getValue();
//            int Code = Integer.valueOf(code.getText());
//            float Solde = Float.valueOf(solde.getText());
//        System.out.println(User + "// "+ Code + "//"+Solde );

        try {

            int User = user.getValue();
            int Code = Integer.valueOf(code.getText());
            float Solde = Float.valueOf(solde.getText());

            offreService servoffre = new offreService();

            offre off = new offre(User, Code, Solde);
            servoffre.ajouter(off);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ajout succés");
            alert.setHeaderText(null);
            alert.setContentText("Offre ajouteé ");
            alert.showAndWait();
            Afficher();
            user.setValue(0);
            solde.setText("");
            code.setText("");
        } catch (SQLException ex) {

            System.out.println(ex);
        }

        /*   }else{
            
          Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Alert");
    alert.setHeaderText("Error Saisir");
    alert.setContentText("Dépasser 5 ");
    alert.showAndWait();
            }*/
    }

    @FXML
    public void Modifer() {
        offreService tmp = new offreService();
        if (!tmp.equals(tabview.getSelectionModel().getSelectedItem())) {
            try {

                int User = user.getValue();
                int Code = Integer.valueOf(code.getText());
                float Solde = Float.valueOf(solde.getText());

                offreService servoffre = new offreService();

                offre tmp2 = tabview.getSelectionModel().getSelectedItem();
                offre off = new offre(tmp2.getIdoff(), User, Code, Solde);
                servoffre.update(off, tmp2.getIdoff());
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Modifier succés");
                alert.setHeaderText(null);
                alert.setContentText("Offre modifieé ");
                alert.showAndWait();
                Afficher();
                user.setValue(0);
                solde.setText("");
                code.setText("");

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    @FXML
    public void supprimer() {
        offreService tmp = new offreService();
        if (!tmp.equals(tabview.getSelectionModel().getSelectedItem())) {
            try {
                offreService ser = new offreService();
                offre tmp2 = tabview.getSelectionModel().getSelectedItem();
                ser.delete(tmp2.getIdoff());
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Supprimer succés");
                alert.setHeaderText(null);
                alert.setContentText("Offre est suprimeé ");
                alert.showAndWait();
                Afficher();
                user.setValue(0);
                solde.setText("");
                code.setText("");
            } catch (SQLException ex) {

                System.out.println(ex);
            }
        }
    }

    public void Afficher() {
        try {
            offreService servoffre = new offreService();
            List<offre> list = servoffre.readAll();
            System.out.println(list);
            ObservableList<offre> cls = FXCollections.observableArrayList();
            for (offre aux : list) {
                cls.add(new offre(aux.getIdoff(), aux.getIdoUser(), aux.getCode(), aux.getSolode()));
            }

            tabview.setItems(cls);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    public void GetData() {
        try {
            offre of = tabview.getSelectionModel().getSelectedItem();
            user.setValue(of.getIdoUser());
            code.setText(String.valueOf(of.getCode()));
            solde.setText(String.valueOf(of.getSolode()));

        } catch (Exception ex) {

            System.out.println(ex);
        }

    }

    @FXML
    public void Search() {
        String S = search.getText();
        try {
            offreService servoffre = new offreService();
            List<offre> list = servoffre.Search(S);
            System.out.println(list);
            ObservableList<offre> cls = FXCollections.observableArrayList();
            for (offre aux : list) {
                cls.add(new offre(aux.getIdoff(), aux.getIdoUser(), aux.getCode(), aux.getSolode()));
            }

            tabview.setItems(cls);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    public void userid() throws SQLException {

        user.getItems().addAll(0);
        UserService SerU = new UserService();
        List<Integer> list = SerU.Getiduser();
        user.getItems().addAll(list);
        code.setText("");
        solde.setText("");

    }

    @FXML
    public void semail() throws Exception {
        email e = new email();
        e.sendEmail("melek.hentati@esprit.tn", "OFFRE", "New OFFRE ");

    }

}
