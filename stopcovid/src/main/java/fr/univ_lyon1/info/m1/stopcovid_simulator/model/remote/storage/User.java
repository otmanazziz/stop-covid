package fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage;

public abstract class User {



    /**
     * Gets this user's unique token.
     * @return
     */
    public abstract String getToken();



    /**
     * Checks if a given key is owned by this user.
     * Currently does NOT work as intended, the model needs cryptographic support
     * (keys should not be random but generated from a private key (==user token ?).
     * @param key
     * @return
     */
    public boolean ownsKey(final String key) {
        return true;
    }


}
