package fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user;

public interface RiskyFlaggingStrategy {

    /**
     * Evaluates if the situation is risky for the user.
     * @return
     */
    boolean isRisky();
}
