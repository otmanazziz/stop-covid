package fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote;

import fr.univ_lyon1.info.m1.stopcovid_simulator.data.KeysManager;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.TokensDatabase;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.UserDatabase;

public final class CovidServer implements ServerModel, ServerApi {

    private final UserApi userApi;

    private final UserDatabase users;
    private final KeysManager infectedKeys;
    private final TokensDatabase covidTokens;



    private CovidServer(final Builder builder) {
        infectedKeys = builder.infectedKeys;
        users = builder.userDatabase;
        covidTokens = builder.covidTokens;

        userApi = builder.userApi;
        userApi.setModel(this);
    }


    @Override
    public UserApi getUserApi() {
        return userApi;
    }

    @Override
    public KeysManager getInfectedKeys() {
        return infectedKeys;
    }

    @Override
    public UserDatabase getUsers() {
        return users;
    }

    @Override
    public ServerApi getApi() {
        return this;
    }


    public static class Builder {
        private KeysManager infectedKeys;
        private UserDatabase userDatabase;
        private TokensDatabase covidTokens;
        private UserApi userApi;

        /**
         * Creates a CovidServer builder.
         */
        public Builder() {
        }

        /**
         * Builds the CovidServer. Will throw if mandatory fields haven't been assigned.
         * @return A CovidServer made from this builder.
         * @throws Exception
         */
        public CovidServer build() throws Exception {
            if (infectedKeys == null
                    || userDatabase == null
                    || userApi == null) {
                throw new Exception("CovidServer not fully built.");
            }

            return new CovidServer(this);
        }

        /**
         * Assign the infected keys manager.
         * Returns this builder for further building.
         * @param infectedKeys
         * @return
         */
        public Builder withInfectedKeys(final KeysManager infectedKeys) {
            this.infectedKeys = infectedKeys;
            return this;
        }

        /**
         * Assign the user database.
         * @param userDatabase
         * @return
         */
        public Builder withUserDatabase(final UserDatabase userDatabase) {
            this.userDatabase = userDatabase;
            return this;
        }

        /**
         * Assign the user token database.
         * @param covidTokensDb
         * @return
         */
        public Builder withCovidTokensDatabase(final TokensDatabase covidTokensDb) {
            this.covidTokens = covidTokensDb;
            return this;
        }

        /**
         * Assign the user API.
         * @param userApi
         * @return
         */
        public Builder withUserApi(final UserApi userApi) {
            this.userApi = userApi;
            return this;
        }
    }
}
