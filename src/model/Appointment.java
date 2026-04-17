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
    /**
     * getter for AppointmentId
     * @return the appointment ID
     * **/
    public int getAppointmentId()       { return appointmentId; }

    /**
     * setter for AppointmentId
     * @param  id - appointment id
     * **/
    public void setAppointmentId(int id){ this.appointmentId = id; }

    /**
     * getter for PatientId
     * @return patientId
     * **/
    public int getPatientID()           { return patientId; }

    /**
     * setter for Patient object
     *
     * **/
    public void setPatient(int patient) { this.patientId = patient; }

    /**
     * getter for DoctorID
     * **/
    public int getDoctorID()            { return doctorId; }

    /**
     * setter for DoctorID
     * **/
    public void setDoctorId(int doctor) { this.doctorId = doctor; }

    /**
     * getter for ReceptionistId
     * **/
    public int getReceptionistId()      { return receptionistId; }

    /**
     * setter for ReceptionistId
     * **/
    public void setReceptionistId(int r){ this.receptionistId = r; }

    /**
     * getter for Appointment Date
     * **/
    public LocalDate getAppointment_date()              { return appointment_date; }

    /**
     * setter for Appointment Date
     * **/
    public void setAppointment_date(LocalDate date)     { this.appointment_date = date; }

    /**
     * getter for Status
     * **/
    public String getStatus()           { return status; }

    /**
     * setter for Status
     * **/
    public void setStatus(String status){ this.status = status; }

    /**
     * getter for TimeSlot
     * **/
    public String getTimeSlot()         { return timeSlot; }

    /**
     * setter for TimeSlot object
     * **/
    public void setTimeSlot(String t)   { this.timeSlot = t; }
}