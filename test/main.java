
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunifast.esprit.Utils.DataBase;
import tunifast.esprit.gui.AcceuilController;
import tunifast.esprit.gui.Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mohamedazizhaddad
 */
public class main {

    /**
     * @param primaryStage
     */
    public void start(Stage primaryStage) { //pas/annonceList.fxml
   
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
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
        System.out.println("jo");
        launch(args);
//        System.out.println("hi");
//        annonceCrud an = new annonceCrud();
//        an.checkDataAvaliPas();
    }

    
}
