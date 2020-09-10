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
    NO_DANGER("Non infectée"), RISKY("Personne à risque"), INFECTED("Personne infectée");
    private String name;

    /**
     * Constructor of Enum CovidStatus.
     * @param name the full name of the status.
     */
    CovidStatus(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
