/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static org.apache.poi.hssf.usermodel.HeaderFooter.tab;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class AcceuilMode2Controller implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private BorderPane rootAnchorPane;
    @FXML
    private JFXTabPane tabPaneAc;
    @FXML
    private Tab tabPass;
    @FXML
    private ImageView img;
    @FXML
    private VBox rootPane1;
    @FXML
    private JFXToggleButton btnRole;
    @FXML
    private JFXButton btnreserved;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }

    @FXML
    private void checkRole(ActionEvent event) {
    }

    @FXML
    private void annoncer(ActionEvent event) {
    }

    @FXML
    private void ResCons(ActionEvent event) {
        System.out.println("hii");
     tunifast.esprit.Utils.TuniFastUtil.loadWindowMode2(getClass().getResource("pas/ResConsPas.fxml"), "reserver" , tabPaneAc);

    }

    @FXML
    private void anListM2(ActionEvent event) {

 tunifast.esprit.Utils.TuniFastUtil.loadWindow(getClass().getResource("pas/annonceList.fxml"), "reserver" , tabPaneAc);
    }
//        Object controller = null;
//        Tab newtab = new Tab();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("pas/annonceList.fxml"));
//        loader.setController(controller);
////        rootPane.getParent().set
//        try {
//            Parent parent = loader.load();
//            newtab = new Tab("A Dynamic Tab");
//            newtab.setClosable(true);
//            newtab.setContent(parent);
//   if (tabPaneAc.getTabs().size() == 5){
//                tabPaneAc.getTabs().remove(3);
//            }
//            tabPaneAc.getTabs().add(newtab);
//            tabPaneAc.getSelectionModel().select(newtab);
//           // tabPaneAc.getTabs().remove(0);
//            System.out.println(tabPaneAc.getTabs().size());
//        } catch (IOException e) {
//            e.printStackTrace();
//            Stage st = new Stage();
//
//        }
//    }
}
    
