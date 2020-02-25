/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui.admin.promotions;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tunifast.esprit.Service.PromotionService;
import tunifast.esprit.Entitie.Promotion;
import tunifast.esprit.Entitie.offre;
import tunifast.esprit.Service.offreService;
import java.util.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PromoController implements Initializable {

    @FXML
    private DatePicker dateD;

    @FXML
    private DatePicker dateF;

    @FXML
    private JFXTextField reduction;

    @FXML
    private JFXTextField search;

    @FXML
    private TableView<Promotion> tabview2;

    @FXML
    private TableColumn<Promotion, String> c1;

    @FXML
    private TableColumn<Promotion, String> c2;

    @FXML
    private TableColumn<Promotion, String> c3;

    @FXML
    private TableColumn<Promotion, String> c4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            PromotionService ps = new PromotionService();
            List<Promotion> list = ps.readAll();
            System.out.println(list);
            ObservableList<Promotion> cls = FXCollections.observableArrayList();
            for (Promotion aux : list) {
                cls.add(new Promotion(aux.getIdP(), aux.getDateD(), aux.getDateF(), aux.getReduction()));
            }

            c1.setCellValueFactory(new PropertyValueFactory<>("IdP"));
            c2.setCellValueFactory(new PropertyValueFactory<>("DateD"));
            c3.setCellValueFactory(new PropertyValueFactory<>("DateF"));
            c4.setCellValueFactory(new PropertyValueFactory<>("Reduction"));

            tabview2.setItems(cls);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    public void Ajouter1() {
        try {

            Date date_debut = java.sql.Date.valueOf(dateD.getValue());
            Date date_fin = java.sql.Date.valueOf(dateF.getValue());
            float reduc = Float.valueOf(reduction.getText());

            PromotionService ps = new PromotionService();

            Promotion p = new Promotion(date_debut, date_fin, reduc);
            ps.ajouter(p);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ajout succés");
            alert.setHeaderText(null);
            alert.setContentText("promotion ajouteé ");
            alert.showAndWait();
            Afficher();

        } catch (SQLException ex) {

            System.out.println(ex);
        }
    }

    @FXML
    public void supprimer() {
        PromotionService tmp = new PromotionService();
        if (!tmp.equals(tabview2.getSelectionModel().getSelectedItem())) {
            try {
                PromotionService ps = new PromotionService();
                Promotion tmp2 = tabview2.getSelectionModel().getSelectedItem();
                ps.delete(tmp2.getIdP());
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Supprimer succés");
                alert.setHeaderText(null);
                alert.setContentText("Offre est suprimeé ");
                alert.showAndWait();
                Afficher();

                reduction.setText("");
            } catch (SQLException ex) {

                System.out.println(ex);
            }
        }
    }

    public void Afficher() {
        try {
            PromotionService ps = new PromotionService();
            List<Promotion> list = ps.readAll();
            System.out.println(list);
            ObservableList<Promotion> cls = FXCollections.observableArrayList();
            for (Promotion aux : list) {
                cls.add(new Promotion(aux.getIdP(), aux.getDateD(), aux.getDateF(), aux.getReduction()));
            }

            tabview2.setItems(cls);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    public void Search() {
        String S = search.getText();
        try {
            PromotionService ps = new PromotionService();
            List<Promotion> list = ps.Search(S);
            System.out.println(list);
            ObservableList<Promotion> cls = FXCollections.observableArrayList();
            for (Promotion aux : list) {
                cls.add(new Promotion(aux.getIdP(), aux.getDateD(), aux.getDateF(), aux.getReduction()));
            }

            tabview2.setItems(cls);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    public void Modifer() {
        PromotionService tmp = new PromotionService();
        if (!tmp.equals(tabview2.getSelectionModel().getSelectedItem())) {
            try {

                Date date_debut = java.sql.Date.valueOf(dateD.getValue());
                Date date_fin = java.sql.Date.valueOf(dateF.getValue());
                float reduc = Float.valueOf(reduction.getText());

                PromotionService ps = new PromotionService();

                Promotion tmp2 = tabview2.getSelectionModel().getSelectedItem();
                Promotion p = new Promotion(tmp2.getIdP(), date_debut, date_fin, reduc);
                ps.update(p, tmp2.getIdP());
                Afficher();

                reduction.setText("");

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    @FXML
    public void GetData() {

        try {
            Promotion p = tabview2.getSelectionModel().getSelectedItem();

            dateD.setValue(convert(p.getDateD()));
            dateF.setValue(convert(p.getDateF()));

            reduction.setText(String.valueOf(p.getReduction()));

        } catch (Exception ex) {

            System.out.println(ex);
        }
    }

    @FXML
    public void sendnotification() {
        try {
            String jsonResponse;

            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Basic OTU3YmU5ZWUtM2IwYS00NDdjLTliOGQtMjFkYTQxMGI3ODVmm");
            con.setRequestMethod("POST");

            String strJsonBody = "{"
                    + "\"app_id\": \"486cc4e1-bf0d-4850-bc82-e1edcb15a0ab\","
                    + "\"included_segments\": [\"All\"],"
                    + "\"data\": {\"foo\": \"bar\"},"
                    + "\"contents\": {\"en\": \"Nouvelle promotion est venue ! Profiter\"}"
                    + "}";

            System.out.println("strJsonBody:\n" + strJsonBody);

            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

            if (httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            } else {
                Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            System.out.println("jsonResponse:\n" + jsonResponse);

        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

    public LocalDate convert(Date D) {
        Instant instant = Instant.ofEpochMilli(D.getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

}
