package fr.univ_lyon1.info.m1.stopcovid_simulator.simulation;

import java.time.Instant;

public final class FakeTime {
    private static final FakeTime INSTANCE = new FakeTime();

    private Instant instant = Instant.now();

    private FakeTime() {

    }

    /**
     * Get the singleton of {@link FakeTime}.
     * @return
     */
    public static FakeTime getInstance() {
        return INSTANCE;
    }

    /**
     * Get the current fake instant/time.
     * @return
     */
    public Instant getNow() {
        return instant;
    }

    /**
     * Set the new current fake/instant.
     * @param instant
     */
    public void setNow(final Instant instant) {
        this.instant = instant;
    }
}
