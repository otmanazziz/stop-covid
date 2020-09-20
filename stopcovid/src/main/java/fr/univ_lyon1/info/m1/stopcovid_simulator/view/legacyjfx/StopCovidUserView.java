package fr.univ_lyon1.info.m1.stopcovid_simulator.view.legacyjfx;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.User;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StopCovidUserView {
    private final User user;

    private final VBox gui = new VBox();
    private final VBox contacts = new VBox();
    private final Label statusLabel;
    private final Button declareInfectedButton;


    Node getGui() {
        return gui;
    }

    StopCovidUserView(final User u) {
        this.user = u;
        final Label l = new Label(u.getName());
        gui.setStyle("-fx-padding: 10; -fx-border-width: 1;"
                + " -fx-border-radius: 5; -fx-border-color: #505050;");

        statusLabel = new Label("NO_DANGER");
        declareInfectedButton = new Button("Declare Infected");
        //declareInfectedButton.setOnAction(this.declare);
        gui.getChildren().addAll(l,
                new Label("Contacts:"),
                contacts,
                declareInfectedButton,
                statusLabel);
    }

    @Override
    public String toString() {
        return user.getName();
    }
}
