/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.profile;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.Rating;
import tunifast.esprit.Entitie.rate;
import tunifast.esprit.Service.rateCrud;

/**
 * FXML Controller class
 *
 * @author mohamedazizhaddad
 */
public class RateController implements Initializable {

    @FXML
    private Rating rate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("raatiing");
        rate.setRating(1);
        double nbrRate = rate.getRating();
         rateCrud rat = new rateCrud();
        rate r = new rate(nbrRate,26);
        rat.ajouterRate(r);
        System.out.println(r);
        
    }    
    
}
