package fr.univ_lyon1.info.m1.stopcovid_simulator.controller;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.UserLocalModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.UserView;

public class UserController {
    private final UserView view;
    private final UserLocalModel model;

    /**
     * Create a User Controller, linking views actions to its model.
     * @param view
     * @param model
     */
    public UserController(final UserView view, final UserLocalModel model) {
        this.view = view;
        this.model = model;
    }


    protected UserView getUserView() {
        return view;
    }

    protected UserLocalModel getModel() {
        return model;
    }
}
