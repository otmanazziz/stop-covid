package fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote;

import fr.univ_lyon1.info.m1.stopcovid_simulator.data.KeysManager;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.UserDatabase;

public interface ServerModel extends ServerApi {

    /**
     * Get the object managing infected keys.
     * @return
     */
    KeysManager getInfectedKeys();

    /**
     * Get the user database's accessor.
     * @return
     */
    UserDatabase getUsers();

    // TokensDatabase getUserTokensDatabase();
}
