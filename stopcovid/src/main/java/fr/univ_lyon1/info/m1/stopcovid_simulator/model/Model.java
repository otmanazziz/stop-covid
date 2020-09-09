package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

import java.util.Collection;
import java.util.HashMap;

public class Model {
    private final HashMap<String, User> users;

    /**
     * Constructor of the main class.
     */
    public Model() {
        this.users = new HashMap<String, User>();
    }

    /**
     * Get Users.
     * @return a list of users.
     */
    public Collection<User> getUsers() {
        return this.users.values();
    }

    /**
     * Get User.
     * @param id the id of the user.
     * @return a user identified by the id.
     */
    public User getUser(final String id) {
        return this.users.get(id);
    }

    /**
     * Create a new user.
     * @param name the name of the new User.
     */
    public void addUser(final String name) {
        User u = new User(name);
        this.users.put(u.getUid(), u);
    }

    /**
     * Delete a user.
     * @param id the id of the user.
     */
    public void removeUser(final String id) {
        this.users.remove(id);
    }
}
