package fr.univ_lyon1.info.m1.stopcovid_simulator.controller;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.CovidServer;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.SimulatedUserApi;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.SimulatorView;

public class ServerController {
    private SimulatorView view;
    private CovidServer server;

    public ServerController(SimulatorView view, CovidServer server) {
        this.view = view;
        this.server = server;
    }
}
