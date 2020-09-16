package fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage;

public interface TokensDatabase {

    /**
     * Add a token to the database.
     * @param token
     */
    void addToken(String token);

    /**
     * Remove a token from the database.
     * @param token
     */
    void removeToken(String token);

    /**
     * Test if a given token is valid.
     * @param token
     * @return
     */
    boolean hasToken(String token);
}
