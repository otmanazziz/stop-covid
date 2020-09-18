package fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.UserLocalModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.UserDebugView;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx.user.DebugActionsPane;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx.user.RegularActionsPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class JfxUserView implements UserDebugView {

    private final UserLocalModel user;

    private TitledPane gui;

    private RegularActionsPane regularActions;
    private DebugActionsPane debugActions;

    /**
     * Create a Java FX UserView.
     * @param user the user viewed.
     */
    public JfxUserView(final UserLocalModel user) {
        this.user = user;

        gui = new TitledPane();
        gui.setText("User #" + UUID.randomUUID().toString());
        gui.setExpanded(false);

        VBox guiVbox = new VBox();

        regularActions = new RegularActionsPane();
        debugActions = new DebugActionsPane();

        guiVbox.getChildren().addAll(regularActions, debugActions);
        gui.setContent(guiVbox);
    }

    public Region getRoot() {
        return gui;
    }



    @Override
    public Observable getDeclaredInfectedObservable() {
        return regularActions.getDeclaredInfected();
    }

    @Override
    public String getCovidToken() {
        return regularActions.getCovidToken();
    }

    @Override
    public Observable getSimulatedContactObservable() {
        return null;
    }

    @Override
    public Collection<String> getSimulatedMetKeys() {
        ArrayList<String> result = new ArrayList<String>();
        result.add(debugActions.getKeyToMeet());
        return result;
    }
}
