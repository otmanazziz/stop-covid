package fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx.user;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.ContactAmountRiskyFlagging;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.RiskyFlaggingStrategy;
import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class RegularActionsPane extends TitledPane {

    private enum RiskyStrategiesEnum {
        COUNT_1("1 contact", new ContactAmountRiskyFlagging(1)),
        COUNT_2("2 contacts", new ContactAmountRiskyFlagging(2)),
        COUNT_3("3 contacts", new ContactAmountRiskyFlagging(3));

        private final String prettyName;
        private final RiskyFlaggingStrategy strategy;

        RiskyStrategiesEnum(final String prettyName, final RiskyFlaggingStrategy strategy) {
            this.prettyName = prettyName;
            this.strategy = strategy;
        }
    }

    private Observable declaredInfected = new Observable();
    private Observable riskyStrategyChanged = new Observable();

    private TextField covidTokenField;
    private ComboBox<String> strategyComboBox;

    /**
     * Creates a RegularActionPane.
     */
    public RegularActionsPane() {
        TitledPane regularActionsPane = this;
        regularActionsPane.setText("Actions normales");
        regularActionsPane.setExpanded(false);

        HBox hbox = new HBox();

        // Declare infected Vbox
        VBox declareInfectedVbox = new VBox();

        HBox covidToken = new HBox();
        covidToken.setAlignment(Pos.CENTER_LEFT);
        covidTokenField = new TextField();
        HBox.setHgrow(covidTokenField, Priority.ALWAYS);
        Label covidTokenLabel = new Label("Covid Token");
        covidTokenLabel.setMinWidth(Region.USE_PREF_SIZE);
        covidToken.getChildren().addAll(covidTokenLabel, covidTokenField);

        Button declareInfectedButton = new Button("Declare infected");
        declareInfectedButton.setMaxWidth(Double.MAX_VALUE);
        declareInfectedButton.setOnAction(event -> declaredInfected.emit());

        declareInfectedVbox.getChildren().addAll(covidToken, declareInfectedButton);

        // Risky flagging strategy vbox
        VBox riskyFlaggingVbox = new VBox();
        Label riskyFlaggingTitle = new Label("Stratégie de détection de risque");
        strategyComboBox = new ComboBox<>();
        strategyComboBox.valueProperty().addListener(observable -> riskyStrategyChanged.emit());
        for (RiskyStrategiesEnum rs : RiskyStrategiesEnum.values()) {
            strategyComboBox.getItems().add(rs.prettyName);
        }
        strategyComboBox.getSelectionModel().selectFirst();

        riskyFlaggingVbox.getChildren().addAll(riskyFlaggingTitle, strategyComboBox);

        //
        hbox.getChildren().addAll(declareInfectedVbox, riskyFlaggingVbox);
        regularActionsPane.setContent(hbox);
    }


    public Observable getDeclaredInfected() {
        return declaredInfected;
    }

    public Observable getRiskyStrategyChanged() {
        return riskyStrategyChanged;
    }

    public String getCovidToken() {
        return covidTokenField.getText();
    }

    /**
     * Retrieve the currently selected {@link RiskyFlaggingStrategy}.
     * @return
     */
    public RiskyFlaggingStrategy getSelectedRiskyStrategy() {
        RiskyFlaggingStrategy rs;
        int selectedIndex = strategyComboBox.getSelectionModel().getSelectedIndex();
        RiskyStrategiesEnum selectedEnum = RiskyStrategiesEnum.values()[selectedIndex];
        return selectedEnum.strategy;
    }
}
