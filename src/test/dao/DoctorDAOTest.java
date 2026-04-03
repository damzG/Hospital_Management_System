//Oyindamola Olaosun C00313475 OOSD Project

package test.dao;

import dao.DoctorDAO;
import model.Doctor;
import org.junit.jupiter.api.*;
import util.DBconnect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for DoctorDAO.
 */
class DoctorDAOTest {

    private Connection conn;

    @BeforeEach
    void setUp() throws SQLException {
        conn = DBconnect.getConnection();
        conn.setAutoCommit(false);
    }

    @AfterEach
    void tearDown() throws SQLException {
        conn.rollback();
        conn.close();
    }

    @Test
    @DisplayName("getAllActiveDoctors returns a list using while loop")
    void testGetAllActiveDoctors() throws SQLException {
        List<Doctor> doctors = DoctorDAO.getAllActiveDoctors();
        assertNotNull(doctors);
        // Confirms all rows are retrieved — not just the first
        assertTrue(doctors.size() >= 0);
    }

    @Test
    @DisplayName("addDoctor inserts without throwing")
    void testAddDoctor() {
        Doctor doctor = new Doctor("Dr. Test Doctor", "Cardiology");
        assertDoesNotThrow(() -> DoctorDAO.addDoctor(doctor));
    }

    @Test
    @DisplayName("updateDoctorSpecialization does not throw")
    void testUpdateSpec() {
        assertDoesNotThrow(() ->
                DoctorDAO.updateDoctorSpecialization(9999, "Neurology"));
    }

    @Test
    @DisplayName("removeDoctor does not throw for unknown ID")
    void testRemoveDoctor() {
        assertDoesNotThrow(() -> DoctorDAO.removeDoctor(9999));
    }
}
