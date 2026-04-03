//Oyindamola Olaosun C00313475 OOSD Project

package model;

import java.time.LocalDate;

/**
 * Represents a historical visit record for a patient.
 * Captures the diagnosis and clinical notes from a specific visit date.
 */
public class PatientHistory {

    /** Unique identifier for this history record (DB-generated). */
    private int historyId;

    /** The ID of the patient this history record belongs to. */
    private int patientId;

    /** The date of the patient's visit. */
    private LocalDate visitDate;

    /** The diagnosis recorded during the visit. */
    private String diagnosis;

    /** Additional clinical notes from the visit. */
    private String notes;

    /**
     * Constructs a PatientHistory record with all fields.
     *
     * @param id        the unique history record ID
     * @param patientId the ID of the patient
     * @param visitDate the date of the visit
     * @param diagnosis the diagnosis made during the visit
     * @param notes     additional clinical notes
     */
    public PatientHistory(int id, int patientId, LocalDate visitDate, String diagnosis, String notes) {
        this.historyId = id;
        this.patientId = patientId;
        this.visitDate = visitDate;
        this.diagnosis = diagnosis;
        this.notes = notes;
    }

    /** @return the history record ID */
    public int getHistoryId() { return historyId; }

    /** @param historyId the new history record ID */
    public void setHistoryId(int historyId) { this.historyId = historyId; }

    /** @return the patient ID associated with this record */
    public int getPatient() { return patientId; }

    /** @param patientId the new patient ID */
    public void setPatient(int patientId) { this.patientId = patientId; }

    /** @return the date of the visit */
    public LocalDate getVisitDate() { return visitDate; }

    /** @param visitDate the new visit date */
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

    /** @return the diagnosis recorded during the visit */
    public String getDiagnosis() { return diagnosis; }

    /** @param diagnosis the new diagnosis */
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    /** @return additional clinical notes from the visit */
    public String getNotes() { return notes; }

    /** @param notes the new notes */
    public void setNotes(String notes) { this.notes = notes; }
}