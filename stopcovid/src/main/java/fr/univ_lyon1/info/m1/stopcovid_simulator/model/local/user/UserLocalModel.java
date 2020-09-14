package fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user;

import fr.univ_lyon1.info.m1.stopcovid_simulator.data.KeysManager;
import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;

public interface UserLocalModel {

    /**
     * Get the observable fired when this user has a risk of being infected.
     * @return
     */
    Observable getRiskyChangedObservable();


    /**
     * Get this user's unique token.
     * @return
     */
    String getUserToken();

    /**
     * Defines the new risky flagging strategy.
     * @param strategy
     */
    void setRiskyFlaggingStrategy(RiskyFlaggingStrategy strategy);

    /**
     * Get the personal keys (own unique keys) manager.
     * @return
     */
    KeysManager getPersonalKeysManager();

    /**
     * Get the manager of met keys (encountered foreign keys).
     * @return
     */
    KeysManager getMetKeysManager();

    /**
     * Get the manager of infected keys (keys belonging to covid infected individuals).
     * @return
     */
    KeysManager getInfectedKeysManager();

    /**
     * Save the model.
     */
    void save();
}
