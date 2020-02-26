/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui;

import main.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import tunifast.esprit.Utils.DataBase;

/**
 *
 * @author mohamedazizhaddad
 */public class StatistiqueController implements Initializable {

    @FXML
    private PieChart piechart;
    Connection con;
    Statement ste;
    ObservableList< PieChart.Data> piechartdata;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        piechart.setData(piechartdata);
    }

    public void loadData() {

        try {

            String query = "SELECT * FROM reclamation";

            piechartdata = FXCollections.observableArrayList();

            con = DataBase.getInstance().getCnx();

            ResultSet rs = con.createStatement().executeQuery(query);

            HashMap<String, Double> map = new HashMap<>();

            try {
                while (rs.next()) {

                    boolean test = map.containsKey(rs.getString("objet"));
                    if (test == false) {
                        map.put(rs.getString("objet"), 1.0);
                    } else {
                        map.put(rs.getString("objet"), map.get(rs.getString("objet")) + 1.0);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(map);

            Iterator mapIterator = map.entrySet().iterator();

            while (mapIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry) mapIterator.next();

                Double i = (Double) mapElement.getValue();

                double resultat = (i * 100) / map.size();
                System.out.println(resultat);
                piechartdata.add(new PieChart.Data(mapElement.getKey().toString(), resultat));
            }

        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
