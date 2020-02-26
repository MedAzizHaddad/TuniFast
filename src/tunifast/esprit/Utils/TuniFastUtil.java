/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Utils;

/**
 *
 * @author mohamedazizhaddad
 */
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunifast.esprit.Entitie.Profile;
import tunifast.esprit.Entitie.User;
import tunifast.esprit.Entitie.UserSession;
import tunifast.esprit.Service.UserCrud;
import tunifast.esprit.gui.AcceuilController;

public class TuniFastUtil {

           Connection cnx;
    Statement st;

    public TuniFastUtil() {
        cnx = DataBase.getInstance().getCnx();
    }
    private Tab myDynamicTab;
    public static final String ICON_IMAGE_LOC = "/resources/icon.png";
    //  public static final String MAIL_CONTENT_LOC = "/resources/mail_content.html";
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(ICON_IMAGE_LOC));
    }

    public static void loadWindow(URL loc, String title, JFXTabPane t) {
       if (t == null){
           loadWindowMode1(loc, title, t);
       } else {
           loadWindowMode2(loc, title, t);
       }
    }

    public static Object loadWindowMode1(URL loc, String title, JFXTabPane t) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            Stage stage = null;
                stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            //setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return controller;
    }

    public static Object loadWindowMode2(URL loc, String title, JFXTabPane t) {
        Object controller = null;
        System.out.println(t.getTabs().size());
        //       tunifast.esprit.Utils.TuniFastUtil.loadWindow(getClass().getResource("pas/annonceRead.fxml"), "reserver" , null);

        Tab newtab = new Tab();

//        rootPane.getParent().set
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();

            newtab = new Tab(title);
            newtab.setClosable(true);
            newtab.setContent(parent);
            if (t.getTabs().size() == 5) {
                t.getTabs().remove(3);
            }
            t.getTabs().add(newtab);
            t.getSelectionModel().select(newtab);

            System.out.println(t.getTabs().size());
        } catch (IOException e) {
            e.printStackTrace();
            Stage st = new Stage();

        }

        return controller;
    }

    public static void parSession(int par) {
        UserSession us = new UserSession().getInstance();

        int x = us.getIdUser();
        String y = us.getRole();
        int z = par;

        UserSession us1 = new UserSession().getInstance(x, y, z);

    }
    
       public static void parSession2(int par) {
        UserSession us = new UserSession().getInstance();

        int x = us.getIdUser();
        String y = us.getRole();
        int z = us.getParam();
              int a = par ;  

        UserSession us1 = new UserSession().getInstance(x, y, z,a);

    }
    
    public static boolean testUnicité(String tab, String col, String val) {
        UserCrud prof = new UserCrud();
        boolean result = false;
        if (prof.fetchColExist(tab, col, val)) {
            result = false; // n'existe pas 

        } else {
            result = true;
        }
        return result;
    }
    
   
}
