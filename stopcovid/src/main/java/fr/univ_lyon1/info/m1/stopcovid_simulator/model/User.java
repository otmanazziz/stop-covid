package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

import java.util.HashMap;
import java.util.UUID;

public class User {
    private HashMap<String, Integer> contacts;
    private String name;
    private String uid;
    private CovidStatus status;

    /**
     * Create a new user.
     * @param name the name of the new user.
     */
    public User(final String name) {
        this.contacts = new HashMap<String, Integer>();
        this.name = name;
        this.uid = UUID.randomUUID().toString();
        this.status = CovidStatus.NO_DANGER;
    }

    /**
     * Get the name of the user.
     * @param name the name of the user.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Set the status of the user: NO_DANGER, RISKY or INFECTED.
     * @param status the new status of the user.
     */
    public void setStatus(final CovidStatus status) {
        this.status = status;
    }

    /**
     * Get the status of the user.
     * @return the status of the user.
     */
    public CovidStatus getStatus() {
        return this.status;
    }

    /**
     * Get the name of the user.
     * @return the name of the user.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get a list of Users this one met.
     * @return a list of users.
     */
    public HashMap<String, Integer> getContacts() {
        return this.contacts;
    }

    /**
     * Get the id of the user.
     * @return the id of the user.
     */
    public String getUid() {
        return this.uid;
    }

    /**
     * Add a new contact, or increase the contact count with a user.
     * @param u the user met.
     */
    public void addContact(final User u) {
        if (this.contacts.get(u.getUid()) == null) {
            this.contacts.put(u.getUid(), 0);
        } else {
            this.contacts.put(u.getUid(), this.contacts.get(u.getUid()) + 1);
        }
    }
}
