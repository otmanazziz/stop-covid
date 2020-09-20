package fr.univ_lyon1.info.m1.stopcovid_simulator.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class DataTest {

    /**
     * Add keys to the KeysManager and test if they can be gotten back.
     */
    @Test
    public void addToKeysManager() {
        //Given
        KeysManager km = new DatedKeysCollection();

        //When
        km.addKey("k1");
        km.addKey("k2", Instant.EPOCH);

        //Then
        assertEquals(km.getKeys().contains("k1"), true);
        assertEquals(km.getKeys().contains("k2"), true);
        assertEquals(km.getDatedKeys()
                .stream().filter(datedKey -> datedKey.getDate()==Instant.EPOCH).count(),
                1);
        assertEquals(km.getDatedKeys()
                        .stream().filter(datedKey -> datedKey.getDate()==Instant.now()).count(),
                0);
    }

    /**
     * Add keys linked to different times and test if the most recent one is returned with
     * getNewestKey().
     */
    @Test
    public void keysManagerGetNewestKey() {
        //Given
        KeysManager km = new DatedKeysCollection();

        //When
        km.addKey("k_60", Instant.EPOCH.plusSeconds(60));
        km.addKey("k_0", Instant.EPOCH);
        km.addKey("k_30", Instant.EPOCH.plusSeconds(30));

        //Then
        assertEquals(km.getNewestKey().getKey(), "k_60");
    }

    /**
     * Test that observables get fired as they should.
     */
    @Test
    public void keysManagerTestObservables() {
        //Given
        KeysManager km = new DatedKeysCollection();
        final int individualAdds = 10;
        final int groupedUpdates = 5;
        final int keysByUpdates = 5;

        var ref = new Object() {
            int addKeyCallbackCount = 0;
            int updateKeysCallbackCount = 0;
        };


        km.getObservableKeysAdded().subscribe(() -> ref.addKeyCallbackCount++);
        km.getObservableKeysUpdated().subscribe(() -> ref.updateKeysCallbackCount++);

        //When
        for(int i = 0; i < individualAdds; ++i) {
            km.addKey(Integer.toString(i));
        }

        for(int i = 0; i < groupedUpdates; ++i) {
            List<DatedKey> keys = new ArrayList<>();
            for (int j = 0; j < keysByUpdates; ++j) {
                var k = new DatedKey("grouped"+Integer.toString(i*i*j), Instant.now());
                keys.add(k);
            }

            km.addKeys(keys);
        }

        //Then
        assertEquals(individualAdds + groupedUpdates, ref.addKeyCallbackCount);
        assertEquals(individualAdds + groupedUpdates, ref.updateKeysCallbackCount);
    }
}
