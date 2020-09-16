package fr.univ_lyon1.info.m1.stopcovid_simulator.controller;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.UserLocalModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.UserView;

public class UserController {
    private UserView view;
    private UserLocalModel model;

    /**
     * User controller constructor.
     * @param view the user view.
     * @param model the user local model.
     */
    public UserController(final UserView view, final UserLocalModel model) {
        this.view = view;
        this.model = model;
    }
}
