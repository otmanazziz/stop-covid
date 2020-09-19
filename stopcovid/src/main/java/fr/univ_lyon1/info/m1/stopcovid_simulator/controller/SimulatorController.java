package fr.univ_lyon1.info.m1.stopcovid_simulator.controller;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.simulator.SimulatorModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.CovidLocalUser;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.ServerApi;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.SimulatorView;

public class SimulatorController {
    private final SimulatorModel model;
    private final SimulatorView view;
    private final ServerApi server;


    /**
     * Create a controller for the simulator.
     * @param model the simulator's model
     * @param view the simulator's view
     * @param server the server's API
     */
    public SimulatorController(final SimulatorModel model,
                               final SimulatorView view,
                               final ServerApi server) {
        this.model = model;
        this.view = view;
        this.server = server;

        view.getObservableAddUser().subscribe(() -> onRequestAddUser());
        view.getDeleteUserObservable().subscribe(m -> onRequestDeleteUser(m));
    }



    private void onRequestAddUser() {
        String newUserToken = server.getUserApi().register();
        CovidLocalUser user = new CovidLocalUser(newUserToken);
        model.addUser(user);
    }

    private void onRequestDeleteUser(final String userToken) {
        model.removeUser(userToken);
    }
}
