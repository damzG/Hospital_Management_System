package model;

import java.time.LocalDate;

public class Prescription {

    private int prescriptionId;   // DB-generated
    private int patientId;
    private int doctorId;
    private LocalDate date;
    private String diagnosis;
    private String notes;

    // Constructor for CREATE
    public Prescription(int patientId, int doctorId,
                        LocalDate date, String diagnosis, String notes) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.diagnosis = diagnosis;
        this.notes = notes;
    }

    // Getters
    public int getPatientId() { return patientId; }
    public int getDoctorId() { return doctorId; }
    public LocalDate getDate() { return date; }
    public String getDiagnosis() { return diagnosis; }
    public String getNotes() { return notes; }
}
