package fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.UserLocalModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.UserDebugView;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx.user.DataViewerPane;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx.user.DebugActionsPane;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx.user.RegularActionsPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;


public class JfxUserView implements UserDebugView {

    private final UserLocalModel user;

    private TitledPane gui;

    private RegularActionsPane regularActions;
    private DataViewerPane dataViewer;
    private DebugActionsPane debugActions;

    /**
     * Create a Java FX UserView.
     * @param user the user viewed.
     */
    public JfxUserView(final UserLocalModel user) {
        this.user = user;
        gui = new TitledPane();
        gui.setExpanded(false);
        updateTitle();

        VBox guiVbox = new VBox();

        regularActions = new RegularActionsPane();
        dataViewer = new DataViewerPane(user);
        debugActions = new DebugActionsPane();

        guiVbox.getChildren().addAll(regularActions,
                dataViewer,
                debugActions);
        gui.setContent(guiVbox);

        user.getPersonalKeysManager().getObservableKeysAdded()
                .subscribe(() -> onOwnKeysUpdated());
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
        return debugActions.getDeclaredContactObservable();
    }

    @Override
    public Observable getDeleteUserObservable() {
        return debugActions.getDeleteUserObservable();
    }

    @Override
    public List<String> getSimulatedMetKeys() {
        ArrayList<String> result = new ArrayList<String>();
        result.add(debugActions.getKeyToMeet());
        return result;
    }

    @Override
    public void updateVisibleForeignKeys(final List<String> keys) {
        debugActions.setForeignKeys(keys);
    }



    private void onOwnKeysUpdated() {
        updateTitle();
    }



    private void updateTitle() {
        gui.setText("User #"
                + user.getUserToken()
                + " - active key:"
                + user.getPersonalKeysManager().getNewestKey().getKey());
    }
}
