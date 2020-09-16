package fr.univ_lyon1.info.m1.stopcovid_simulator.controller;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.CovidServer;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.SimulatorView;

public class ServerController {
    private SimulatorView view;
    private CovidServer server;

    /**
     * Server controller constructor.
     * @param view the server view.
     * @param server the server.
     */
    public ServerController(final SimulatorView view, final CovidServer server) {
        this.view = view;
        this.server = server;
    }
}
