package fr.univ_lyon1.info.m1.stopcovid_simulator.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UserManipulatorTest {

    @Test
    public void simpleCreateUser() {
        //Given
        String name = "Moy Matthieu";

        //When
        User user = new User(name);

        //Then
        assertEquals(name, user.getName());
        assertEquals(0, user.getContacts().size());
        assertEquals(CovidStatus.NO_DANGER, user.getStatus());
        assertEquals("Non infectée", user.getStatusToString());
    }
}
