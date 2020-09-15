package fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user;

import java.util.List;

public class ContactAmountRiskyFlagging implements RiskyFlaggingStrategy {

    private final UserLocalModel user;
    private final long requiredContactCount;

    ContactAmountRiskyFlagging(final UserLocalModel user, final long requiredContactCount) {
        this.user = user;
        this.requiredContactCount = requiredContactCount;
    }

    @Override
    public boolean isRisky() {
        List<String> met = user.getMetKeysManager().getKeys();
        List<String> infected = user.getMetKeysManager().getKeys();
        long infectedContacts = met.stream().
                filter(s -> {
                    return infected.contains(s);
                })
                .count();
        return infectedContacts >= requiredContactCount;
    }
}
