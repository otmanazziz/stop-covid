package fr.univ_lyon1.info.m1.stopcovid_simulator.view;

import fr.univ_lyon1.info.m1.stopcovid_simulator.model.local.user.RiskyFlaggingStrategy;
import fr.univ_lyon1.info.m1.stopcovid_simulator.util.Observable;

public interface UserView {
    /**
     * Get the {@link Observable} called when the user wants to declare himself as infected.
     * @return the {@link Observable}.
     */
    Observable getDeclaredInfectedObservable();

    /**
     * Get the {@link Observable} called when the user changes the risky flagging strategy.
     * @return
     */
    Observable getRiskyStrategyChangedObservable();


    /**
     * Get the Covid token inputted by the user.
     * @return the covid token.
     */
    String getCovidToken();

    /**
     * Get the risky flagging strategy selected by the view.
     * @return
     */
    RiskyFlaggingStrategy getSelectedRiskyFlaggingStrategy();
}
