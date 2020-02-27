/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.chauf;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.AnnonceCrud;
import tunifast.esprit.Service.ReservationCrud;
import tunifast.esprit.Utils.DataBase;
import tunifast.esprit.Utils.TuniFastUtil;
import tunifast.esprit.gui.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class ReadMesAnnChaufController implements Initializable {

       Connection cnx;
    Statement st;

    public ReadMesAnnChaufController() {
        cnx = DataBase.getInstance().getCnx();
    }


    private static final int ROWS_PER_PAGE = 8;
    private ObservableList<Annonce> masterData = FXCollections.observableArrayList();
    private FilteredList<Annonce> filteredData;
    
    @FXML
    private Text titre;
    @FXML
    private JFXTextField filterField;
     @FXML
    private TableColumn<Annonce, String> DepartColumn;
    @FXML
    private TableColumn<Annonce, String> dateAnColumn;
    @FXML
    private TableColumn<Annonce, String> ArriveeColumn;
    @FXML
    private TableColumn<Annonce, String> DateColumn;
    @FXML
    private TableColumn<Annonce, String> HeureColumn;
    @FXML
    private TableColumn<Annonce, Integer> PlaceDispColumn;
    @FXML
    private TableColumn<Annonce, Integer> PlaceResColumn;
    @FXML
    private Pagination pagination;
    @FXML
    private StackPane root;
    @FXML
    private TableView<Annonce> annonceTable; 
   
 
    

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
         
           DepartColumn.setCellValueFactory((new PropertyValueFactory<Annonce, String>("lieuDepart")));
        ArriveeColumn.setCellValueFactory((new PropertyValueFactory<Annonce, String>("lieuArrivee")));
        DateColumn.setCellValueFactory((new PropertyValueFactory<Annonce, String>("dateAnnonce")));
        HeureColumn.setCellValueFactory((new PropertyValueFactory<Annonce, String>("heureAnnonce")));
        PlaceDispColumn.setCellValueFactory((new PropertyValueFactory<Annonce, Integer>("nbrPlaceDispo")));
        PlaceResColumn.setCellValueFactory((new PropertyValueFactory<Annonce, Integer>("nbPlaceReser")));
        dateAnColumn.setCellValueFactory((new PropertyValueFactory<Annonce, String>("dateAnnPost")));
         
             int totalPage = (int) (Math.ceil(masterData.size() * 1.0 / ROWS_PER_PAGE));
        pagination.setPageCount(totalPage);
        pagination.setCurrentPageIndex(0);
        changeTableView(0, ROWS_PER_PAGE);
        pagination.currentPageIndexProperty().addListener(
                (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));

         
         
    }    
public void setupData() {
UserSession us = UserSession.getInstance();
        masterData.clear();
        AnnonceCrud an = new AnnonceCrud();
        ArrayList<Annonce> rs = new ArrayList<Annonce>();
        rs = an.getAllChauAnnonceById(us.getIdUser());
        for (int i = 0; i < rs.size(); i++) {
            int idAnnonce = rs.get(i).getIdAnnonce();
            int idUser = rs.get(i).getIdUser();
            String lieuDepart = rs.get(i).getLieuDepart();
            String lieuArrivee = rs.get(i).getLieuArrivee();
            String dateAnnonce = rs.get(i).getDateAnnonce();
            String heureAnnonce = rs.get(i).getHeureAnnonce();
            int nbrPlaceDispo = rs.get(i).getNbrPlaceDispo();
            int nbPlaceReser = rs.get(i).getNbPlaceReser();
            String dateAnPost = rs.get(i).getDateAnnPost();
        
            Annonce a =  new Annonce(idAnnonce, idUser, lieuDepart, lieuArrivee, dateAnnonce, heureAnnonce, nbrPlaceDispo, nbPlaceReser, dateAnPost, lieuDepart);
//            Annonce a = new Annonce(idAnnonce, idUser, lieuDepart, lieuArrivee, dateAnnonce, heureAnnonce, nbrPlaceDispo, nbPlaceReser);
            masterData.add(a);
        }}

 private void changeTableView(int index, int limit) {

        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, masterData.size());

        int minIndex = Math.min(toIndex, filteredData.size());
        SortedList<Annonce> sortedData = new SortedList<>(
                FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
        sortedData.comparatorProperty().bind(annonceTable.comparatorProperty());

        annonceTable.setItems(sortedData);

    
    }

    @FXML
    private void afficher(ActionEvent event) {
             UserSession us = new UserSession().getInstance();
        ReservationCrud res = new ReservationCrud();
        Annonce selectedAn = annonceTable.getSelectionModel().getSelectedItem();
        //  System.out.println(selectedAn);
        if (selectedAn == null) {
            AlertMaker.showSimpleAlert("aucune annonce selectionné !! ", "veuillez selecionner une annonce");
        } else {
            TuniFastUtil.parSession(selectedAn.getIdAnnonce());
            TuniFastUtil.loadWindow(getClass().getResource("detAnnChauf.fxml"), "list de mes annonces", null);
        }
    }


    @FXML
    private void refresh(ActionEvent event) {
          setupData();
        changeTableView(0, ROWS_PER_PAGE);
    }

    @FXML
    private void annuler(ActionEvent event) {
         UserSession us = new UserSession().getInstance();
        ReservationCrud res = new ReservationCrud();
        Annonce selectedAn = annonceTable.getSelectionModel().getSelectedItem();
        //  System.out.println(selectedAn);
        if (selectedAn == null) {
            AlertMaker.showSimpleAlert("aucune annonce selectionné !! ", "veuillez selecionner une annonce");
    } else {
            TuniFastUtil.parSession(selectedAn.getIdAnnonce());
       
         try {
            String requete2 = "UPDATE `annonce` SET  `nbrPlaceDispo`="+ 0 +"  WHERE `idAnnonce` = "+selectedAn.getIdAnnonce()+" ";
            PreparedStatement pst = cnx.prepareStatement(requete2);
                                         
            pst.executeUpdate();
            System.out.println("annoce annulé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        }
         AlertMaker.showSimpleAlert("succée", "annonce annulé");
    }

    @FXML
    private void imprimer(ActionEvent event) {
        System.out.println(" Wait few seconds ...");
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob.printPage(annonceTable)) {
            printerJob.endJob();
            System.out.println("printed");
        }
    }
    
}
