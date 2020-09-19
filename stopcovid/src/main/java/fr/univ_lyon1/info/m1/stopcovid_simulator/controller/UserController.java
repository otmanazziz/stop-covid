package fr.univ_lyon1.info.m1.stopcovid_simulator.controller;

import fr.univ_lyon1.info.m1.stopcovid_simulator.data.DatedKey;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.UserLocalModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.UserApi;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.UserView;

import java.util.List;

public class UserController {
    private final UserView view;
    private final UserLocalModel model;
    private final UserApi userApi;

    /**
     * Create a User Controller, linking views actions to its model.
     * @param view
     * @param model
     * @param userApi
     */
    public UserController(final UserView view,
                          final UserLocalModel model,
                          final UserApi userApi) {
        this.view = view;
        this.model = model;
        this.userApi = userApi;

        view.getDeclaredInfectedObservable().subscribe((() -> onDeclaredInfected()));
    }


    protected UserView getUserView() {
        return view;
    }

    protected UserLocalModel getModel() {
        return model;
    }

    protected UserApi getUserApi() {
        return userApi;
    }



    private void onDeclaredInfected() {
        String userToken = model.getUserToken();
        String covidToken = view.getCovidToken();
        List<DatedKey> ownedDatedKeys = model.getPersonalKeysManager().getDatedKeys();
        userApi.declareInfected(userToken, covidToken, ownedDatedKeys);
    }
}
