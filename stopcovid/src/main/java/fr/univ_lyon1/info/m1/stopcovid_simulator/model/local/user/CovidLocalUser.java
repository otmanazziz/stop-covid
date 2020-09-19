package fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user;

import fr.univ_lyon1.info.m1.stopcovid_simulator.data.DatedKeysCollection;
import fr.univ_lyon1.info.m1.stopcovid_simulator.data.KeysManager;
import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;

import java.time.Period;
import java.util.UUID;


public class CovidLocalUser implements UserLocalModel {

    private static final Period KEYS_EXPIRATION_TIME = Period.ofDays(14);


    private final String userToken;
    private boolean isRisky;

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
        personalKeysManager.addKey(UUID.randomUUID().toString());

        metKeysManager = new DatedKeysCollection();
        metKeysManager.getObservableKeysUpdated().subscribe(() -> verifyIsRisky());

        infectedKeysManager = new DatedKeysCollection();
        infectedKeysManager.getObservableKeysUpdated().subscribe(() -> verifyIsRisky());

        riskyFlaggingStrategy = new ContactAmountRiskyFlagging(1);
        verifyIsRisky();
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
    public boolean getIsRisky() {
        return isRisky;
    }

    @Override
    public void setRiskyFlaggingStrategy(final RiskyFlaggingStrategy strategy) {
        riskyFlaggingStrategy = strategy;
        verifyIsRisky();
    }

    @Override
    public void save() {

    }



    protected void setIsRisky(final boolean newValue) {
        if (newValue != isRisky) {
            isRisky = newValue;
            getRiskyChangedObservable().emit();
        }
    }


    private void verifyIsRisky() {
        setIsRisky(riskyFlaggingStrategy.isRisky(this));
    }
}
