/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.derby.shared.common.error.ExceptionUtil;
import tunifast.esprit.Utils.DataBase;

/**
 *
 * @author mohamedazizhaddad
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) { //pas/annonceList.fxml
        System.out.println("hi");
        try {
          //  Parent root = FXMLLoader.load(getClass().getResource("startPage.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("startPage.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();
            // to instanciate the db since the start of the app nad thread it to prevent the app from slow launchign
           
            new Thread(() -> {
                DataBase.getInstance();
            }).start();
            //      root.requestFocus();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // System.out.println("hii");
        launch(args);
//        System.out.println("hi");
//        annonceCrud an = new annonceCrud();
//        an.checkDataAvaliPas();
    }

}
