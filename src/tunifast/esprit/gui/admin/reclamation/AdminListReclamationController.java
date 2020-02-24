/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.admin.reclamation;

import tunifast.esprit.gui.profile.reclamation.*;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Entitie.Reclamation;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.AnnonceCrud;
import tunifast.esprit.Service.ReclamationCrud;
import tunifast.esprit.Service.ReservationCrud;
import tunifast.esprit.Service.UserCrud;
import tunifast.esprit.Utils.TuniFastUtil;
import tunifast.esprit.gui.AcceuilController;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class AdminListReclamationController implements Initializable {

    @FXML
    private TableColumn<Reclamation, String> dateCol;
    @FXML
    private TableColumn<Reclamation, String> userCol;
    @FXML
    private TableColumn<Reclamation, String> detailsCol;
    @FXML
    private TableColumn<Reclamation, String> etatRecCol;
    @FXML
    private TableColumn<Reclamation, String> etatUserCol;
    @FXML
    private JFXTextField filterField;
    @FXML
    private TableView<Reclamation> reclamationTable;
    @FXML
    private Pagination pagination;
    private static final int ROWS_PER_PAGE = 8;
    private ObservableList<Reclamation> masterData = FXCollections.observableArrayList();
    private FilteredList<Reclamation> filteredData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setupData();
        filteredData = new FilteredList<>(masterData, p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(
                    reclamation -> newValue == null || newValue.isEmpty() || reclamation.getDateReclamation().toLowerCase()
                    .contains(newValue.toLowerCase())
                    || newValue.isEmpty() || reclamation.getUserReported().toLowerCase()
                    .contains(newValue.toLowerCase()) || reclamation.getEtatReclamation().toLowerCase()
                    .contains(newValue.toLowerCase()) || reclamation.getEtatUser().toLowerCase()
                    .contains(newValue.toLowerCase()) || String.valueOf(reclamation.getIdReclamation()).toLowerCase().contains(newValue.toLowerCase())
            );
            changeTableView(pagination.getCurrentPageIndex(), ROWS_PER_PAGE);
        });
        dateCol.setCellValueFactory((new PropertyValueFactory<Reclamation, String>("dateReclamation")));
        userCol.setCellValueFactory((new PropertyValueFactory<Reclamation, String>("userReported")));
        detailsCol.setCellValueFactory((new PropertyValueFactory<Reclamation, String>("details")));
        etatRecCol.setCellValueFactory((new PropertyValueFactory<Reclamation, String>("etatReclamation")));
        etatUserCol.setCellValueFactory((new PropertyValueFactory<Reclamation, String>("etatUser")));

        int totalPage = (int) (Math.ceil(masterData.size() * 1.0 / ROWS_PER_PAGE));
        pagination.setPageCount(totalPage);
        pagination.setCurrentPageIndex(0);
        changeTableView(0, ROWS_PER_PAGE);
        pagination.currentPageIndexProperty().addListener(
                (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));

    }

    public void setupData() {

//            Reclamation rec = new Reclamation(54, "sdfs", "sdfsd", "sdfsdf", "2020-02-02", "sfsdf", "sdfsdf");
//            masterData.add(rec);
//           Reclamation rec1 = new Reclamation(54, "1", "2", "3", "2020-02-02", "4", "5");
//            masterData.add(rec1);
//            ReclamationCrud recC = new ReclamationCrud();
//            ArrayList<Reclamation> rs = new ArrayList<Reclamation>();
//            rs = recC.adminConsRec();
        masterData.clear();
        ReclamationCrud recC = new ReclamationCrud();

        UserSession us = UserSession.getInstance();
        UserCrud uc = new UserCrud();
        ArrayList<Reclamation> rs = new ArrayList<Reclamation>();

        rs = recC.adminConsRec();

        for (int i = 0; i < rs.size(); i++) {
            Reclamation rec = new Reclamation();
            rec.setIdReclamation(rs.get(i).getIdReclamation());
            rec.setDateReclamation(rs.get(i).getDateReclamation());
            rec.setUserReported(rs.get(i).getUserReported());
            rec.setDetails(rs.get(i).getDetails());

            rec.setEtatReclamation(rs.get(i).getEtatReclamation());
            rec.setEtatUser(rs.get(i).getEtatUser());
            masterData.add(rec);

        }

    }

    private void changeTableView(int index, int limit) {

        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, masterData.size());

        int minIndex = Math.min(toIndex, filteredData.size());
        SortedList<Reclamation> sortedData = new SortedList<>(
                FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
        sortedData.comparatorProperty().bind(reclamationTable.comparatorProperty());

        reclamationTable.setItems(sortedData);

    }

    @FXML
    private void details(ActionEvent event) {
        Reclamation selectedAn = reclamationTable.getSelectionModel().getSelectedItem();
        UserCrud uc = new UserCrud();
        ReclamationCrud recC = new ReclamationCrud();
        int x = uc.getIduByUsername(selectedAn.getUserReported());
     
        recC.setEtatRec(selectedAn.getIdReclamation());
        UserSession us = new UserSession().getInstance();
        ReservationCrud res = new ReservationCrud();

        if (selectedAn == null) {
            AlertMaker.showSimpleAlert("aucune reclamation selectionné !! ", "veuillez selecionner une reclamation");
        } else {
            TuniFastUtil.parSession(selectedAn.getIdReclamation());
            TuniFastUtil.loadWindow(getClass().getResource("readReclamation.fxml"), "list des annonces", null);

        }
    }

    @FXML
    private void profile(ActionEvent event) {
//        Reclamation selectedAn = reclamationTable.getSelectionModel().getSelectedItem();
//        TuniFastUtil.parSession(selectedAn.getIdReclamation());
//TuniFastUtil.loadWindow(getClass().getResource("../profile/profilePub.fxml"), "profile", null);
//
        Reclamation selectedAn = reclamationTable.getSelectionModel().getSelectedItem();

        UserCrud uc = new UserCrud();

        int x = uc.getIduByUsername(selectedAn.getUserReported());

        ReclamationCrud recC = new ReclamationCrud();

    
        recC.setEtatRec(selectedAn.getIdReclamation());
        TuniFastUtil.parSession(x);

        try {
// URL LINK 

            URL fxmlURL = Paths.get("C:\\Users\\mohamedazizhaddad\\Documents\\NetBeansProjects89\\TuniFast0\\src\\tunifast\\esprit\\gui\\profile\\profilePub.fxml").toUri().toURL();
//            FXMLLoader loader = new FXMLLoader(fxmlURL));
            Parent parent = FXMLLoader.load(fxmlURL);

            Stage stage = null;
            stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("profile");
            stage.setScene(new Scene(parent));
            stage.show();
            //setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void adver(ActionEvent event) {
        Reclamation selectedAn = reclamationTable.getSelectionModel().getSelectedItem();
        UserCrud uc = new UserCrud();
        ReclamationCrud recC = new ReclamationCrud();
        int x = uc.getIduByUsername(selectedAn.getUserReported());

        recC.setEtatRec(selectedAn.getIdReclamation());
        System.out.println("envooie advertisment mail");
    }

    @FXML
    private void suspendre(ActionEvent event) {
        Reclamation selectedAn = reclamationTable.getSelectionModel().getSelectedItem();
        UserCrud uc = new UserCrud();
        ReclamationCrud recC = new ReclamationCrud();
        int x = uc.getIduByUsername(selectedAn.getUserReported());
        recC.setEtatUser(selectedAn.getIdReclamation());
        recC.setEtatRec(selectedAn.getIdReclamation());
        uc.suspendre(x);

        AlertMaker.showSimpleAlert("succé", "utilisateur suspendu");
    }

    @FXML
    private void ignorer(ActionEvent event) { 
        Reclamation selectedAn = reclamationTable.getSelectionModel().getSelectedItem();
           ReclamationCrud recC = new ReclamationCrud();
           recC.setEtatRec(selectedAn.getIdReclamation());

    }

    @FXML
    private void actualisez(ActionEvent event) {
       
        setupData();
        changeTableView(0, ROWS_PER_PAGE);
    
    }
}
