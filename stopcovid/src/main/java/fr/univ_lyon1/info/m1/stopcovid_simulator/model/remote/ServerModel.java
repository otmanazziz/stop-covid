package fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote;

import fr.univ_lyon1.info.m1.stopcovid_simulator.data.KeysManager;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.UserDatabase;

public interface ServerModel {

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

    /**
     * Get this server's API.
     * @return
     */
    ServerApi getApi();

    // TokensDatabase getUserTokensDatabase();
}
