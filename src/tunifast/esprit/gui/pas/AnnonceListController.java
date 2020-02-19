/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.pas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.AnnonceCrud;
import tunifast.esprit.Service.PasServices;
import tunifast.esprit.Service.ReservationCrud;
import tunifast.esprit.Utils.DataBase;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class AnnonceListController implements Initializable {

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
    private TableColumn<Annonce, String> DepartColumn;
    @FXML
    private TableColumn<Annonce, String> ArriveeColumn;
    @FXML
    private TableColumn<Annonce, String> DateColumn;
    @FXML
    private TableColumn<Annonce, String> HeureColumn;
    @FXML
    private TableColumn<Annonce, Integer> PlaceColumn;
    @FXML
    private Text titre;
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
                    annonce -> newValue == null || newValue.isEmpty() || annonce.getDateAnnonce().toLowerCase()
                    .contains(newValue.toLowerCase()) || annonce.getLieuArrivee().toLowerCase()
                    .contains(newValue.toLowerCase()) || annonce.getLieuDepart().toLowerCase()
                    .contains(newValue.toLowerCase()) || annonce.getHeureAnnonce().toLowerCase()
                    .contains(newValue.toLowerCase()) || String.valueOf(annonce.getNbrPlaceDispo()).toLowerCase().contains(newValue.toLowerCase())
            );
            changeTableView(pagination.getCurrentPageIndex(), ROWS_PER_PAGE);
        });

        //                 DepartColumn.setCellValueFactory(data -> data.getValue().getLieuDepart());
//        lastNameColumn.setCellValueFactory(data -> data.getValue().lastNameProperty());  
        DepartColumn.setCellValueFactory((new PropertyValueFactory<Annonce, String>("lieuDepart")));
        ArriveeColumn.setCellValueFactory((new PropertyValueFactory<Annonce, String>("lieuArrivee")));
        DateColumn.setCellValueFactory((new PropertyValueFactory<Annonce, String>("dateAnnonce")));
        HeureColumn.setCellValueFactory((new PropertyValueFactory<Annonce, String>("heureAnnonce")));
        PlaceColumn.setCellValueFactory((new PropertyValueFactory<Annonce, Integer>("nbrPlaceDispo")));

        int totalPage = (int) (Math.ceil(masterData.size() * 1.0 / ROWS_PER_PAGE));
        pagination.setPageCount(totalPage);
        pagination.setCurrentPageIndex(0);
        changeTableView(0, ROWS_PER_PAGE);
        pagination.currentPageIndexProperty().addListener(
                (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));

    }

    public void setupData() {

       
        
        
        


        
        
        
            //        AnnonceCrud an = new AnnonceCrud();
//        ArrayList<Annonce> rs = new ArrayList<Annonce>();
//        rs = an.checkDataAvaliPas();
//        rs.get(0).getIdAnnonce();
//        for (int i = 0; i < rs.size(); i++) {
//            //    System.out.println(anno.affListToutAnn().get(i));
//            int idAnnonce = rs.get(i).getIdAnnonce();
//            int idUser = rs.get(i).getIdUser();
//            String lieuDepart = rs.get(i).getLieuDepart();
//            String lieuArrivee = rs.get(i).getLieuArrivee();
//            String dateAnnonce = rs.get(i).getDateAnnonce();
//            String heureAnnonce = rs.get(i).getHeureAnnonce();
//            int nbrPlaceDispo = rs.get(i).getNbrPlaceDispo();
//            int nbPlaceReser = rs.get(i).getNbPlaceReser();
//------------------------------------------------------
 try {
            DataBase handler = DataBase.getInstance();
            String qu = "SELECT * FROM annonce INNER JOIN user "
                    + "ON annonce.idUser = user.idUser AND user.role = 'chauffeur'"
                    + " AND annonce.dateAnnonce > now() AND `nbrPlaceDispo` > 0";
            ResultSet rs = handler.execQuery(qu);

            while (rs.next()) {

                int idAnnonce = rs.getInt("idAnnonce");
                int idUser = rs.getInt("idUser");
                String lieuDepart = rs.getString("lieuDepart");
                String lieuArrivee = rs.getString("lieuArrivee");
                String dateAnnonce = rs.getString("dateAnnonce");
                String heureAnnonce = rs.getString("heureAnnonce");
                int nbrPlaceDispo = rs.getInt("nbrPlaceDispo");
                int nbPlaceReser = rs.getInt("nbPlaceReser");

                Annonce a = new Annonce(idAnnonce, idUser, lieuDepart, lieuArrivee, dateAnnonce, heureAnnonce, nbrPlaceDispo, nbPlaceReser);
                masterData.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceListController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void reserver(ActionEvent event) {

        UserSession us = new UserSession().getInstace();
        ReservationCrud res = new ReservationCrud();
        Annonce selectedAn = reservationTable.getSelectionModel().getSelectedItem();
        //  System.out.println(selectedAn);
        if (selectedAn == null) {
            AlertMaker.showSimpleAlert("aucune annonce selectionné !! ", "veuillez selecionner une annonce");
        } else {

            TextInputDialog dialog = new TextInputDialog("1 , 2 , 3 ou 4");
            dialog.setTitle("Nombre de places à reserver");
            dialog.setHeaderText(null);
            dialog.setContentText("Veuillez saisir nombre de place à reserver");
     

// Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                 if ((!result.get().equals("1")) && (!result.get().equals("2")) && (!result.get().equals("3")) && (!result.get().equals("4"))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Erreur de saisi !! (1,2,3 ou 4 )");
                    alert.showAndWait();
                } else if (Integer.parseInt(result.get()) > selectedAn.getNbrPlaceDispo()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Nombre de place disponible sont insuffisants pour vous!");
                    alert.showAndWait();

                } else { System.out.println("hi");
                 PasServices.reserver(selectedAn.getIdAnnonce(), us.getIdUser(), Integer.parseInt(result.get()));
                     JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showMaterialDialog0(root, Arrays.asList(btn), "reservation bien ajouté", null);
                }

            }}
    }

    @FXML
    private void afficher(ActionEvent event) {
    }
}
