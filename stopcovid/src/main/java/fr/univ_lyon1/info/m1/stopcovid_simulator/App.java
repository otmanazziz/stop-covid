package fr.univ_lyon1.info.m1.stopcovid_simulator;

import fr.univ_lyon1.info.m1.stopcovid_simulator.controller.SimulatorController;
import fr.univ_lyon1.info.m1.stopcovid_simulator.data.DatedKeysCollection;
import fr.univ_lyon1.info.m1.stopcovid_simulator.data.KeysManager;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.simulator.CovidSimulator;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.simulator.SimulatorModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.CovidServer;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.ServerModel;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.SimulatedUserApi;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.RamUserDatabase;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.UserDatabase;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.SimulatorView;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx.JfxSimulatorView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the application (structure imposed by JavaFX).
 */
public class App extends Application {


    /**
     * With javafx, start() is called when the application is launched.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        //TokensDatabase covidTokensDb = new RamTokensDatabase();
        SimulatedUserApi userApi = new SimulatedUserApi();
        KeysManager infectedKeysManager = new DatedKeysCollection();
        UserDatabase userDb = new RamUserDatabase();

        ServerModel serverModel = new CovidServer.Builder()
                .withInfectedKeys(infectedKeysManager)
                .withUserDatabase(userDb)
                .withUserApi(userApi)
                .build();

        SimulatorModel simulatorModel = new CovidSimulator();

        SimulatorView simulatorView = new JfxSimulatorView(stage, serverModel, simulatorModel);

        var simulatorController = new SimulatorController(simulatorModel,
                simulatorView,
                serverModel);
    }

    /**
     * A main method in case the user launches the application using
     * App as the main class.
     *
     * @param args Command-line arguments
     */
    public static void main(final String[] args) {
        Application.launch(args);
    }
}
