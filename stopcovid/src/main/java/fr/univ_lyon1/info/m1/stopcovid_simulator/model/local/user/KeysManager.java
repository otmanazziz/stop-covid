package fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user;

import java.time.Instant;
import java.util.List;

public interface KeysManager {
    /**
     * Add the given key to the manager.
     * @param key key to store
     */
    void addKey(String key);

    /**
     * Add the given key to the manager, associated with a given date.
     * @param key
     * @param date
     */
    void addKey(String key, Instant date);


    /**
     * Get the collections of keys.
     * @return
     */
    List<String> getKeys();

    /**
     * Get all keys, dated.
     * @return
     */
    List<DatedKey> getDatedKeys();
}
