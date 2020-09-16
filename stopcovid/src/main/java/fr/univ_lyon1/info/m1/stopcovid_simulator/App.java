package fr.univ_lyon1.info.m1.stopcovid_simulator;

import fr.univ_lyon1.info.m1.stopcovid_simulator.controller.ServerController;
import fr.univ_lyon1.info.m1.stopcovid_simulator.data.DatedKeysCollection;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.CovidServer;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.RamTokensDatabase;
import fr.univ_lyon1.info.m1.stopcovid_simulator.model.remote.storage.RamUserDatabase;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.SimulatorView;
import fr.univ_lyon1.info.m1.stopcovid_simulator.view.jfx.JfxMainView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Main class for the application (structure imposed by JavaFX).
 */
public class App extends Application {
    static final int WIDTH = 600;
    static final int HEIGHT = 600;
    static final int TOTAL_USERS = 3;



    /**
     * With javafx, start() is called when the application is launched.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        RamTokensDatabase tokensDatabase = new RamTokensDatabase();
        DatedKeysCollection datedKeysCollection = new DatedKeysCollection();
        RamUserDatabase userDatabase = new RamUserDatabase();

        CovidServer server = new CovidServer.Builder()
                .withCovidTokensDatabase(tokensDatabase)
                .withInfectedKeys(datedKeysCollection)
                .withUserDatabase(userDatabase)
                .build();

        List<String> tokens = new ArrayList<>();
        for (int i = 0; i < TOTAL_USERS; i++) {
            tokens.add(server.getUserApi().register());
        }

        SimulatorView v = new JfxMainView(stage, WIDTH, HEIGHT);

        ServerController controller = new ServerController(v, server);
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
