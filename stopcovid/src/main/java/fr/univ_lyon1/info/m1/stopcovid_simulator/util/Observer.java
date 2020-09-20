package fr.univ_lyon1.info.m1.stopcovid_simulator.util;

public interface Observer {
    /**
     * Callback method that may be called after subscribing to a {@link Observable}.
     */
    void onEvent();
}
