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
        model.getPersonalKeysManager().getObservableKeysUpdated()
                .subscribe(() -> onOwnKeysUpdated());
        model.getMetKeysManager().getObservableKeysUpdated()
                .subscribe(() -> onMetKeysUpdated());

        HBox hbox = new HBox();

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

        hbox.getChildren().addAll(ownKeysVbox, metKeysVbox);

        this.setContent(hbox);

        onOwnKeysUpdated(); // force update
        onMetKeysUpdated();
    }

    private void onOwnKeysUpdated() {
        updateListViewWithDateKeys(ownKeysViewer, model.getPersonalKeysManager().getDatedKeys());
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
}
