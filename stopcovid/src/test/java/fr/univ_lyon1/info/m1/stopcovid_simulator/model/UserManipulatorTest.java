package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

import static org.junit.jupiter.api.Assertions.*;

import fr.univ_lyon1.info.m1.stopcovid_simulator.data.DatedKeysCollection;
import fr.univ_lyon1.info.m1.stopcovid_simulator.data.KeysManager;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.simulator.CovidSimulator;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.simulator.SimulatorModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.ContactAmountRiskyFlagging;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.CovidLocalUser;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.UserLocalModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.CovidServer;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.ServerModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.SimulatedUserApi;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.RamUserDatabase;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.User;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.UserDatabase;
import fr.univ_lyon1.info.m1.stopcovid_simulator.simulation.FakeTime;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserManipulatorTest {

    //TokensDatabase covidTokensDb = new RamTokensDatabase();
    private SimulatedUserApi userApi;
    private KeysManager infectedKeysManager;
    private UserDatabase userDb;
    private ServerModel serverModel;
    private SimulatorModel simulatorModel;

    public UserManipulatorTest() throws Exception {
        this.userApi = new SimulatedUserApi();
        this.infectedKeysManager = new DatedKeysCollection();
        this.userDb = new RamUserDatabase();

        this.serverModel = new CovidServer.Builder()
                .withInfectedKeys(infectedKeysManager)
                .withUserDatabase(userDb)
                .withUserApi(userApi)
                .build();

        this.simulatorModel = new CovidSimulator();
    }

    @Test
    public void createUsers() {
        //Given
        User u1 = userDb.createUser();
        User u2 = userDb.createUser();
        UserLocalModel user1 = new CovidLocalUser(u1.getToken());
        UserLocalModel user2 = new CovidLocalUser(u2.getToken());

        //When
        simulatorModel.addUser(user1);
        simulatorModel.addUser(user2);

        //Then
        assertEquals(simulatorModel.getUsers().size(), 2);
        assertNotEquals(user1.getUserToken(), user2.getUserToken());
        assertNotEquals(simulatorModel.getUser(user1.getUserToken()), simulatorModel.getUser(user2.getUserToken()));
    }

    @Test
    public void meetingUsers(){
        //Given
        String token1 = userApi.register();
        String token2 = userApi.register();
        String token3 = userApi.register();
        UserLocalModel user1 = new CovidLocalUser(token1);
        UserLocalModel user2 = new CovidLocalUser(token2);
        UserLocalModel user3 = new CovidLocalUser(token3);
        simulatorModel.addUser(user1);
        simulatorModel.addUser(user2);
        simulatorModel.addUser(user3);

        //When
        user1.getMetKeysManager().addKey(user2.getPersonalKeysManager().getNewestKey().getKey());
        user1.getMetKeysManager().addKey(user3.getPersonalKeysManager().getNewestKey().getKey());
        user3.getMetKeysManager().addKey(user2.getPersonalKeysManager().getNewestKey().getKey());

        //Then
        assertEquals(simulatorModel.getUsers().size(), 3);

        assertEquals(simulatorModel.getUser(user1.getUserToken()).getMetKeysManager().getKeys().contains(
                simulatorModel.getUser(user2.getUserToken()).getPersonalKeysManager().getNewestKey().getKey())
                , true);

        assertNotEquals(simulatorModel.getUser(user2.getUserToken()).getMetKeysManager().getKeys().contains(
                simulatorModel.getUser(user1.getUserToken()).getPersonalKeysManager().getNewestKey().getKey())
                , true);

        assertEquals(simulatorModel.getUser(user1.getUserToken()).getMetKeysManager().getKeys().contains(
                simulatorModel.getUser(user3.getUserToken()).getPersonalKeysManager().getNewestKey().getKey())
                , true);

        assertEquals(simulatorModel.getUser(user2.getUserToken()).getMetKeysManager().getKeys().contains(
                simulatorModel.getUser(user3.getUserToken()).getPersonalKeysManager().getNewestKey().getKey())
                , false);
    }

    @Test
    public void userDeclareInfected(){
        //Given
        String token1 = userApi.register();
        String token2 = userApi.register();
        String token3 = userApi.register();
        UserLocalModel user1 = new CovidLocalUser(token1);
        UserLocalModel user2 = new CovidLocalUser(token2);
        UserLocalModel user3 = new CovidLocalUser(token3);
        simulatorModel.addUser(user1);
        simulatorModel.addUser(user2);
        simulatorModel.addUser(user3);
        user1.getMetKeysManager().addKey(user2.getPersonalKeysManager().getNewestKey().getKey());
        user1.getMetKeysManager().addKey(user3.getPersonalKeysManager().getNewestKey().getKey());
        user3.getMetKeysManager().addKey(user2.getPersonalKeysManager().getNewestKey().getKey());

        //When
        userApi.declareInfected(user1.getUserToken(), "", user1.getPersonalKeysManager().getDatedKeys());
        List<String> newInfectedKeysList = userApi.getInfectedKeys();
        for (String key : newInfectedKeysList) {
            user2.getInfectedKeysManager().addKey(key, FakeTime.getInstance().getNow());
            user3.getInfectedKeysManager().addKey(key, FakeTime.getInstance().getNow());
        }
        user2.getMetKeysManager().addKey(user1.getPersonalKeysManager().getNewestKey().getKey());
        user3.getMetKeysManager().addKey(user2.getPersonalKeysManager().getNewestKey().getKey());

        //Then
        assertEquals(userApi.getInfectedKeys().containsAll(user1.getPersonalKeysManager().getKeys()), true);
        assertEquals(user2.getIsRisky(), true);
        assertNotEquals(user3.getIsRisky(), true);
    }

    @Test
    public void deleteUsers(){
        //Given
        String token1 = userApi.register();
        String token2 = userApi.register();
        UserLocalModel user1 = new CovidLocalUser(token1);
        UserLocalModel user2 = new CovidLocalUser(token2);
        simulatorModel.addUser(user1);
        simulatorModel.addUser(user2);

        assertEquals(simulatorModel.getUsers().size(), 2);
        //When

        simulatorModel.removeUser(token1);

        //Then
        assertNotEquals(simulatorModel.getUsers().size(), 2);
        assertEquals(simulatorModel.getUsers().size(), 1);
        assertEquals(simulatorModel.getUsers().contains(user1), false);
        assertEquals(simulatorModel.getUsers().contains(user2), true);
    }
}
