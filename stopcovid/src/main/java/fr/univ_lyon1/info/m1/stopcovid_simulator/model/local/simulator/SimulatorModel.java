package fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.simulator;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.UserLocalModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.util.EventEmitter;
import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;

import java.util.HashMap;
import java.util.Map;

public class SimulatorModel {

    private Map<String, UserLocalModel> simulatedUsers = new HashMap<>();

    private EventEmitter<String> userAdded = new EventEmitter<>();
    private EventEmitter<String> userRemoved = new EventEmitter<>();

    public SimulatorModel() {

    }

    /**
     * Add a user to the simulator.
     * @param userModel
     */
    public void addUser(UserLocalModel userModel) {
        simulatedUsers.put(userModel.getUserToken(), userModel);
        userAdded.emit(userModel.getUserToken());
    }

    /**
     * Remove a user from the simulator.
     * @param userToken
     */
    public void removeUser(String userToken) {
        simulatedUsers.remove(userToken);
        userRemoved.emit(userToken);
    }


    /**
     * Get the observable {@link EventEmitter}, notifies when a user is added. Passes its token.
     * @return
     */
    public EventEmitter<String> getObservableUserAdded() {
        return userAdded;
    }

    /**
     * Get the observable {@link EventEmitter}, notifies when a user is removed. Passes its token.
     * @return
     */
    public EventEmitter<String> getObservableUserRemoved() {
        return userRemoved;
    }
}
