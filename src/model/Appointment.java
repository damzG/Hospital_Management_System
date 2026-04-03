//Oyindamola Olaosun C00313475 OOSD Project

package model;

import java.time.LocalDate;

/**
 * Represents a scheduled appointment between a patient and a doctor.
 * Links back to the receptionist who booked it via receptionistId.
 */
public class Appointment {

    private int appointmentId;
    private int patientId;
    private int doctorId;
    private int receptionistId; // FK — receptionist who booked this appointment
    private LocalDate appointment_date;
    private String status;
    private String timeSlot;

    /**
     * Constructor for booking a new appointment.
     *
     * @param patientId      the ID of the patient
     * @param doctorId       the ID of the doctor
     * @param appDate        the scheduled date
     * @param timeSlot       the reserved time slot
     * @param receptionistId the ID of the receptionist making the booking
     */
    public Appointment(int patientId, int doctorId, LocalDate appDate,
                       String timeSlot, int receptionistId) {
        this.patientId      = patientId;
        this.doctorId       = doctorId;
        this.appointment_date = appDate;
        this.status         = "BOOKED";
        this.timeSlot       = timeSlot;
        this.receptionistId = receptionistId;
    }

    public int getAppointmentId()       { return appointmentId; }
    public void setAppointmentId(int id){ this.appointmentId = id; }
    public int getPatientID()           { return patientId; }
    public void setPatient(int patient) { this.patientId = patient; }
    public int getDoctorID()            { return doctorId; }
    public void setDoctorId(int doctor) { this.doctorId = doctor; }
    public int getReceptionistId()      { return receptionistId; }
    public void setReceptionistId(int r){ this.receptionistId = r; }
    public LocalDate getAppointment_date()              { return appointment_date; }
    public void setAppointment_date(LocalDate date)     { this.appointment_date = date; }
    public String getStatus()           { return status; }
    public void setStatus(String status){ this.status = status; }
    public String getTimeSlot()         { return timeSlot; }
    public void setTimeSlot(String t)   { this.timeSlot = t; }
}