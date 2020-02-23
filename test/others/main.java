/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author mohamedazizhaddad
 */
public class main {
    
    
      
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 640, 400);
        primaryStage.setScene(scene);
        WebView embeddedWV = new WebView();
        embeddedWV.getEngine().load(
       "https://www.youtube.com/embed/oyA8odjCzZ4"
            );
            embeddedWV.setPrefSize(640, 400);
            root.getChildren().add(embeddedWV);

            primaryStage.setScene(scene);
            primaryStage.show();
    }
    
    
    
    
    
    
}
