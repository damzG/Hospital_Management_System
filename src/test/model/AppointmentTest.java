//Oyindamola Olaosun C00313475 OOSD Project

package test.model;

import model.Appointment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Appointment model.
 */
class AppointmentTest {

    @Test
    @DisplayName("Constructor sets status to BOOKED by default")
    void testDefaultStatus() {
        Appointment appt = new Appointment(1, 2,
                LocalDate.of(2025, 6, 15), "09:00", 1);

        assertEquals("BOOKED", appt.getStatus());
    }

    @Test
    @DisplayName("Constructor sets all fields correctly")
    void testConstructorFields() {
        LocalDate date = LocalDate.of(2025, 6, 15);
        Appointment appt = new Appointment(1, 2, date, "10:30", 3);

        assertEquals(1,      appt.getPatientID());
        assertEquals(2,      appt.getDoctorID());
        assertEquals(date,   appt.getAppointment_date());
        assertEquals("10:30",appt.getTimeSlot());
        assertEquals(3,      appt.getReceptionistId());
    }

    @Test
    @DisplayName("setStatus updates correctly")
    void testSetStatus() {
        Appointment appt = new Appointment(1, 2,
                LocalDate.now(), "09:00", 1);
        appt.setStatus("CANCELLED");

        assertEquals("CANCELLED", appt.getStatus());
    }

    @Test
    @DisplayName("setAppointmentId assigns correctly")
    void testSetAppointmentId() {
        Appointment appt = new Appointment(1, 2,
                LocalDate.now(), "09:00", 1);
        appt.setAppointmentId(7);

        assertEquals(7, appt.getAppointmentId());
    }
}
