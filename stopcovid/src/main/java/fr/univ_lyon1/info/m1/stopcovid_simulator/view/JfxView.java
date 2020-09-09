package fr.univ_lyon1.info.m1.stopcovid_simulator.view;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class JfxView extends HBox {
    private StopCovidServerView server;
    private VBox usersBox;

    /** View for the whole application.
     * @param stage The JavaFX stage where everything will be displayed.
     * @param width width in px
     * @param height height in px
     */
    public JfxView(final Stage stage, final int width,
                   final int height) {
        server = new StopCovidServerView();

        // Name of window
        stage.setTitle("StopCovid Simulator");

        final HBox root = this;

        usersBox = new VBox();
        final ObservableList<StopCovidUserView> usersList = FXCollections.observableArrayList();

        usersBox.getChildren().add(new Label("Users"));


        root.getChildren().add(usersBox);

        final VBox meetBox = new VBox();
        final Label l = new Label("Proximity simulator");

        final ComboBox<StopCovidUserView> userA = new ComboBox<>();
        final ComboBox<StopCovidUserView> userB = new ComboBox<>();
        userA.setItems(usersList);
        userB.setItems(usersList);
        final Button meetBtn = new Button("Meet!");
        /*meetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final StopCovidUserView a = userA.getValue();
                final StopCovidUserView b = userB.getValue();
                if (a == null || b == null) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Please select two users that will meet");
                    alert.showAndWait();
                    return;
                }
                a.meet(b);
            }
        });*/


        meetBox.getChildren().addAll(l, new HBox(userA, userB), meetBtn,
            new Separator(), server.getGui());

        root.getChildren().addAll(new Separator(), meetBox);

        final Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    StopCovidServerView getServer() {
        return server;
    }


    private void regenerateUserViewsList(final List<User> users) {
        for (User u : users) {
            final StopCovidUserView userView = new StopCovidUserView(u);
            usersBox.getChildren().add(userView.getGui());
        }
    }
}
