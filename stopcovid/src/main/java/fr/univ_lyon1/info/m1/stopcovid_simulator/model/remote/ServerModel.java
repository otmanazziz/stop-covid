package fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote;

import fr.univ_lyon1.info.m1.stopcovid_simulator.data.KeysManager;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.UserDatabase;

interface ServerModel {

    KeysManager getInfectedKeys();

    UserDatabase getUsers();

    // TokensDatabase getUserTokensDatabase();
}
