package fr.univ_lyon1.info.m1.stopcovid_simulator.controller;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.UserLocalModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.simulation.FakeTime;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.UserDebugView;

import java.util.List;

public class UserDebugController extends UserController {

    private UserDebugView debugView;

    /**
     * Create a user debug controller, linking a user debug view to its model.
     * @param view
     * @param model
     */
    public UserDebugController(final UserDebugView view, final UserLocalModel model) {
        super(view, model);
        debugView = view;

        debugView.getSimulatedContactObservable().subscribe(() -> onSimulatedContact());
    }



    private void onSimulatedContact() {
        List<String> keys = debugView.getSimulatedMetKeys();
        for (String key : keys) {
            getModel().getMetKeysManager().addKey(key, FakeTime.getInstance().getNow());
        }
    }
}
