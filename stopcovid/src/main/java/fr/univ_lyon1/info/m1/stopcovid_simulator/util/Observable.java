package fr.univ_lyon1.info.m1.stopcovid_simulator.util;

import java.util.HashSet;
import java.util.Set;

public class Observable {
    private Set<Observer> listeners = new HashSet<>();

    /**
     * Subscribe a listener to this emitter.
     * @param l Listener to subscribe
     */
    public void subscribe(final Observer l) {
        listeners.add(l);
    }

    /**
     * Unsubscribe a listener from this emitter.
     * @param l Listener to unsubscribe
     */
    public void unsubscribe(final Observer l) {
        listeners.remove(l);
    }

    /**
     * Unsubscribe all listeners from this emitter.
     */
    public void unsubscribeAll() {
        listeners.clear();
    }

    /**
     * Emit an event.
     */
    public void emit() {
        for (Observer l : listeners) {
            l.onEvent();
        }
    }
}
