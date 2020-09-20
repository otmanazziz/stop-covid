package fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.simulator;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.UserLocalModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.util.EventEmitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CovidSimulator implements SimulatorModel {

    private Map<String, UserLocalModel> simulatedUsers = new HashMap<>();

    private EventEmitter<String> userAdded = new EventEmitter<>();
    private EventEmitter<String> userRemoved = new EventEmitter<>();

    /**
     * Create a Covid Simulator model.
     */
    public CovidSimulator() {

    }


    @Override
    public void addUser(final UserLocalModel userModel) {
        simulatedUsers.put(userModel.getUserToken(), userModel);
        userAdded.emit(userModel.getUserToken());
    }

    @Override
    public void removeUser(final String userToken) {
        simulatedUsers.remove(userToken);
        userRemoved.emit(userToken);
    }

    @Override
    public UserLocalModel getUser(final String userToken) {
        return simulatedUsers.get(userToken);
    }

    @Override
    public List<UserLocalModel> getUsers() {
        return new ArrayList<UserLocalModel>(this.simulatedUsers.values());
    }


    @Override
    public EventEmitter<String> getObservableUserAdded() {
        return userAdded;
    }

    @Override
    public EventEmitter<String> getObservableUserRemoved() {
        return userRemoved;
    }
}
