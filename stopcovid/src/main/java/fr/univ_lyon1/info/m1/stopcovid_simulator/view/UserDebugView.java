package fr.univ_lyon1.info.m1.stopcovid_simulator.view;

import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;

import java.util.List;

public interface UserDebugView extends UserView {
    /**
     * Get the {@link Observable} called when the user wants to simulate a contact.
     * @return the {@link Observable}.
     */
    Observable getSimulatedContactObservable();

    /**
     * Get the {@link Observable} called when a user wants to delete its data.
     * @return
     */
    Observable getDeleteUserObservable();



    /**
     * Retrieve the keys the user wants to simulate a contact with.
     * @return keys the users had a "contact" with.
     */
    List<String> getSimulatedMetKeys();

    /**
     * Update the keys that can be met.
     * @param keys
     */
    void updateVisibleForeignKeys(List<String> keys);
}
