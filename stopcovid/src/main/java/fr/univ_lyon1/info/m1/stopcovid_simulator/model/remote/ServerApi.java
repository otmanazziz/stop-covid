package fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote;

public class ServerApi {

    private final UserApi userApi;

    /**
     * Create a server API, which references the different parts of the server's public API.
     * @param userApi
     */
    public ServerApi(final UserApi userApi) {
        this.userApi = userApi;
    }


    void setModel(final ServerModel model) {
        userApi.setModel(model);
    }


    /**
     * Get the user service.
     * @return
     */
    public UserApi getUserApi() {
        return userApi;
    }
}
