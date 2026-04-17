//Oyindamola Olaosun C00313475 OOSD Project

package test.model;

import model.Doctor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Doctor model.
 */
class DoctorTest {

    /**
     * Unit test to set name and specialization (constructor).
     */
    @Test
    @DisplayName("Two-arg constructor sets name and specialization")
    void testTwoArgConstructor() {
        Doctor doctor = new Doctor("Dr. Mary Nolan", "Cardiology");

        assertEquals("Dr. Mary Nolan", doctor.getName());
        assertEquals("Cardiology",     doctor.getSpecialization());
    }

    /**
     * Unit tests for setting the constructor
     */
    @Test
    @DisplayName("Full constructor sets all fields")
    void testFullConstructor() {
        Doctor doctor = new Doctor(1, "Dr. James Flynn", "Neurology");

        assertEquals(1,                  doctor.getId());
        assertEquals("Dr. James Flynn",  doctor.getName());
        assertEquals("Neurology",        doctor.getSpecialization());
    }

    /**
     * Unit tests for printing out the patient name and ID.
     */
    @Test
    @DisplayName("toString returns name and ID")
    void testToString() {
        Doctor doctor = new Doctor(2, "Dr. Sarah Lynch", "Paediatrics");
        assertEquals("Dr. Sarah Lynch (2)", doctor.toString());
    }

    /**
     * Unit tests for setting the fields correctly
     */
    @Test
    @DisplayName("Setters update fields correctly")
    void testSetters() {
        Doctor doctor = new Doctor("Dr. Tom Burke", "General");
        doctor.setName("Dr. Thomas Burke");
        doctor.setSpecialization("Oncology");

        assertEquals("Dr. Thomas Burke", doctor.getName());
        assertEquals("Oncology",         doctor.getSpecialization());
    }
}