package fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx.user;

import fr.univ_lyon1.info.m1.stopcovid_simulator.data.DatedKey;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.UserLocalModel;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

public class DataViewerPane extends TitledPane {

    private static final double LISTS_HEIGHT = 100.0;


    private final UserLocalModel model;

    private Label riskyLabel;
    private ListView ownKeysViewer;
    private ListView metKeysViewer;


    /**
     * Create a user data viewer pane. Display the model internal data.
     * @param model
     */
    public DataViewerPane(final UserLocalModel model) {
        this.setText("Données du modèle");
        this.setExpanded(false);

        this.model = model;
        model.getOwnKeysManager().getObservableKeysUpdated()
                .subscribe(() -> onOwnKeysUpdated());
        model.getMetKeysManager().getObservableKeysUpdated()
                .subscribe(() -> onMetKeysUpdated());
        model.getRiskyChangedObservable().subscribe(() -> onRiskyChanged());

        VBox vbox = new VBox();

        riskyLabel = new Label();

        HBox keysHbox = new HBox();

        VBox ownKeysVbox = new VBox();
        HBox.setHgrow(ownKeysVbox, Priority.ALWAYS);
        Label ownKeysTitle = new Label("Clés personnelles");
        ownKeysViewer = new ListView();
        ownKeysViewer.setMaxHeight(LISTS_HEIGHT);
        ownKeysVbox.getChildren().addAll(ownKeysTitle, ownKeysViewer);

        VBox metKeysVbox = new VBox();
        HBox.setHgrow(metKeysVbox, Priority.ALWAYS);
        Label metKeysTitle = new Label("Clés rencontrées");
        metKeysViewer = new ListView();
        metKeysViewer.setMaxHeight(LISTS_HEIGHT);
        metKeysVbox.getChildren().addAll(metKeysTitle, metKeysViewer);

        keysHbox.getChildren().addAll(ownKeysVbox, metKeysVbox);

        vbox.getChildren().addAll(riskyLabel, keysHbox);

        this.setContent(vbox);

        // force update
        onOwnKeysUpdated();
        onMetKeysUpdated();
        onRiskyChanged();
    }

    private void onOwnKeysUpdated() {
        updateListViewWithDateKeys(ownKeysViewer, model.getOwnKeysManager().getDatedKeys());
    }

    private void onMetKeysUpdated() {
        updateListViewWithDateKeys(metKeysViewer, model.getMetKeysManager().getDatedKeys());
    }


    private void updateListViewWithDateKeys(final ListView target,
                                            final List<DatedKey> datedKeyList) {
        target.getItems().clear();
        final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(Locale.UK)
                .withZone(ZoneId.systemDefault());

        for (DatedKey datedKey : datedKeyList) {
            String formattedInstant = formatter.format(datedKey.getDate());
            target.getItems().add(formattedInstant + ") " + datedKey.getKey());
        }
    }

    private void onRiskyChanged() {
        boolean isRisky = model.getIsRisky();
        String newText = "Susceptible d'être contaminé: ";
        String newStyle = "";
        if (isRisky) {
            newText += "OUI";
            newStyle += "-fx-text-fill: red;";
        } else {
            newText += "non";
        }

        riskyLabel.setText(newText);
        riskyLabel.setStyle(newStyle);
    }
}
