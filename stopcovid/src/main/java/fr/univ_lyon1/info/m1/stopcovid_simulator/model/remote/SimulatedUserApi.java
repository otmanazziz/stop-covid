package fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote;

import fr.univ_lyon1.info.m1.stopcovid_simulator.data.DatedKey;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.User;
import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;

import java.util.List;
import java.util.stream.Collectors;

public class SimulatedUserApi implements UserApi {

    private ServerModel model;

    public SimulatedUserApi() {

    }

    @Override
    public void setModel(final ServerModel model) {
        this.model = model;
    }

    @Override
    public void declareInfected(final String userToken,
                                final String covidToken,
                                final List<DatedKey> keys) {
        // TODO : test if the covid token really exist / is valid (and remove it).
        User u = model.getUsers().getUser(userToken);
        List<DatedKey> ownedKeys = keys.stream()
                .filter(datedKey -> u.ownsKey(datedKey.getKey()))
                .collect(Collectors.toList());
        model.getInfectedKeys().addKeys(ownedKeys);
    }

    @Override
    public List<String> getInfectedKeys() {
        return model.getInfectedKeys().getKeys();
    }

    @Override
    public String register() {
        return model.getUsers().createUser().getToken();
    }

    @Override
    public Observable getInfectedKeysUpdatedObservable() {
        return model.getInfectedKeys().getObservableKeysAdded();
    }
}
