package model;

import java.time.LocalDate;

public class Prescription {
    private int prescription_id;
    private Patient patient;
    private Doctor doctor;
    private LocalDate entryDate;
    private String notes;

    public Prescription(int id, Patient patient, Doctor doctor, LocalDate entryDate, String notes){
        this.prescription_id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.entryDate = entryDate;
        this.notes = notes;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
