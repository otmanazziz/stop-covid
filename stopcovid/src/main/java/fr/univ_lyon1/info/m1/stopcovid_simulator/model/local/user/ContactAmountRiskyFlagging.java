package fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user;

import java.util.List;

public class ContactAmountRiskyFlagging implements RiskyFlaggingStrategy {

    private final long requiredContactCount;

    /**
     * Create a RiskyFlagging strategy that flags the contact count is over a certain threshold.
     * @param requiredContactCount
     */
    public ContactAmountRiskyFlagging(final long requiredContactCount) {
        this.requiredContactCount = requiredContactCount;
    }

    @Override
    public boolean isRisky(final UserLocalModel user) {
        List<String> met = user.getMetKeysManager().getKeys();
        List<String> infected = user.getInfectedKeysManager().getKeys();
        long infectedContacts = met.stream()
                .distinct()
                .filter(infected::contains)
                .count();
        return infectedContacts >= requiredContactCount;
    }
}
