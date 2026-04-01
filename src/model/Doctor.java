package model;

/**
 * Represents a doctor in the hospital management system.
 * Stores identification, name, and medical specialization.
 */
public class Doctor {

    /** Unique identifier for this doctor (DB-generated). */
    private int doctorId;

    /** Full name of the doctor. */
    private String name;

    /** Medical specialization of the doctor (e.g., "Cardiology", "Neurology"). */
    private String specialization;

    /**
     * Constructs a new Doctor with a name and specialization.
     * Used when inserting a new doctor (ID is assigned by the database).
     *
     * @param name           the doctor's full name
     * @param specialization the doctor's medical specialization
     */
    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    /**
     * Constructs a Doctor with all fields populated.
     * Used when retrieving an existing doctor from the database.
     *
     * @param doctorId the doctor's unique ID
     * @param name     the doctor's full name
     * @param spec     the doctor's medical specialization
     */
    public Doctor(int doctorId, String name, String spec) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = spec;
    }

    /**
     * Returns the doctor's unique ID.
     *
     * @return the doctor ID
     */
    public int getId() { return doctorId; }

    /**
     * Returns the doctor's full name.
     *
     * @return the name
     */
    public String getName() { return name; }

    /**
     * Updates the doctor's full name.
     *
     * @param name the new name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the doctor's medical specialization.
     *
     * @return the specialization
     */
    public String getSpecialization() { return specialization; }

    /**
     * Updates the doctor's medical specialization.
     *
     * @param specialization the new specialization
     */
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    /**
     * Returns a human-readable string representation of the doctor.
     *
     * @return a string in the format "Name (ID)"
     */
    @Override
    public String toString() {
        return name + " (" + doctorId + ")";
    }
}