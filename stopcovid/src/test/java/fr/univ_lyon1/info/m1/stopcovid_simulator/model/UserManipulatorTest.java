package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

import static org.junit.jupiter.api.Assertions.*;

import fr.univ_lyon1.info.m1.stopcovid_simulator.data.DatedKeysCollection;
import fr.univ_lyon1.info.m1.stopcovid_simulator.data.KeysManager;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.simulator.CovidSimulator;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.simulator.SimulatorModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.ContactAmountRiskyFlagging;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.CovidLocalUser;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.UserLocalModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.ServerApi;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.ServerModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.SimulatedUserApi;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.RamUserDatabase;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.User;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.UserDatabase;
import fr.univ_lyon1.info.m1.stopcovid_simulator.simulation.FakeTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserManipulatorTest {

    //TokensDatabase covidTokensDb = new RamTokensDatabase();
    private ServerApi serverApi;
    private SimulatedUserApi userApi;
    private KeysManager infectedKeysManager;
    private UserDatabase userDb;
    private ServerModel serverModel;
    private SimulatorModel simulatorModel;

    @BeforeEach
    public void setup() throws Exception {
        this.userApi = new SimulatedUserApi();
        this.serverApi = new ServerApi(this.userApi);
        this.infectedKeysManager = new DatedKeysCollection();
        this.userDb = new RamUserDatabase();

        this.serverModel = new ServerModel.Builder()
                .withInfectedKeys(infectedKeysManager)
                .withUserDatabase(userDb)
                .withApi(serverApi)
                .build();

        this.simulatorModel = new CovidSimulator();
    }

    /**
     * Create users by registering them on the server.
     */
    @Test
    public void registerUsersOnServer() {
        //Given
        String u1Token = userApi.register();
        String u2Token = userApi.register();

        //When

        //Then
        assertNotEquals(null, userDb.getUser(u1Token));
        assertNotEquals(null, userDb.getUser(u2Token));
        assertEquals(null, userDb.getUser("____DO NOT EXIST____"));
    }

    /**
     * Register users, add them to the simulator model and check if they are there.
     */
    public void addUsersToSimulatorModel() {
        //Given
        String u1Token = userApi.register();
        String u2Token = userApi.register();
        UserLocalModel user1 = new CovidLocalUser(u1Token);
        UserLocalModel user2 = new CovidLocalUser(u2Token);

        //When
        simulatorModel.addUser(user1);
        simulatorModel.addUser(user2);

        //Then
        assertEquals(simulatorModel.getUsers().size(), 2);
        assertNotEquals(user1.getUserToken(), user2.getUserToken());
        assertNotEquals(simulatorModel.getUser(user1.getUserToken()),
                simulatorModel.getUser(user2.getUserToken()));

        assertEquals(simulatorModel.getUser(user1.getUserToken()), user1);
        assertEquals(simulatorModel.getUser(user2.getUserToken()), user2);
        assertEquals(simulatorModel.getUser("__INVALID TOKEN__"), null);
    }


    /**
     * Test making three users meet. u1 meets u2 & u3, u3 meets u2.
     */
    @Test
    public void meetingUsers(){
        //Given
        String token1 = userApi.register();
        String token2 = userApi.register();
        String token3 = userApi.register();
        UserLocalModel user1 = new CovidLocalUser(token1);
        UserLocalModel user2 = new CovidLocalUser(token2);
        UserLocalModel user3 = new CovidLocalUser(token3);

        //When
        user1.getMetKeysManager().addKey(user2.getOwnKeysManager().getNewestKey().getKey());
        user1.getMetKeysManager().addKey(user3.getOwnKeysManager().getNewestKey().getKey());
        user3.getMetKeysManager().addKey(user2.getOwnKeysManager().getNewestKey().getKey());

        //Then
        assertEquals(user1.getMetKeysManager().getKeys().contains(
                user2.getOwnKeysManager().getNewestKey().getKey()),
                true);

        assertNotEquals(user2.getMetKeysManager().getKeys().contains(
                user1.getOwnKeysManager().getNewestKey().getKey()),
                true);

        assertEquals(user1.getMetKeysManager().getKeys().contains(
                user3.getOwnKeysManager().getNewestKey().getKey()),
                true);

        assertEquals(user2.getMetKeysManager().getKeys().contains(
                user3.getOwnKeysManager().getNewestKey().getKey()),
                false);
    }

    /**
     * Make a user declare himself as infected, check other users' strategy fires the default
     * risky flagging strategy. Also checks the infected user's own keys are contained in the
     * list of infected keys given by the server.
     */
    @Test
    public void userDeclareInfectedToServer(){
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
        user1.getMetKeysManager().addKey(user2.getOwnKeysManager().getNewestKey().getKey());
        user1.getMetKeysManager().addKey(user3.getOwnKeysManager().getNewestKey().getKey());
        user3.getMetKeysManager().addKey(user2.getOwnKeysManager().getNewestKey().getKey());

        //When
        userApi.declareInfected(user1.getUserToken(),
                "",
                user1.getOwnKeysManager().getDatedKeys());
        List<String> newInfectedKeysList = userApi.getInfectedKeys();
        for (String key : newInfectedKeysList) {
            user2.getInfectedKeysManager().addKey(key, FakeTime.getInstance().getNow());
            user3.getInfectedKeysManager().addKey(key, FakeTime.getInstance().getNow());
        }
        user2.getMetKeysManager().addKey(user1.getOwnKeysManager().getNewestKey().getKey());
        user3.getMetKeysManager().addKey(user2.getOwnKeysManager().getNewestKey().getKey());

        //Then
        assertEquals(userApi.getInfectedKeys().containsAll(user1.getOwnKeysManager().getKeys()),
                true);
        assertEquals(user2.getIsRisky(), true);
        assertEquals(user3.getIsRisky(), false);
    }

    /**
     * Add users to the simulator's model, and checks if removing them does work.
     */
    @Test
    public void deleteUsersFromSimulatorModel(){
        //Given
        String token1 = userApi.register();
        String token2 = userApi.register();
        UserLocalModel user1 = new CovidLocalUser(token1);
        UserLocalModel user2 = new CovidLocalUser(token2);
        simulatorModel.addUser(user1);
        simulatorModel.addUser(user2);

        // Preliminary then
        assertEquals(simulatorModel.getUsers().size(), 2);
        //When

        simulatorModel.removeUser(token1);

        //Then
        assertNotEquals(simulatorModel.getUsers().size(), 2);
        assertEquals(simulatorModel.getUsers().size(), 1);
        assertEquals(simulatorModel.getUsers().contains(user1), false);
        assertEquals(simulatorModel.getUsers().contains(user2), true);
    }

    @Test
    public void riskyFlaggingStrategySwapping() {
        //Given
        String token1 = userApi.register();
        UserLocalModel user1 = new CovidLocalUser(token1);
        user1.setRiskyFlaggingStrategy(new ContactAmountRiskyFlagging(1));
        String infectedKey = "dangerous";

        //When
        user1.getMetKeysManager().addKey(infectedKey);
        user1.getInfectedKeysManager().addKey(infectedKey);

        //Then
        assertEquals(user1.getIsRisky(), true);
        user1.setRiskyFlaggingStrategy(new ContactAmountRiskyFlagging(2));
        assertEquals(user1.getIsRisky(), false);
    }

}
