/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.chauf;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Entitie.Reservation;
import tunifast.esprit.Entitie.User;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.AnnonceCrud;
import tunifast.esprit.Service.ReservationCrud;
import tunifast.esprit.Utils.DataBase;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class AnnonceReadChaufController implements Initializable {

    Connection cnx;
    Statement st;

    public AnnonceReadChaufController() {
        cnx = DataBase.getInstance().getCnx();
    }
    @FXML
    private StackPane root;
    @FXML
    private JFXListView<String> listDetAnn;
    @FXML
    private JFXTextField txtDatePostAn;
    @FXML
    private JFXTextField txtDepAn;
    @FXML
    private JFXTextField txtArAn;
    @FXML
    private JFXTextField txtDateAn;
    @FXML
    private JFXTextField txtHrAn;
    @FXML
    private JFXTextField txtPlDisp;
    @FXML
    private JFXTextField txtPlRes;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXTextField txtTel;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtDate;
    @FXML
    private JFXTextField txtPlace;
    @FXML
    private JFXTextField txtEtat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> data = FXCollections.observableArrayList();

        txtDatePostAn.setEditable(false);
        txtDepAn.setEditable(false);
        txtArAn.setEditable(false);
        txtDateAn.setEditable(false);
        txtHrAn.setEditable(false);
        txtPlDisp.setEditable(false);
        txtPlRes.setEditable(false);
        txtNom.setEditable(false);
        txtPrenom.setEditable(false);
        txtTel.setEditable(false);
        txtEmail.setEditable(false);
        txtDate.setEditable(false);
        txtPlace.setEditable(false);
        txtEtat.setEditable(false);

        ArrayList<Annonce> res = new ArrayList<Annonce>();
        ArrayList<Reservation> res1 = new ArrayList<Reservation>();
        ArrayList<Reservation> res3 = new ArrayList<Reservation>();

        UserSession u = UserSession.getInstance();
        System.out.println(u.getParam());
        AnnonceCrud an = new AnnonceCrud();
        res = an.ReadAnnonce(u.getParam());
        txtDatePostAn.setText("Date Annonce : " + res.get(0).getDateAnnPost());
        txtDepAn.setText("Depart : " + res.get(0).getLieuDepart());
        txtArAn.setText("Arrivee : " + res.get(0).getLieuArrivee());
        txtDateAn.setText("Date : " + res.get(0).getDateAnnonce());
        txtHrAn.setText("Heure :" + res.get(0).getHeureAnnonce());
        txtPlDisp.setText("PLaces disponible : " + Integer.toString(res.get(0).getNbrPlaceDispo()));
        txtPlRes.setText("Places reservé : " + Integer.toString(res.get(0).getNbPlaceReser()));

        ReservationCrud reserv = new ReservationCrud();
        res1 = reserv.listResChaufAn(u.getParam());

        for (int i = 0; i < res1.size(); i++) {
            data.add(Integer.toString(res1.get(i).getIdReservation()));

        }

        listDetAnn.getItems().setAll(data);

        listDetAnn.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                int i = Integer.parseInt(listDetAnn.getSelectionModel().getSelectedItem());

                //--------------------------------------------------------------------
                ArrayList<Reservation> result = new ArrayList<Reservation>();

                try {
                    String requete3 = "SELECT * FROM reservation "
                            + "INNER JOIN USER ON user.idUser = reservation.idUser"
                            + " WHERE idReservation  = " + i + " ";

                    PreparedStatement pst2 = cnx.prepareStatement(requete3);
                    ResultSet rs = pst2.executeQuery();
                    User u = new User();
                    Reservation res = new Reservation();
                    while (rs.next()) {
                        u.setNom(rs.getString("nom"));
                        u.setPrenom(rs.getString("prenom"));
                        u.setNumTel(rs.getInt("numTel"));
                        u.setMail(rs.getString("mail"));
                        res.setUser(u);
                        res.setDateReservation(rs.getString("dateReservation"));
                        res.setNbPlace(rs.getInt("nbPlace"));
                        res.setEtatReservation(rs.getString("etatReservation"));
                        result.add(res);

                    }

                } catch (SQLException ex) {

                }
                //--------*----------------------------------
                txtNom.setText(result.get(0).getUser().getNom());
                txtPrenom.setText(result.get(0).getUser().getPrenom());
                txtTel.setText(Integer.toString(result.get(0).getUser().getNumTel()));
                txtEmail.setText(result.get(0).getUser().getMail());
                txtDate.setText(result.get(0).getDateReservation());
                txtPlace.setText(Integer.toString(result.get(0).getNbPlace()));
                txtEtat.setText(result.get(0).getEtatReservation());
         
            }
        });
    }

}
