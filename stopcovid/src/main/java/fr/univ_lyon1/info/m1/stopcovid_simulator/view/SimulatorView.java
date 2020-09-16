package fr.univ_lyon1.info.m1.stopcovid_simulator.view;

import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;

public interface SimulatorView {
    /**
     * Get a user view.
     * @param userToken Master key of the desired user.
     * @return The desired {@link UserView} if it exists, null else.
     */
    UserView getUserView(String userToken);

    /**
     * Get the {@link Observable} called when the view request adding a user.
     * @return
     */
    Observable getObservableAddUser();
}
