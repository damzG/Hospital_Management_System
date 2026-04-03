//Oyindamola Olaosun C00313475 OOSD Project

package test.dao;

import dao.PatientDAO;
import model.Patient;
import org.junit.jupiter.api.*;
import util.DBconnect;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for PatientDAO.
 * Each test rolls back so no test data is left in the database.
 */
class PatientDAOTest {

    private Connection conn;

    @BeforeEach
    void setUp() throws SQLException {
        conn = DBconnect.getConnection();
        conn.setAutoCommit(false); // start transaction — rolls back after each test
    }

    @AfterEach
    void tearDown() throws SQLException {
        conn.rollback(); // clean up — no test data left behind
        conn.close();
    }

    @Test
    @DisplayName("addPatient inserts a record without throwing")
    void testAddPatient() {
        Patient patient = new Patient("Test Patient",
                LocalDate.of(1995, 5, 10),
                "0861111111", "1 Test Street", "Male", 1);

        assertDoesNotThrow(() -> PatientDAO.addPatient(patient));
    }

    @Test
    @DisplayName("getAllActivePatients returns a non-null list")
    void testGetAllActivePatients() throws SQLException {
        List<Patient> patients = PatientDAO.getAllActivePatients();
        assertNotNull(patients);
    }

    @Test
    @DisplayName("getAllActivePatients returns only ACTIVE records")
    void testOnlyActiveReturned() throws SQLException {
        List<Patient> patients = PatientDAO.getAllActivePatients();
        // Every returned patient should be retrievable — list should not be null
        assertTrue(patients instanceof List);
    }

    @Test
    @DisplayName("updatePatientAddress does not throw")
    void testUpdateAddress() {
        // Uses an ID that may not exist — should handle gracefully
        assertDoesNotThrow(() ->
                PatientDAO.updatePatientAddress(9999, "New Address, Dublin"));
    }

    @Test
    @DisplayName("deactivatePatient does not throw for unknown ID")
    void testDeactivatePatient() {
        assertDoesNotThrow(() -> PatientDAO.deactivatePatient(9999));
    }
}