package fr.univ_lyon1.info.m1.stopcovid_simulator.data;

import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;

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
     * Add all keys to the manager.
     * @param keys
     */
    void addKeys(List<DatedKey> keys);

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

    /**
     * Get the most recent key stored.
     * @return
     */
    DatedKey getNewestKey();



    /**
     * Get the {@link Observable} called when the key collection changed.
     * @return
     */
    Observable getObservableKeysUpdated();

    /**
     * Get the {@link Observable} called when one or more key are added.
     * @return
     */
    Observable getObservableKeysAdded();

}
