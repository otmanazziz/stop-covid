package fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RamUserDatabase implements UserDatabase {

    private Map<String, User> users = new HashMap<>();

    @Override
    public User createUser() {
        String userToken = UUID.randomUUID().toString();
        User u = new RamUser(userToken);
        users.put(userToken, u);
        return u;
    }

    @Override
    public User getUser(final String userToken) {
        return users.get(userToken);
    }

    @Override
    public void removeUser(final String userToken) {
        users.remove(userToken);
    }


    private class RamUser extends User {
        private final String token;

        /**
         * Create a user identified by a unique token.
         * @param token
         */
        RamUser(final String token) {
            this.token = token;
        }


        /**
         * Gets this user's unique token.
         * @return
         */
        public String getToken() {
            return token;
        }
    }
}
