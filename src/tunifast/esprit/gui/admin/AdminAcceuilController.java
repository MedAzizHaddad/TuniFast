/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.admin;

import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tunifast.esprit.Entitie.User;
import tunifast.esprit.Service.UserCrud;
import tunifast.esprit.Utils.PDFutil;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class AdminAcceuilController implements Initializable {

    @FXML
    private TableColumn<User, Integer> idU;
    @FXML
    private TableColumn<User, String> usernameU;
    @FXML
    private TableColumn<User, String> nomU;
    @FXML
    private TableColumn<User, String> prenomU;
    @FXML
    private TableColumn<User, String> passwordU;
    @FXML
    private TableColumn<User, String> sexeU;
    @FXML
    private TableColumn<User, String> emailU;
    @FXML
    private TableColumn<User, Integer> phoneU;
    @FXML
    private TableColumn<User, String> roleU;
    @FXML
    private JFXTextField filterField;
    @FXML
    private TableView<User> usersTable;
    @FXML
    private Pagination pagination;
    private static final int ROWS_PER_PAGE = 8;
    private ObservableList<User> masterData = FXCollections.observableArrayList();
    private FilteredList<User> filteredData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupData();
           filteredData = new FilteredList<>(masterData, p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(
                    user -> newValue == null || newValue.isEmpty() || user.getNom().toLowerCase()
                    .contains(newValue.toLowerCase()) 
                            || String.valueOf(user.getIdUser()).toLowerCase().contains(newValue.toLowerCase())
                            || String.valueOf(user.getNumTel()).toLowerCase().contains(newValue.toLowerCase())
              //     || user.getUsername().toLowerCase().contains(newValue.toLowerCase()) 
 //  || user.getSexe().toLowerCase().contains(newValue.toLowerCase()) 
//                   || user.getMail().toLowerCase().contains(newValue.toLowerCase()) 
//                   || user.getRole().toLowerCase().contains(newValue.toLowerCase()) 
//                   || user.getPrenom().toLowerCase().contains(newValue.toLowerCase()) 
//                   || user.getPassword().toLowerCase().contains(newValue.toLowerCase()) 
            );
            changeTableView(pagination.getCurrentPageIndex(), ROWS_PER_PAGE);
        });

           idU.setCellValueFactory((new PropertyValueFactory<User, Integer>("idUser")));
           usernameU.setCellValueFactory((new PropertyValueFactory<User, String>("username")));
           nomU.setCellValueFactory((new PropertyValueFactory<User, String>("nom")));
           prenomU.setCellValueFactory((new PropertyValueFactory<User, String>("prenom")));
           passwordU.setCellValueFactory((new PropertyValueFactory<User, String>("password")));
           sexeU.setCellValueFactory((new PropertyValueFactory<User, String>("sexe")));
           emailU.setCellValueFactory((new PropertyValueFactory<User, String>("mail")));
           phoneU.setCellValueFactory((new PropertyValueFactory<User, Integer>("numTel")));
           roleU.setCellValueFactory((new PropertyValueFactory<User, String>("role")));
        
        int totalPage = (int) (Math.ceil(masterData.size() * 1.0 / ROWS_PER_PAGE));
        pagination.setPageCount(totalPage);
        pagination.setCurrentPageIndex(0);
        changeTableView(0, ROWS_PER_PAGE);
        pagination.currentPageIndexProperty().addListener(
                (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));

           
    }

    public void setupData() {

        masterData.clear();

        ArrayList<User> rs = new ArrayList<User>();
        UserCrud us = new UserCrud();

        rs = us.getAllUsers();

        for (int i = 0; i < rs.size(); i++) {
            User u = new User();
            u.setIdUser(rs.get(i).getIdUser());
            u.setNom(rs.get(i).getNom());
            u.setPrenom(rs.get(i).getPrenom());
            u.setPassword(rs.get(i).getPassword());
            u.setSexe(rs.get(i).getSexe());
            u.setMail(rs.get(i).getMail());
            u.setNumTel(rs.get(i).getNumTel());
            u.setRole(rs.get(i).getRole());
            u.setUsername(rs.get(i).getUsername());
            masterData.add(u);
        }

    }

    private void changeTableView(int index, int limit) {

        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, masterData.size());

        int minIndex = Math.min(toIndex, filteredData.size());
        SortedList<User> sortedData = new SortedList<>(
                FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
        sortedData.comparatorProperty().bind(usersTable.comparatorProperty());

        usersTable.setItems(sortedData);

    }

    @FXML
    private void exporter(ActionEvent event) throws com.lowagie.text.DocumentException {
        
        try {
            PDFutil pdf = new PDFutil();
            try {
                pdf.listUtilisateurs();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AdminAcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminAcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdminAcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
