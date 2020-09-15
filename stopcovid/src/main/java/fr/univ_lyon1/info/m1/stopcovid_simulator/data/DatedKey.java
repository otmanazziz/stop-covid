package fr.univ_lyon1.info.m1.stopcovid_simulator.data;

import java.time.Instant;

public class DatedKey {
    private final String key;
    private final Instant date;

    /**
     * Creates a DatedKey, which is a pair made of a String and an Instant.
     * @param key
     * @param date
     */
    public DatedKey(final String key, final Instant date) {
        this.key = key;
        this.date = date;
    }

    /**
     * Get the key.
     * @return
     */
    public String getKey() {
        return key;
    }

    /**
     * Get the key's date.
     * @return
     */
    public Instant getDate() {
        return date;
    }
}
