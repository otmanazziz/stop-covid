package fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user;

import fr.univ_lyon1.info.m1.stopcovid_simulator.data.DatedKeysCollection;
import fr.univ_lyon1.info.m1.stopcovid_simulator.data.KeysManager;
import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;

import java.time.Period;



public class CovidLocalUser implements UserLocalModel {

    private static final Period KEYS_EXPIRATION_TIME = Period.ofDays(14);


    private final String userToken;

    private final KeysManager personalKeysManager;
    private final KeysManager metKeysManager;
    private final KeysManager infectedKeysManager;

    private RiskyFlaggingStrategy riskyFlaggingStrategy;

    private Observable riskyChanged = new Observable();


    /**
     * Creates a Covid local user identified by a unique user token.
     * @param userToken
     */
    public CovidLocalUser(final String userToken) {
        this.userToken = userToken;

        personalKeysManager = new DatedKeysCollection();
        metKeysManager = new DatedKeysCollection();
        infectedKeysManager = new DatedKeysCollection();
    }


    @Override
    public Observable getRiskyChangedObservable() {
        return riskyChanged;
    }

    @Override
    public KeysManager getPersonalKeysManager() {
        return personalKeysManager;
    }

    @Override
    public KeysManager getMetKeysManager() {
        return metKeysManager;
    }

    @Override
    public KeysManager getInfectedKeysManager() {
        return infectedKeysManager;
    }


    @Override
    public String getUserToken() {
        return userToken;
    }

    @Override
    public void setRiskyFlaggingStrategy(final RiskyFlaggingStrategy strategy) {
        riskyFlaggingStrategy = strategy;
    }

    @Override
    public void save() {

    }
}
