package fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage;

public interface UserDatabase {

    /**
     * Add a user to the database.
     * @return the user's data accessor.
     */
    User createUser();

    /**
     * Get user data from the database.
     * @param userToken
     * @return
     */
    User getUser(String userToken);

    /**
     * Remove a user from the database.
     * @param userToken
     */
    void removeUser(String userToken);
}
