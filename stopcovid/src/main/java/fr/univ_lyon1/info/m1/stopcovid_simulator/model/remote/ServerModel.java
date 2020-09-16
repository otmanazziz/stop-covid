package fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote;

import fr.univ_lyon1.info.m1.stopcovid_simulator.data.KeysManager;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.UserDatabase;

public interface ServerModel extends ServerApi {

    KeysManager getInfectedKeys();

    UserDatabase getUsers();

    // TokensDatabase getUserTokensDatabase();
}
