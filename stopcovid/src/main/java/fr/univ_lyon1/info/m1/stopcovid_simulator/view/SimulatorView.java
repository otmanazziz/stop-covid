package fr.univ_lyon1.info.m1.stopcovid_simulator.view;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.CovidServer;

public interface SimulatorView {
    /**
     * Get a user view.
     * @param userToken Master key of the desired user.
     * @return The desired {@link UserView} if it exists, null else.
     */
    UserView getUserView(String userToken);

    /**
     * Get a model.
     * @return The currend model.
     */
    CovidServer getModel();
}
