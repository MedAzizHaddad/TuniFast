/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.chauf;

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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Entitie.Reservation;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.AnnonceCrud;
import tunifast.esprit.Service.ReservationCrud;
import tunifast.esprit.Utils.TuniFastUtil;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class ResConsChaufController implements Initializable {

    @FXML
    private Text titre;
    @FXML
    private JFXTextField filterField;
    @FXML
    private TableView<Annonce> reservationTable;
    @FXML
    private Pagination pagination;
    private static final int ROWS_PER_PAGE = 8;
    private ObservableList<Annonce> masterData = FXCollections.observableArrayList();
    private FilteredList<Annonce> filteredData;
    @FXML
    private TableColumn<Annonce, String> DateAnCol;
    @FXML
    private TableColumn<Annonce, String> Departcol;
    @FXML
    private TableColumn<Annonce, String> ArriveeCol;
    @FXML
    private TableColumn<Annonce, String> DateSorCol;
    @FXML
    private TableColumn<Annonce, String> HeureSorCol;
    @FXML
    private TableColumn<Annonce, Integer> NbPlDispoCol;
    @FXML
    private TableColumn<Annonce, Integer> NbPlResCol;
    @FXML
    private StackPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupData();
        filteredData = new FilteredList<>(masterData, p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(
                    reservation -> newValue == null || newValue.isEmpty() || reservation.getDateAnnonce().toLowerCase()
                    .contains(newValue.toLowerCase()) || reservation.getLieuArrivee().toLowerCase()
                    .contains(newValue.toLowerCase()) || reservation.getLieuDepart().toLowerCase()
                    .contains(newValue.toLowerCase()) || reservation.getHeureAnnonce().toLowerCase()
                    .contains(newValue.toLowerCase())
            );
            changeTableView(pagination.getCurrentPageIndex(), ROWS_PER_PAGE);
        });

        DateAnCol.setCellValueFactory(new PropertyValueFactory<Annonce, String>("dateAnnPost"));
        Departcol.setCellValueFactory(new PropertyValueFactory<Annonce, String>("lieuDepart"));
        ArriveeCol.setCellValueFactory(new PropertyValueFactory<Annonce, String>("lieuArrivee"));
        DateSorCol.setCellValueFactory(new PropertyValueFactory<Annonce, String>("dateAnnonce"));
        HeureSorCol.setCellValueFactory(new PropertyValueFactory<Annonce, String>("heureAnnonce"));
        NbPlDispoCol.setCellValueFactory(new PropertyValueFactory<Annonce, Integer>("nbrPlaceDispo"));
        NbPlResCol.setCellValueFactory(new PropertyValueFactory<Annonce, Integer>("nbPlaceReser"));

        int totalPage = (int) (Math.ceil(masterData.size() * 1.0 / ROWS_PER_PAGE));
        pagination.setPageCount(totalPage);
        pagination.setCurrentPageIndex(0);
        changeTableView(0, ROWS_PER_PAGE);
        pagination.currentPageIndexProperty().addListener(
                (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));

    }

    public void setupData() {
        UserSession us = new UserSession().getInstance();
        AnnonceCrud an = new AnnonceCrud();
        ArrayList<Annonce> result = new ArrayList<Annonce>();

        result = an.ReadAnnonceChauf(us.getIdUser());
        for (int i = 0; i < result.size(); i++) {
            int idAnnonce = result.get(i).getIdAnnonce();
            int idUser = result.get(i).getIdUser();
            String dateAnnPost = result.get(i).getDateAnnPost();
            String lieuDepart = result.get(i).getLieuDepart();
            String lieuArrivee = result.get(i).getLieuArrivee();
            String dateAnnonce = result.get(i).getDateAnnonce();
            String heureAnnonce = result.get(i).getHeureAnnonce();
            int nbrPlaceDispo = result.get(i).getNbrPlaceDispo();
            int nbPlaceReser = result.get(i).getNbPlaceReser();

            Annonce a = new Annonce(idAnnonce, idUser, lieuDepart, lieuArrivee, dateAnnonce, heureAnnonce, nbrPlaceDispo, nbPlaceReser, dateAnnPost, lieuDepart);
            masterData.add(a);
        }
    }

    private void changeTableView(int index, int limit) {

        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, masterData.size());

        int minIndex = Math.min(toIndex, filteredData.size());
        SortedList<Annonce> sortedData = new SortedList<>(
                FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
        sortedData.comparatorProperty().bind(reservationTable.comparatorProperty());

        reservationTable.setItems(sortedData);
    }


    @FXML
    private void refresh(ActionEvent event) {
    }

    @FXML
      private void afficher(ActionEvent event) {
        UserSession us = new UserSession().getInstance();
        Annonce an = new Annonce();
        Annonce selectedAn = reservationTable.getSelectionModel().getSelectedItem();
        ;

        if (selectedAn == null) {
            AlertMaker.showSimpleAlert("aucune annonce selectionné !! ", "veuillez selecionner une annonce");
        } else {
            //System.out.println(selectedAn.getIdAnnonce());
                        TuniFastUtil.parSession(selectedAn.getIdAnnonce());
           TuniFastUtil.loadWindow(getClass().getResource("AnnonceReadChauf.fxml"), "list des annonce que vous avez créer", null);

        }
//        }

    }
}
