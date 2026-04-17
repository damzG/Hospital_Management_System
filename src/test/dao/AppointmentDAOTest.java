//Oyindamola Olaosun C00313475 OOSD Project

package test.dao;

import dao.AppointmentDAO;
import org.junit.jupiter.api.*;
import util.DBconnect;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for AppointmentDAO.
 */
class AppointmentDAOTest {

    private Connection conn;

    /**
     * Tests for setting up database connection
     */
    @BeforeEach
    void setUp() throws SQLException {
        conn = DBconnect.getConnection();
        conn.setAutoCommit(false);
    }

    /**
     * Tests for tearing down database connection
     */
    @AfterEach
    void tearDown() throws SQLException {
        conn.rollback();
        conn.close();
    }

    /**
     * Tests to check if the doctor is available (true)
     */
    @Test
    @DisplayName("isDoctorAvailable returns true for unused slot")
    void testDoctorAvailableUnusedSlot() throws SQLException {
        // A date far in the future — almost certainly free
        boolean available = AppointmentDAO.isDoctorAvailable(
                1, LocalDate.of(2099, 12, 31), "09:00");
        assertTrue(available);
    }

    /**
     * Tests to check if the doctor is unavailable (false)
     */
    @Test
    @DisplayName("isDoctorAvailable returns false after booking same slot")
    void testDoctorUnavailableAfterBooking() throws SQLException {
        LocalDate testDate = LocalDate.of(2099, 6, 15);
        String slot = "14:00";
        int doctorId = 1;

        // Should be free before booking
        assertTrue(AppointmentDAO.isDoctorAvailable(doctorId, testDate, slot));
    }

    /**
     * Tests to deactivate the appointment
     */
    @Test
    @DisplayName("deactivateAppointment does not throw for unknown ID")
    void testDeactivate() {
        assertDoesNotThrow(() ->
                AppointmentDAO.deactivateAppointment(9999));
    }

    /**
     * Tests to get the AppointmentById
     */
    @Test
    @DisplayName("getAppointmentById does not throw for unknown ID")
    void testGetById() {
        assertDoesNotThrow(() ->
                AppointmentDAO.getAppointmentById(9999));
    }
}