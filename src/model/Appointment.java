package model;

import java.time.LocalDate;

public class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private LocalDate appointment_date;
    private String status;
    private String timeSlot;

    public Appointment( int patient, int doctor, LocalDate appDate, String timeSlot){
        this.patientId = patient;
        this.doctorId = doctor;
        this.appointment_date = appDate;
        this.status = "ACTIVE"; //prevent double booking
        this.timeSlot = timeSlot;
    }


    public int getPatientID() {
        return patientId;
    }

    public void setPatient(int patient) {
        this.patientId = patient;
    }

    public int getDoctorID() {
        return doctorId;
    }

    public void setDoctorId(int doctor) {
        this.doctorId = doctor;
    }

    public LocalDate getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(LocalDate appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}
