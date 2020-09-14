package fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx.user;

import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class RegularActionsPane extends TitledPane {

    private Observable declaredInfected = new Observable();

    private TextField covidTokenField;

    /**
     * Creates a RegularActionPane.
     */
    public RegularActionsPane() {
        TitledPane regularActionsPane = this;
        regularActionsPane.setText("Actions normales");
        regularActionsPane.setCollapsible(false);

        VBox regularActionsVbox = new VBox();

        HBox covidToken = new HBox();
        covidToken.setAlignment(Pos.CENTER_LEFT);
        covidTokenField = new TextField();
        HBox.setHgrow(covidTokenField, Priority.ALWAYS);
        Label covidTokenLabel = new Label("Covid Token");
        covidTokenLabel.setMinWidth(Region.USE_PREF_SIZE);
        covidToken.getChildren().addAll(covidTokenLabel, covidTokenField);

        Button declareInfectedButton = new Button("Declare infected");
        declareInfectedButton.setMaxWidth(Double.MAX_VALUE);
        declareInfectedButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                declaredInfected.emit();
            }
        });

        regularActionsVbox.getChildren().addAll(covidToken, declareInfectedButton);
        regularActionsPane.setContent(regularActionsVbox);
    }


    public Observable getDeclaredInfected() {
        return declaredInfected;
    }

    public String getCovidToken() {
        return covidTokenField.getText();
    }

}
