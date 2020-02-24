/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.profile.reclamation;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Entitie.Reclamation;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.AnnonceCrud;
import tunifast.esprit.Service.ReclamationCrud;
import tunifast.esprit.Service.ReservationCrud;
import tunifast.esprit.Service.UserCrud;
import tunifast.esprit.Utils.TuniFastUtil;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class UserListReclamationController implements Initializable {

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
//            
        masterData.clear();
        ReclamationCrud recC = new ReclamationCrud();

        UserSession us = UserSession.getInstance();
        UserCrud uc = new UserCrud();
        ArrayList<Reclamation> rs = new ArrayList<Reclamation>();

        rs = recC.UserConsRec(uc.getUsernameByIdu(us.getIdUser()));
        System.out.println(us.getIdUser());
        System.out.println(uc.getUsernameByIdu(us.getIdUser()));
        System.out.println(rs.size());
        rs = recC.UserConsRec(uc.getUsernameByIdu(us.getIdUser()));

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
        UserSession us = new UserSession().getInstance();
        ReservationCrud res = new ReservationCrud();
        Reclamation selectedAn = reclamationTable.getSelectionModel().getSelectedItem();
         
        if (selectedAn == null) {
            AlertMaker.showSimpleAlert("aucune reclamation selectionné !! ", "veuillez selecionner une reclamation");
        } else {
            TuniFastUtil.parSession(selectedAn.getIdReclamation());
            TuniFastUtil.loadWindow(getClass().getResource("readReclamation.fxml"), "list des annonces", null);
      
        }
    }
}