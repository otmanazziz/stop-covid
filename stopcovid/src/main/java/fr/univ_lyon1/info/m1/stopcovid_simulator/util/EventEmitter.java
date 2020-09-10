package fr.univ_lyon1.info.m1.stopcovid_simulator.util;

import java.util.HashSet;
import java.util.Set;

public class EventEmitter<T> {
    private Set<EventListener<T>> listeners = new HashSet<>();

    /**
     * Subscribe a listener to this emitter.
     * @param l Listener to subscribe
     */
    public void subscribe(final EventListener<T> l) {
        listeners.add(l);
    }

    /**
     * Unsubscribe a listener from this emitter.
     * @param l Listener to unsubscribe
     */
    public void unsubscribe(final EventListener<T> l) {
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
     * @param e Event data
     */
    public void emit(final T e) {
        for (EventListener<T> l : listeners) {
            l.onEvent(e);
        }
    }
}
