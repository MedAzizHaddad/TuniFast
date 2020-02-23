
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
    public void start(Stage primaryStage) {
        try {
            //pas/annonceList.fxml

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/../profile/test.fxml"));
            Parent parent = loader.load();

            Stage stage = null;
            stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("klh");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
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
