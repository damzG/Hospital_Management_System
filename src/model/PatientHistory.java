package model;

import java.time.LocalDate;

public class PatientHistory {
    private int historyId;
    private int patientId;
    private LocalDate visitDate;
    private String diagnosis;
    private String notes;

    public PatientHistory(int id, int patientId, LocalDate visitDate, String diagnosis, String notes){
        this.historyId = id;
        this.patientId = patientId;
        this.visitDate = visitDate;
        this.diagnosis = diagnosis;
        this.notes = notes;
    }


    public int getPatient() {
        return patientId;
    }

    public void setPatient(int patientId) {
        this.patientId = patientId;
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

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }
}
