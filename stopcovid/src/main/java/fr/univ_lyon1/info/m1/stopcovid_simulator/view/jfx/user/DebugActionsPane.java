package fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx.user;

import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

public class DebugActionsPane extends TitledPane {

    private Observable declareContact = new Observable();

    private final ComboBox<String> foreignKeysCombobox;

    /**
     * Creates a DebugActionsPane.
     */
    public DebugActionsPane() {
        TitledPane debugActionsPane = this;
        debugActionsPane.setText("Actions de debug");
        debugActionsPane.setCollapsible(false);

        VBox regularActionsVbox = new VBox();

        HBox covidToken = new HBox();
        covidToken.setAlignment(Pos.CENTER_LEFT);

        foreignKeysCombobox = new ComboBox<>();
        HBox.setHgrow(foreignKeysCombobox, Priority.ALWAYS);

        Label covidTokenLabel = new Label("Clé");
        covidTokenLabel.setMinWidth(Region.USE_PREF_SIZE);
        covidToken.getChildren().addAll(covidTokenLabel, foreignKeysCombobox);

        Button declareInfectedButton = new Button("Déclarer un contact");
        declareInfectedButton.setMaxWidth(Double.MAX_VALUE);
        declareInfectedButton.setOnAction((actionEvent) -> declareContact.emit());

        regularActionsVbox.getChildren().addAll(covidToken, declareInfectedButton);
        debugActionsPane.setContent(regularActionsVbox);
    }

    public String getKeyToMeet() {
        return foreignKeysCombobox.getSelectionModel().getSelectedItem();
    }

    /**
     * Assigns the list of foreign keys that can be met.
     * @param keys
     */
    public void setForeignKeys(final List<String> keys) {
        foreignKeysCombobox.getItems().clear();
        foreignKeysCombobox.getItems().addAll(keys);
    }

    public Observable getObservableDeclareContact() {
        return declareContact;
    }
}
