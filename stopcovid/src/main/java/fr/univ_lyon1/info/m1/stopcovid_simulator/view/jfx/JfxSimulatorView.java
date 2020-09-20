package fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx;

import fr.univ_lyon1.info.m1.stopcovid_simulator.controller.UserDebugController;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.simulator.SimulatorModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.UserLocalModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.ServerModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.util.EventEmitter;
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
    private EventEmitter<String> deleteUser = new EventEmitter<>();

    private VBox userViewsVbox;
    private Map<String, JfxUserView> userViews = new HashMap<>();

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
        simulatorModel.getObservableUserRemoved().subscribe(m -> removeUserView(m));

        // Name of window
        stage.setTitle("StopCovid Simulator");

        final VBox root = this;

        ScrollPane usersScrollpane = new ScrollPane();
        usersScrollpane.setFitToWidth(true);

        userViewsVbox = new VBox();
        usersScrollpane.setContent(userViewsVbox);
        usersScrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


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

    @Override
    public EventEmitter<String> getDeleteUserObservable() {
        return deleteUser;
    }


    private void onUserAddedInSimulatorModel(final String token) {
        createUserViewController(token);
        updateUsersForeignKeys();
    }





    private void updateUsersForeignKeys() {
        List<UserLocalModel> usersList = simulatorModel.getUsers();
        for (UserLocalModel uModel : usersList) {
            UserDebugView uView = userViews.get(uModel.getUserToken());
            uView.updateVisibleForeignKeys(simulatorModel.getUsers()
                    .stream()
                    .filter(model -> model != uModel)
                    .map(userLocalModel -> userLocalModel.
                            getOwnKeysManager().
                            getNewestKey().
                            getKey())
                    .collect(Collectors.toList()));
        }
    }

    private void createUserViewController(final String token) {
        // TODO create a user controller there
        UserLocalModel user = simulatorModel.getUser(token);
        JfxUserView userView = new JfxUserView(user);
        userView.getDeleteUserObservable().subscribe(() -> deleteUser.emit(token));
        var controller = new UserDebugController(userView, user, serverModel.getApi().getUserApi());

        userViewsVbox.getChildren().add(userView.getRoot());
        userViews.put(token, userView);
    }

    private void removeUserView(final String token) {
        JfxUserView uView = userViews.get(token);
        userViewsVbox.getChildren().remove(uView.getRoot());
    }
}
