//Oyindamola Olaosun C00313475 OOSD Project

package test.model;

import model.Receptionist;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Receptionist model.
 */
class ReceptionistTest {

    @Test
    @DisplayName("Constructor sets all fields correctly")
    void testConstructor() {
        Receptionist rec = new Receptionist(1, "Nodal Ranberry",
                "tg@hms.ie", "0916482648", "rgb", "cryptonite");

        assertEquals(1,              rec.getReceptionistId());
        assertEquals("Nodal Ranberry", rec.getName());
        assertEquals("tg@hms.ie",    rec.getEmail());
        assertEquals("0916482648",   rec.getPhone());
        assertEquals("rgb",          rec.getUsername());
        assertEquals("cryptonite",   rec.getPassword());
    }

    @Test
    @DisplayName("Setters update all fields correctly")
    void testSetters() {
        Receptionist rec = new Receptionist(1, "Old Name",
                "old@hms.ie", "0000000000", "olduser", "oldpass");

        rec.setName("New Name");
        rec.setEmail("new@hms.ie");
        rec.setPhone("0871234567");
        rec.setUsername("newuser");
        rec.setPassword("newpass");

        assertEquals("New Name",    rec.getName());
        assertEquals("new@hms.ie",  rec.getEmail());
        assertEquals("0871234567",  rec.getPhone());
        assertEquals("newuser",     rec.getUsername());
        assertEquals("newpass",     rec.getPassword());
    }
}
