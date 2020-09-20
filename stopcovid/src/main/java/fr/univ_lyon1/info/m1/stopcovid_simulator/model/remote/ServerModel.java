package fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote;

import fr.univ_lyon1.info.m1.stopcovid_simulator.data.KeysManager;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.TokensDatabase;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.UserDatabase;

public final class ServerModel {

    private final ServerApi serverApi;

    private final UserDatabase users;
    private final KeysManager infectedKeys;
    private final TokensDatabase covidTokens;



    private ServerModel(final Builder builder) {
        infectedKeys = builder.infectedKeys;
        users = builder.userDatabase;
        covidTokens = builder.covidTokens;
        serverApi = builder.serverApi;
        serverApi.setModel(this);
    }

    /**
     * Get the object managing infected keys.
     * @return
     */
    public KeysManager getInfectedKeys() {
        return infectedKeys;
    }

    /**
     * Get the user database's accessor.
     * @return
     */
    public UserDatabase getUsers() {
        return users;
    }

    /**
     * Get this server's API.
     * @return
     */
    public ServerApi getApi() {
        return serverApi;
    }


    public static class Builder {
        private KeysManager infectedKeys;
        private UserDatabase userDatabase;
        private TokensDatabase covidTokens;
        private ServerApi serverApi;

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
        public ServerModel build() throws Exception {
            if (infectedKeys == null
                    || userDatabase == null
                    || serverApi == null) {
                throw new Exception("CovidServer not fully built.");
            }

            return new ServerModel(this);
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
         * Assign the user token database. (UNUSED)
         * @param covidTokensDb
         * @return
         */
        public Builder withCovidTokensDatabase(final TokensDatabase covidTokensDb) {
            this.covidTokens = covidTokensDb;
            return this;
        }

        /**
         * Assign the user API.
         * @param serverApi
         * @return
         */
        public Builder withApi(final ServerApi serverApi) {
            this.serverApi = serverApi;
            return this;
        }
    }
}
