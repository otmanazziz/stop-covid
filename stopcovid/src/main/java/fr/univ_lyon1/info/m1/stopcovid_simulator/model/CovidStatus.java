package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

public enum CovidStatus {
    /**
     * Enum for Covid Status related with the user himself.
     *
     * > NO_DANGER: The user is not infected and never meet someone who's infected or risky.
     *
     * > RISKY: The user meet an infected person, but no sure if he's really infected.
     *
     * > INFECTED: The user is infected.
     */
    NO_DANGER, RISKY, INFECTED;
}
