package fr.univ_lyon1.info.m1.stopcovid_simulator.data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DatedKeysCollection implements KeysManager {

    private List<DatedKey> keys = new ArrayList<>();

    @Override
    public void addKey(final String key) {
        addKey(key, Instant.now());
    }

    @Override
    public void addKey(final String key, final Instant date) {
        keys.add(new DatedKey(key, date));
    }

    @Override
    public List<String> getKeys() {
        return keys.stream().map(DatedKey::getKey).collect(Collectors.toList());
    }

    @Override
    public List<DatedKey> getDatedKeys() {
        return Collections.unmodifiableList(keys);
    }
}
