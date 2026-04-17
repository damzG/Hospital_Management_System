//Oyindamola Olaosun C00313475 OOSD Project

package model;

import java.time.LocalDate;

/**
 * Represents a patient registered in the hospital management system.
 * Stores personal details including contact information and demographics.
 */

public class Patient {

    /** Unique identifier for this patient (DB-generated). */
    private int patientId;

    /** Full name of the patient. */
    private String name;

    /** Date of birth of the patient. */
    private LocalDate dob;

    /** Contact phone number of the patient. */
    private String phone;

    /** Residential address of the patient. */
    private String address;

    /** Gender of the patient (e.g., "Male", "Female", "Other"). */
    private String gender;

    /** ReceptionistId that logins the patient */
    private int receptionistId;

    /**
     * Constructs a new Patient without an ID.
     * Used when registering a new patient (ID is assigned by the database).
     *
     * @param name    the patient's full name
     * @param dob     the patient's date of birth
     * @param phone   the patient's phone number
     * @param address the patient's address
     * @param gender  the patient's gender
     */
    public Patient(String name, LocalDate dob, String phone, String address, String gender) {
        this.name = name;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }

    /**
     * Default no-argument constructor.
     * Required for frameworks or manual field-by-field construction.
     */
    public Patient() {}

    /**
     * Constructs a Patient with all fields populated.
     * Used when loading an existing patient record from the database.
     *
     * @param patientId the patient's unique ID
     * @param name      the patient's full name
     * @param dob       the patient's date of birth
     * @param phone     the patient's phone number
     * @param address   the patient's address
     * @param gender    the patient's gender
     */
    public Patient(int patientId, String name, LocalDate dob, String phone, String address, String gender) {
        this.patientId = patientId;
        this.name = name;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }

    /**
     * Constructs a Patient with all fields populated.
     * Used when loading an existing patient record from the database.
     *
     * @param name      the patient's full name
     * @param dob       the patient's date of birth
     * @param phone     the patient's phone number
     * @param address   the patient's address
     * @param gender    the patient's gender
     * @param receptionistId the id of the receptionist that logged into the system
     */
    public Patient(String name, LocalDate dob, String phone, String address, String gender, int receptionistId) {
        this.patientId = patientId;
        this.name = name;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.receptionistId = receptionistId;
    }

    /**
     * Returns the patient's unique ID.
     *
     * @return the patient ID
     */
    public int getId() { return patientId; }

    /**
     * Returns the receptionist's unique ID.
     *
     * @return the receptionist ID
     */
    public int    getReceptionistId() { return receptionistId; }

    /**
     * Returns the patient's unique ID.
     *
     * @return the patient ID
     */
    public void setReceptionistId(int receptionistId) { this.receptionistId = receptionistId; }

    /**
     * Returns the patient ID (alias for {@link #getId()}).
     *
     * @return the patient ID
     */
    public int getPatientId() { return patientId; }

    /**
     * Sets the patient's unique ID (typically assigned by the database).
     *
     * @param patientId the ID to assign
     */
    public void setPatientId(int patientId) {
        this.patientId = patientId; // BUG FIX: was `patientId++` which never assigned
    }

    /** @return the patient's full name */
    public String getName() { return name; }

    /** @param name the new name */
    public void setName(String name) { this.name = name; }

    /** @return the patient's date of birth */
    public LocalDate getDob() { return dob; }

    /** @param dob the new date of birth */
    public void setDob(LocalDate dob) { this.dob = dob; }

    /** @return the patient's phone number */
    public String getPhone() { return phone; }

    /** @param phone the new phone number */
    public void setPhone(String phone) { this.phone = phone; }

    /** @return the patient's address */
    public String getAddress() { return address; }

    /** @param address the new address */
    public void setAddress(String address) { this.address = address; }

    /** @return the patient's gender */
    public String getGender() { return gender; }

    /** @param gender the new gender */
    public void setGender(String gender) { this.gender = gender; }

    /**
     * Returns a human-readable representation of the patient.
     * @return a string in the format "Name (ID: X)"
     */
    @Override
    public String toString() {
        return name + " (ID: " + patientId + ")";
    }

}