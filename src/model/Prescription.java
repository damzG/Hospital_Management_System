//Oyindamola Olaosun C00313475 OOSD Project

package model;

import java.time.LocalDate;

/**
 * Represents a medical prescription issued by a doctor to a patient.
 * Contains the diagnosis summary and any additional clinical notes.
 */
public class Prescription {

    /** Unique identifier for this prescription (DB-generated). */
    private int prescriptionId;

    /** The ID of the patient receiving the prescription. */
    private int patientId;

    /** The ID of the doctor issuing the prescription. */
    private int doctorId;

    /** The date the prescription was issued. */
    private LocalDate date;

    /** The diagnosis associated with this prescription. */
    private String diagnosis;

    /** Additional notes or instructions on the prescription. */
    private String notes;

    /**
     * Constructs a new Prescription for insertion into the database.
     * The prescription ID is assigned by the database upon saving.
     *
     * @param patientId the ID of the patient
     * @param doctorId  the ID of the prescribing doctor
     * @param date      the date the prescription is issued
     * @param diagnosis the diagnosis for which the prescription is given
     * @param notes     any additional instructions or notes
     */
    public Prescription(int patientId, int doctorId, LocalDate date, String diagnosis, String notes) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.diagnosis = diagnosis;
        this.notes = notes;
    }

    /** @return the patient ID */
    public int getPatientId() { return patientId; }

    /** @return the doctor ID */
    public int getDoctorId() { return doctorId; }

    /** @return the date the prescription was issued */
    public LocalDate getDate() { return date; }

    /** @return the diagnosis associated with this prescription */
    public String getDiagnosis() { return diagnosis; }

    /** @return additional notes on the prescription */
    public String getNotes() { return notes; }
}