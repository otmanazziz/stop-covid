package fr.univ_lyon1.info.m1.stopcovid_simulator.util;

public interface EventListener<T> {
    /**
     * Callback method that may be called after suscribing to a {@link EventEmitter}.
     * @param m event's message
     */
    void onEvent(T m);
}
