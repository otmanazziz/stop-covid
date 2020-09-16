package fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.Model;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.CovidServer;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.SimulatorView;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.UserView;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JfxMainView extends VBox implements SimulatorView {

    private static final int DEBUG_USER_COUNT = 10;
    private CovidServer m;

    /**
     * Build a {@link SimulatorView} that uses the Java FX library.
     * @param stage javafx stage
     * @param width view's width (px)
     * @param height view's height (px)
     */
    public JfxMainView(final Stage stage, final int width, final int height) {
        // Name of window
        stage.setTitle("StopCovid Simulator");

        final VBox root = this;

        ScrollPane usersScrollpane = new ScrollPane();
        usersScrollpane.setFitToWidth(true);
        VBox usersVBox = new VBox();
        for (int i = 0; i < DEBUG_USER_COUNT; ++i) {
            JfxUserView u = new JfxUserView();
            usersVBox.getChildren().add(u.getRoot());
        }
        usersScrollpane.setContent(usersVBox);

        root.getChildren().addAll(usersScrollpane);

        final Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public UserView getUserView(final String userKey) {
        return null;
    }

    @Override
    public CovidServer getModel() {
        return this.m;
    }

}
