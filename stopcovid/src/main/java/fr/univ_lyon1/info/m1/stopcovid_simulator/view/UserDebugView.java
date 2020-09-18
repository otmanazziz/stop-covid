package fr.univ_lyon1.info.m1.stopcovid_simulator.view;

import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;

import java.util.Collection;

public interface UserDebugView extends UserView {
    /**
     * Get the {@link Observable} called when the user wants to simulate a contact.
     * @return the {@link Observable}.
     */
    Observable getSimulatedContactObservable();

    /**
     * Retrieve the keys the user wants to simulate a contact with.
     * @return keys the users had a "contact" with.
     */
    Collection<String> getSimulatedMetKeys();
}
