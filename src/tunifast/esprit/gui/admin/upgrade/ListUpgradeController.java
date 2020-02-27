/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.admin.upgrade;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Entitie.Upgrade;
import tunifast.esprit.Service.AnnonceCrud;
import tunifast.esprit.Service.UpgradeCrud;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class ListUpgradeController implements Initializable {

    @FXML
    private JFXTextField filterField;
    @FXML
    private TableView<Upgrade> upgradeTable;
 
    private static final int ROWS_PER_PAGE = 8;
    private ObservableList<Upgrade> masterData = FXCollections.observableArrayList();
    private FilteredList<Upgrade> filteredData;
    @FXML
    private TableColumn<Upgrade, String> dateCol;
    @FXML
    private TableColumn<Upgrade, Integer> userCol;
    @FXML
    private TableColumn<Upgrade, String>trajetCol;
    @FXML
    private TableColumn<Upgrade, Integer> expCol;
    @FXML
    private TableColumn<Upgrade, String>detCol;
    @FXML
    private Pagination pagination;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    setupData();
      filteredData = new FilteredList<>(masterData, p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(
                    Upgrade -> newValue == null || newValue.isEmpty() || Upgrade.getCv().toLowerCase()
                    .contains(newValue.toLowerCase()) ||  String.valueOf(Upgrade.getIdUser()).toLowerCase().contains(newValue.toLowerCase())
            );
            changeTableView(pagination.getCurrentPageIndex(), ROWS_PER_PAGE);
        });
    
        dateCol.setCellValueFactory((new PropertyValueFactory<Upgrade, String>("DateTime")));
        userCol.setCellValueFactory((new PropertyValueFactory<Upgrade, Integer>("idUer")));
        trajetCol.setCellValueFactory((new PropertyValueFactory<Upgrade, String>("trajet")));
        expCol.setCellValueFactory((new PropertyValueFactory<Upgrade, Integer>("exp")));
        detCol.setCellValueFactory((new PropertyValueFactory<Upgrade, String>("details")));

        int totalPage = (int) (Math.ceil(masterData.size() * 1.0 / ROWS_PER_PAGE));
        pagination.setPageCount(totalPage);
        pagination.setCurrentPageIndex(0);
        changeTableView(0, ROWS_PER_PAGE);
        pagination.currentPageIndexProperty().addListener(
                (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));

        
        
    
    }    
    
      public void setupData() {

        masterData.clear();
          UpgradeCrud upC = new UpgradeCrud();
        ArrayList<Upgrade> rs = new ArrayList<Upgrade>();
        rs = upC.getAllUpgrade();
        for (int i = 0; i < rs.size(); i++) {
            int idUp = rs.get(i).getIdUp();
            String cv = rs.get(i).getCv();
            int exp = rs.get(i).getExp();
            String trajet = rs.get(i).getTrajet();
            String details = rs.get(i).getDetails();
            int user = rs.get(i).getIdUser();
            Date DateTime = rs.get(i).getDateTime();
            String etat = rs.get(i).getEtat();
            Upgrade up = new Upgrade(idUp, cv, exp, trajet, details, user, DateTime, details);
         //   Annonce a = new Annonce(idAnnonce, idUser, lieuDepart, lieuArrivee, dateAnnonce, heureAnnonce, nbrPlaceDispo, nbPlaceReser);
            masterData.add(up);
        }

    }

      private void changeTableView(int index, int limit) {
        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, masterData.size());
        int minIndex = Math.min(toIndex, filteredData.size());
        SortedList<Upgrade> sortedData = new SortedList<>(
                FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
        sortedData.comparatorProperty().bind(upgradeTable.comparatorProperty());
        upgradeTable.setItems(sortedData);
    }
    
}
