package fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.simulator.SimulatorModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.UserLocalModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.ServerModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.SimulatorView;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.UserDebugView;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.UserView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JfxSimulatorView extends VBox implements SimulatorView {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int DEBUG_USER_COUNT = 0;


    private Observable addUserObservable = new Observable();

    private VBox userViewsVbox;
    private Map<String, UserDebugView> userViews = new HashMap<>();

    private ServerModel serverModel;
    private SimulatorModel simulatorModel;


    /**
     * Build a {@link SimulatorView} that uses the Java FX library.
     * @param stage javafx stage
     * @param serverModel
     * @param simulatorModel
     */
    public JfxSimulatorView(final Stage stage,
                            final ServerModel serverModel,
                            final SimulatorModel simulatorModel) {
        this.serverModel = serverModel;
        this.simulatorModel = simulatorModel;

        simulatorModel.getObservableUserAdded().subscribe(m -> onUserAddedInSimulatorModel(m));

        // Name of window
        stage.setTitle("StopCovid Simulator");

        final VBox root = this;

        ScrollPane usersScrollpane = new ScrollPane();
        usersScrollpane.setFitToWidth(true);
        userViewsVbox = new VBox();
        /*for (int i = 0; i < DEBUG_USER_COUNT; ++i) {
            JfxUserView u = new JfxUserView();
            usersVBox.getChildren().add(u.getRoot());
        }*/
        usersScrollpane.setContent(userViewsVbox);

        Button testAddButton = new Button("ADD USER");
        testAddButton.setOnMouseClicked(event -> addUserObservable.emit());

        root.getChildren().addAll(testAddButton, usersScrollpane);

        final Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public UserView getUserView(final String userKey) {
        return null;
    }

    @Override
    public Observable getObservableAddUser() {
        return addUserObservable;
    }


    private void onUserAddedInSimulatorModel(final String token) {
        UserLocalModel user = simulatorModel.getUser(token);
        JfxUserView userView = new JfxUserView(user);
        userViewsVbox.getChildren().add(userView.getRoot());
        userViews.put(token, userView);

        List<UserLocalModel> usersList = simulatorModel.getUsers();
        for (UserLocalModel uModel : usersList) {
            UserDebugView uView = userViews.get(uModel.getUserToken());
            uView.updateVisibleForeignKeys(simulatorModel.getUsers()
                    .stream()
                    .map(userLocalModel -> userLocalModel.
                            getPersonalKeysManager().
                            getNewestKey().
                            getKey())
                    .collect(Collectors.toList()));
        }


    }
}
