/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

/**
 *
 * @author mohamedazizhaddad*/
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ActivityListViewer extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ListView<Activity> activityListView = new ListView<>(
                FXCollections.observableArrayList(
                        new Activity("Fetch super suit from drycleaner", false),
                        new Activity("Interview governor with Jimmy", false),
                        new Activity("Rescue strandsfed ndfgen", true),
                        new Activity("Dinner date with Lois", false)
                )
        );

        activityListView.setCellFactory(param -> new ListCell<Activity>() {
            static final String ACTIVE_CLASS = "active";
            @Override
            protected void updateItem(Activity item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                    getStyleClass().remove(ACTIVE_CLASS);
                } else {
                    if (item.isActive()) {
                        setMinWidth(0);
                    setMaxWidth(100);
                    setPrefWidth(70);
                        setWrapText(true);
                        setText(item.getName() + " - active");
                        
                         
                    } else {
                        setText(item.getName());
                    }

                    if (item.isActive() && !getStyleClass().contains(ACTIVE_CLASS)) {
                        getStyleClass().add(ACTIVE_CLASS);
                    } else {
                        getStyleClass().remove(ACTIVE_CLASS);
                    }
                }
            }
        });

        activityListView.setPrefHeight(150);

        Scene scene = new Scene(activityListView);
        scene.getStylesheets().add(
                this.getClass().getResource("activity.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Activity {
    private final StringProperty nameProperty;
    private final BooleanProperty activeProperty;

    public Activity(String name, boolean active) {
        this.nameProperty = new SimpleStringProperty(name);
        this.activeProperty = new SimpleBooleanProperty(active);
    }

    public String getName() {
        return nameProperty.get();
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }

    public boolean isActive() {
        return activeProperty.getValue() != null
                ? activeProperty.getValue()
                : false;
    }

    public BooleanProperty activeProperty() {
        return activeProperty;
    }
}