/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.gui;

import tunifast.esprit.Entitie.Actualite;
import tunifast.esprit.Service.ActualiteCrud;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import sun.rmi.transport.Transport;
//////
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import tunifast.esprit.Entitie.Actualite;
import tunifast.esprit.Service.ActualiteCrud;
import java.awt.event.MouseEvent;
import static java.lang.Math.E;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/////
/**
 * FXML Controller class
 *
 * @author HUNTER
 */
public class AddActController implements Initializable {

    @FXML
    private TextField tfidUser;
    @FXML
    private TextField tflieuActualite;
    @FXML
    private TextField tfdateActualite;
    @FXML
    private TextField tfheureActualite;
    @FXML
    private TextField tfActualite;
    @FXML
    private TextField tftypeActualite;
    @FXML
    private Button bAjouter;
    @FXML
    private TableView<Actualite> table;
    public ObservableList<Actualite> tables = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Actualite, Integer> col_id;
    @FXML
    private TableColumn<Actualite, String> col_lieuAct;
    @FXML
    private TableColumn<Actualite, String> col_dateAct;
    @FXML
    private TableColumn<Actualite, String> col_heureAct;
    @FXML
    private TableColumn<Actualite, String> col_Act;
    @FXML
    private TableColumn<Actualite, Integer> col_typeAct;
    @FXML
    private TextField recherche1;
    Actualite A = new Actualite();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        col_id.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        col_lieuAct.setCellValueFactory(new PropertyValueFactory<>("lieuActualite"));
        col_dateAct.setCellValueFactory(new PropertyValueFactory<>("dateActualite"));
        col_heureAct.setCellValueFactory(new PropertyValueFactory<>("heureActualite"));
        col_Act.setCellValueFactory(new PropertyValueFactory<>("Actualite"));
        col_typeAct.setCellValueFactory(new PropertyValueFactory<>("typeActualite"));

        try {
            ActualiteCrud act = new ActualiteCrud();
            Actualite A = new Actualite();

            tables = act.afficher(A);

        } catch (SQLException ex) {

        }
        table.setItems((ObservableList<Actualite>) tables);

        FilteredList<Actualite> filteredData = new FilteredList<>(tables, b -> true);
        recherche1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(A -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (A.getLieuActualite().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }// Filter matches first name.
                else {
                    return false;
                }
            });
        });
        SortedList<Actualite> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

    }

    public void refresh() {

        col_id.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        col_lieuAct.setCellValueFactory(new PropertyValueFactory<>("lieuActualite"));
        col_dateAct.setCellValueFactory(new PropertyValueFactory<>("dateActualite"));
        col_heureAct.setCellValueFactory(new PropertyValueFactory<>("heureActualite"));
        col_Act.setCellValueFactory(new PropertyValueFactory<>("Actualite"));
        col_typeAct.setCellValueFactory(new PropertyValueFactory<>("typeActualite"));

        try {
            ActualiteCrud act = new ActualiteCrud();
            Actualite A = new Actualite();
            tables = act.afficher(A);
        } catch (SQLException ex) {

        }
        table.setItems((ObservableList<Actualite>) tables);
    }

    @FXML
    private void SelectItemes(javafx.scene.input.MouseEvent event) {
        ObservableList<Actualite> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        if (oblist != null) {
            tflieuActualite.setText(oblist.get(0).getLieuActualite());
            int max = oblist.get(0).getTypeActualite();
            max = oblist.get(0).getIdActualite();

            tftypeActualite.setText("" + oblist.get(0).getTypeActualite());

            tfdateActualite.setText(oblist.get(0).getDateActualite());
            tfheureActualite.setText(oblist.get(0).getHeureActualite());
            tfActualite.setText(oblist.get(0).getActualite());

        }

    }

    @FXML
    private void ajouter(ActionEvent event) {

        String iduser = tfidUser.getText();
        String lieuact = tflieuActualite.getText();
        String dateact = tfdateActualite.getText();
        String heureact = tfheureActualite.getText();
        String act = tfActualite.getText();
        String typeact = tftypeActualite.getText();

        int iduserr, typeactt;
        iduserr = Integer.parseInt(iduser);
        typeactt = Integer.parseInt(typeact);

        ActualiteCrud ac = new ActualiteCrud();
        Actualite a = new Actualite(iduserr, lieuact, dateact, heureact, act, typeactt);
        ac.ajouterActualite(a);
        refresh();

    }

    @FXML
    private void modifier(ActionEvent event) {

        Actualite A = new Actualite();
        A.setLieuActualite(tflieuActualite.getText());
        A.setDateActualite(tfdateActualite.getText());
        A.setHeureActualite(tfheureActualite.getText());
        A.setActualite(tfActualite.getText());
        A.setTypeActualite(Integer.parseInt(tftypeActualite.getText()));

        ObservableList<Actualite> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        int max = oblist.get(0).getIdActualite();
        ActualiteCrud act = new ActualiteCrud();
        try {

            act.modifier(A, max);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        refresh();

    }

    @FXML
    private void supp(ActionEvent event) {
        
        ObservableList<Actualite> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        int max = oblist.get(0).getIdActualite();
     //   
        Actualite A = new Actualite();

        ActualiteCrud act = new ActualiteCrud();
        try {

            act.supprimer(max);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        refresh();
        
    }

    @FXML
    private void mail(ActionEvent event) {

        try {
            String host = "smtp.gmail.com";
            String user = "TunisianGottalentPidev@gmail.com";
            String pass = "Pidev123";

            String to = "mohamediheb.bchini@esprit.tn";
            String from = "tunisiangottalentesprit@gmail.com";
            String subject = "Une nouvelle actualité est ajoutée";
            String messageText = "Bienvenue  " + "Mr, dans ce mail vous trouvez tout nos actualités de cette semaine...Merci de visiter notre application.";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            // msg.setSentDate(new Date());
            //msg.setSentDate(new Date());
            msg.setText(messageText);

            javax.mail.Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
        } catch (Exception ex) {
            System.out.println(ex);

        }

    }

}
