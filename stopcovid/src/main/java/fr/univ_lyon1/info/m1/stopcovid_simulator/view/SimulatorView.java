package fr.univ_lyon1.info.m1.stopcovid_simulator.view;

public interface SimulatorView {
    /**
     * Get a user view.
     * @param userToken Master key of the desired user.
     * @return The desired {@link UserView} if it exists, null else.
     */
    UserView getUserView(String userToken);
}
