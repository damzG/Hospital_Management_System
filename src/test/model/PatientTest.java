//Oyindamola Olaosun C00313475 OOSD Project

package test.model;

import model.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Patient model.
 * Tests constructors, getters, setters, and toString.
 */
class PatientTest {

    /**
     * Unit tests for the Patient model.
     * Tests constructors, getters, setters, and toString.
     */
    @Test
    @DisplayName("Constructor sets all fields correctly")
    void testConstructorWithReceptionistId() {
        LocalDate dob = LocalDate.of(1990, 3, 14);
        Patient patient = new Patient("Aoife Murphy", dob, "0861234567",
                "12 Oak Lane, Dublin", "Female", 1);

        assertEquals("Aoife Murphy",        patient.getName());
        assertEquals(dob,                   patient.getDob());
        assertEquals("0861234567",          patient.getPhone());
        assertEquals("12 Oak Lane, Dublin", patient.getAddress());
        assertEquals("Female",              patient.getGender());
        assertEquals(1,                     patient.getReceptionistId());
    }

    /**
     * Unit tests for the Patient model.
     * Tests constructors, getters, setters, and toString.
     */
    @Test
    @DisplayName("Full constructor sets patientId correctly")
    void testFullConstructor() {
        LocalDate dob = LocalDate.of(1985, 7, 22);
        Patient patient = new Patient( "Ciarán Kelly", dob,
                "0872345678", "5 Maple Drive, Cork", "Male", 1);

        assertEquals(5,              patient.getPatientId());
        assertEquals("Ciarán Kelly", patient.getName());
    }

    /**
     * Unit tests for the Patient model.
     * Tests constructors, getters, setters, and toString.
     */
    @Test
    @DisplayName("setPatientId assigns correctly")
    void testSetPatientId() {
        Patient patient = new Patient();
        patient.setPatientId(10);
        assertEquals(10, patient.getPatientId());
    }

    /**
     * Unit tests for the Patient model.
     * Tests constructors, getters, setters, and toString.
     */
    @Test
    @DisplayName("Setters update fields correctly")
    void testSetters() {
        Patient patient = new Patient();
        patient.setName("Sinéad O'Brien");
        patient.setPhone("0853456789");
        patient.setAddress("34 River Road, Galway");
        patient.setGender("Female");
        patient.setDob(LocalDate.of(1998, 11, 8));
        patient.setReceptionistId(2);

        assertEquals("Sinéad O'Brien",        patient.getName());
        assertEquals("0853456789",            patient.getPhone());
        assertEquals("34 River Road, Galway", patient.getAddress());
        assertEquals("Female",                patient.getGender());
        assertEquals(2,                       patient.getReceptionistId());
    }

    /**
     * Unit tests for the Patient model.
     * Tests constructors, getters, setters, and toString.
     */
    @Test
    @DisplayName("toString returns correct format")
    void testToString() {
        LocalDate dob = LocalDate.of(1990, 1, 1);
        Patient patient = new Patient("Niamh Brennan", dob,
                "0895678901", "21 Elm Close", "Female", 1);

        assertEquals("Niamh Brennan (ID: 3)", patient.toString());
    }

    /**
     * Unit tests for the Patient model.
     * Tests constructors, getters, setters, and toString.
     */
    @Test
    @DisplayName("Default constructor creates empty patient")
    void testDefaultConstructor() {
        Patient patient = new Patient();
        assertNull(patient.getName());
        assertEquals(0, patient.getPatientId());
    }
}