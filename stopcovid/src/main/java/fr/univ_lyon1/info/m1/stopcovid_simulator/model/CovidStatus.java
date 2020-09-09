package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

public enum CovidStatus {
    /**
     * Represents a user's covid risk status.
     *
     * > NO_DANGER: The user is not infected and never met someone who's infected or risky.
     *
     * > RISKY: The user met an infected person, there's a possibility he's infected.
     *
     * > INFECTED: The user is infected.
     */
    NO_DANGER, RISKY, INFECTED;
}
