/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tunifast.esprit.Entitie.Annonce;
import tunifast.esprit.Entitie.Reservation;
import tunifast.esprit.Service.AnnonceCrud;
import tunifast.esprit.Utils.DataBase;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class AcceuilController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private StackPane rootPane;
    @FXML
    private BorderPane rootAnchorPane;
    @FXML
    private Tab tabPass;

    @FXML
    public JFXTabPane tabPaneAc;
    @FXML
    private ImageView img;
    @FXML
    private Tab tabPass1;
    @FXML
    private ImageView img1;
    Connection cnx = DataBase.getInstance().getCnx();
    Statement st;
    @FXML
    private Text txt1;
    @FXML
    private Text txt2;
    @FXML
    private Text txt3;
    @FXML
    private Text txt4;
    @FXML
    private Text txt5;
    @FXML
    private Text txt6;
    @FXML
    private Text txt11;
    @FXML
    private Text txt22;
    @FXML
    private Text txt33;
    @FXML
    private Text txt44;
    @FXML
    private Text txt55;
    @FXML
    private Text txt66;

    public void initialize(URL url, ResourceBundle rb) {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ArrayList<Annonce> res = new ArrayList<Annonce>();
                AnnonceCrud an = new AnnonceCrud();
                res = an.getLastAnnoncePas();

//                txt1.setText(res.get(0).getUser().getUsername());
//                txt2.setText("looking for ride");
//                txt3.setText(res.get(0).getLieuDepart());
//                txt4.setText(res.get(0).getLieuArrivee());
//                txt5.setText(res.get(0).getDateAnnonce());
//                txt6.setText(res.get(0).getHeureAnnonce());

                ArrayList<Annonce> res1 = new ArrayList<Annonce>();
                AnnonceCrud an1 = new AnnonceCrud();
                res1 = an.getLastAnnonceChauf();

                txt11.setText(res1.get(0).getUser().getUsername());
                txt22.setText("offering a ride");
                txt33.setText(res1.get(0).getLieuDepart());
                txt44.setText(res1.get(0).getLieuArrivee());
                txt55.setText(res1.get(0).getDateAnnonce());
                txt66.setText(res1.get(0).getHeureAnnonce());

            }
        }, 0, 4000);

        initDrawer();
        img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("Tile pressed ");
                img.setVisible(false);
                event.consume();
            }
        });
        tabPass.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tabPass.isSelected()) {
                    img.setVisible(false);
                    System.out.println("tabB.setOnSelectionChanged() :: counter is now: ");

                }
            }
        });

    }

    private void initDrawer() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("toolbar.fxml"));
            VBox toolbar = loader.load();
            drawer.setSidePane(toolbar);
            //  ToolbarController controller = loader.getController();
            //    controller.setBookReturnCallback(this);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            drawer.toggle();
        });
        drawer.setOnDrawerOpening((event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            drawer.toFront();
        });
        drawer.setOnDrawerClosed((event) -> {
            drawer.toBack();
            task.setRate(task.getRate() * -1);
            task.play();
        });
    }
}
