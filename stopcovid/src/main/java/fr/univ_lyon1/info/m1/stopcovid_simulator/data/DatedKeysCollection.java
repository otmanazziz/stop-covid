package fr.univ_lyon1.info.m1.stopcovid_simulator.data;

import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DatedKeysCollection implements KeysManager {

    private List<DatedKey> keys = new ArrayList<>();

    private Observable keysUpdated = new Observable();
    private Observable keysAdded = new Observable();


    @Override
    public void addKey(final String key) {
        addKey(key, Instant.now());
    }

    @Override
    public void addKey(final String key, final Instant date) {
        keys.add(new DatedKey(key, date));

        keysUpdated.emit();
        keysAdded.emit();
    }

    @Override
    public void addKeys(final List<DatedKey> k) {
        keys.addAll(k);

        keysUpdated.emit();
        keysAdded.emit();
    }

    @Override
    public List<String> getKeys() {
        return keys.stream().map(DatedKey::getKey).collect(Collectors.toList());
    }

    @Override
    public List<DatedKey> getDatedKeys() {
        return Collections.unmodifiableList(keys);
    }

    @Override
    public DatedKey getNewestKey() {
        keys.sort((dk1, dk2) -> dk2.getDate().compareTo(dk1.getDate()));
        return keys.get(0);
    }


    @Override
    public Observable getObservableKeysUpdated() {
        return keysUpdated;
    }

    @Override
    public Observable getObservableKeysAdded() {
        return keysAdded;
    }
}
