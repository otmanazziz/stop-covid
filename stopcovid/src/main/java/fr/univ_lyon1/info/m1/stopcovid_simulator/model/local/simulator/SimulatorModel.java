package fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.simulator;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.UserLocalModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.util.EventEmitter;

public interface SimulatorModel {
    /**
     * Add a user to the simulator.
     * @param userModel
     */
    void addUser(UserLocalModel userModel);

    /**
     * Remove a user from the simulator.
     * @param userToken
     */
    void removeUser(String userToken);

    UserLocalModel getUser(String userToken);

    /**
     * Get the observable {@link EventEmitter}, notifies when a user is added. Passes its token.
     * @return
     */
    EventEmitter<String> getObservableUserAdded();

    /**
     * Get the observable {@link EventEmitter}, notifies when a user is removed. Passes its token.
     * @return
     */
    EventEmitter<String> getObservableUserRemoved();
}
