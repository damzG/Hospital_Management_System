package model;

import java.time.LocalDate;

public class PatientHistory {
    private int historyId;
    private Patient patient;
    private LocalDate visitDate;
    private String diagnosis;
    private String notes;

    public PatientHistory(int id, Patient patient, LocalDate visitDate, String diagnosis, String notes){
        this.historyId = id;
        this.patient = patient;
        this.visitDate = visitDate;
        this.diagnosis = diagnosis;
        this.notes = notes;
    }


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
