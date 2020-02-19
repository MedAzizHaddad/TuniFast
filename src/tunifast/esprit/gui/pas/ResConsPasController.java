/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.pas;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import tunifast.esprit.Entitie.Reservation;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.ReservationCrud;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class ResConsPasController implements Initializable {

    @FXML
    private TextField filterField;
    @FXML
    private TableView<Reservation> reservationTable;
    @FXML
    private Pagination pagination;
    private static final int ROWS_PER_PAGE = 8;
    private ObservableList<Reservation> masterData = FXCollections.observableArrayList();
    private FilteredList<Reservation> filteredData;
    @FXML
    private TableColumn<Reservation, String> DateResCol;
    @FXML
    private TableColumn<Reservation, String> Departcol;
    @FXML
    private TableColumn<Reservation, String> ArriveeCol;
    @FXML
    private TableColumn<Reservation, String> DateSorCol;
    @FXML
    private TableColumn<Reservation, String> HeureSorCol;
    @FXML
    private TableColumn<Reservation, Integer> PlacesCol;
    @FXML
    private TableColumn<Reservation, Integer> MontantCol;
    @FXML
    private TableColumn<Reservation, String> EtatCol;
    @FXML
    private StackPane root;
    @FXML
    private Text titre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

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

        DateResCol.setCellValueFactory((new PropertyValueFactory<Reservation, String>("dateReservation")));
        Departcol.setCellValueFactory((new PropertyValueFactory<Reservation, String>("lieuDepart")));
        ArriveeCol.setCellValueFactory((new PropertyValueFactory<Reservation, String>("lieuArrivee")));
        DateSorCol.setCellValueFactory((new PropertyValueFactory<Reservation, String>("dateAnnonce")));
        HeureSorCol.setCellValueFactory((new PropertyValueFactory<Reservation, String>("heureAnnonce")));
        PlacesCol.setCellValueFactory((new PropertyValueFactory<Reservation, Integer>("nbPlace")));
        MontantCol.setCellValueFactory((new PropertyValueFactory<Reservation, Integer>("montant")));
        EtatCol.setCellValueFactory((new PropertyValueFactory<Reservation, String>("etatReservation")));

        int totalPage = (int) (Math.ceil(masterData.size() * 1.0 / ROWS_PER_PAGE));
        pagination.setPageCount(totalPage);
        pagination.setCurrentPageIndex(0);
        changeTableView(0, ROWS_PER_PAGE);
        pagination.currentPageIndexProperty().addListener(
                (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));

    }

    public void setupData() {

        //2020-02-18, heureAnnonce=03:22:12
        UserSession us = new UserSession().getInstance();
        //System.out.println(us.getIdUser());
        ReservationCrud res1 = new ReservationCrud();
        ReservationCrud res2 = new ReservationCrud();

        int compteur = 0;
        int pl = 0;
        ArrayList<Reservation> result = new ArrayList<>();
        result = res1.consMesResPa(us.getIdUser());
//res1.consMesResPa(us.getIdUser()).forEach(System.out::println);
        //  res1.consMesResPa(us.getIdUser()).forEach(System.out::println);
        // System.out.println(anno.affListToutAnn().size());
        for (int i = 0; i < result.size(); i++) {

            //System.out.println(emp);
            //    System.out.println(anno.affListToutAnn().get(i));
            int idReservation = result.get(i).getIdReservation();
            int idAnnonce = result.get(i).getIdAnnonce();
            int idUser = result.get(i).getIdUser();
            String dateReservation = result.get(i).getDateReservation();
            String lieuDepart = result.get(i).getLieuDepart();
            String lieuArrivee = result.get(i).getLieuArrivee();
            String dateAnnonce = result.get(i).getDateAnnonce();
            String heureAnnonce = result.get(i).getHeureAnnonce();
            int nbPlace = result.get(i).getNbPlace();
            int montant = result.get(i).getMontant();
            String etatReservation = result.get(i).getEtatReservation();
            // System.out.println(lieuDepart = result.get(i).getLieuDepart());

            Reservation a = new Reservation(idReservation, idAnnonce, idUser, dateReservation, nbPlace, montant, etatReservation, lieuDepart, lieuArrivee, dateAnnonce, heureAnnonce);
            masterData.add(a);

        }

//       masterData.add(new Reservation(00, 0, 0, "2020-02-11", 0, 50, "etat 1", "a", "b", "2020-02-18", "03:22:10"));
//       masterData.add(new Reservation(11, 1, 111, "2020-02-18", 0, 50, "etat 1", "d", "b", "2020-02-18", "03:22:12"));
//       masterData.add(new Reservation(22, 2, 0, "2020-02-18", 0, 50, "etat 1", "e", "b", "2020-02-18", "03:22:12"));
//       masterData.add(new Reservation(33, 3, 0, "2020-02-18", 0, 50, "etat 1", "x", "b", "2020-02-18", "03:22:12"));
    }

    private void changeTableView(int index, int limit) {

        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, masterData.size());

        int minIndex = Math.min(toIndex, filteredData.size());
        SortedList<Reservation> sortedData = new SortedList<>(
                FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
        sortedData.comparatorProperty().bind(reservationTable.comparatorProperty());

        reservationTable.setItems(sortedData);
    }

    @FXML
    private void refresh(ActionEvent event) {
    }

    @FXML
    private void annuler(ActionEvent event) {

        Reservation selectedAn = reservationTable.getSelectionModel().getSelectedItem();
        UserSession us = new UserSession().getInstance();
        if (selectedAn == null) {
            AlertMaker.showSimpleAlert("aucune annonce selectionné !! ", "veuillez selecionner une annonce");

        } else {

            JFXButton ok = new JFXButton("Yes!");
            JFXButton cancel = new JFXButton("Cancel");

            AlertMaker.showMaterialDialog0(root, Arrays.asList(ok, cancel), "Are u sure !", "cinformation text");
            ok.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
                ReservationCrud res = new ReservationCrud();
                res.AnnulerResPas(selectedAn.getIdAnnonce(),selectedAn.getIdReservation(), selectedAn.getNbPlace());
                AlertMaker.showSimpleAlert("done", "annul");

            });
            cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
                System.out.println("canceled operation");
            });

//              
        }

    }

}
