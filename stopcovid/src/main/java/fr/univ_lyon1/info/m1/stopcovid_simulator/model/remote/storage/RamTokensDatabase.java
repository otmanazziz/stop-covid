package fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage;

import java.util.HashSet;
import java.util.Set;

public class RamTokensDatabase implements TokensDatabase {

    private Set<String> tokens = new HashSet<>();

    @Override
    public void addToken(final String token) {
        tokens.add(token);
    }

    @Override
    public void removeToken(final String token) {
        tokens.remove(token);
    }

    @Override
    public boolean hasToken(final String token) {
        return tokens.contains(token);
    }
}
