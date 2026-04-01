package model;

import java.time.LocalDate;

/**
 * Represents a scheduled appointment between a patient and a doctor.
 * Each appointment has a status (defaulting to "ACTIVE") to prevent double-booking.
 */
public class Appointment {

    /** Unique identifier for this appointment (DB-generated). */
    private int appointmentId;

    /** The ID of the patient attending this appointment. */
    private int patientId;

    /** The ID of the doctor assigned to this appointment. */
    private int doctorId;

    /** The date on which this appointment is scheduled. */
    private LocalDate appointment_date;

    /**
     * The current status of this appointment.
     * Possible values: "ACTIVE", "CANCELLED", "COMPLETED".
     */
    private String status;

    /** The time slot reserved for this appointment (e.g., "09:00-09:30"). */
    private String timeSlot;

    /**
     * Constructs a new Appointment with the given patient, doctor, date, and time slot.
     * The status is automatically set to "ACTIVE" to prevent double-booking.
     *
     * @param patient  the ID of the patient
     * @param doctor   the ID of the doctor
     * @param appDate  the scheduled date of the appointment
     * @param timeSlot the reserved time slot (e.g., "09:00-09:30")
     */
    public Appointment(int patient, int doctor, LocalDate appDate, String timeSlot) {
        this.patientId = patient;
        this.doctorId = doctor;
        this.appointment_date = appDate;
        this.status = "ACTIVE";
        this.timeSlot = timeSlot;
    }

    /**
     * Returns the unique appointment ID.
     *
     * @return the appointment ID
     */
    public int getAppointmentId() { return appointmentId; }

    /**
     * Sets the appointment ID (typically assigned by the database).
     *
     * @param appointmentId the ID to assign
     */
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    /**
     * Returns the ID of the patient for this appointment.
     *
     * @return the patient ID
     */
    public int getPatientID() { return patientId; }

    /**
     * Updates the patient associated with this appointment.
     *
     * @param patient the new patient ID
     */
    public void setPatient(int patient) { this.patientId = patient; }

    /**
     * Returns the ID of the doctor for this appointment.
     *
     * @return the doctor ID
     */
    public int getDoctorID() { return doctorId; }

    /**
     * Updates the doctor associated with this appointment.
     *
     * @param doctor the new doctor ID
     */
    public void setDoctorId(int doctor) { this.doctorId = doctor; }

    /**
     * Returns the scheduled date of the appointment.
     *
     * @return the appointment date
     */
    public LocalDate getAppointment_date() { return appointment_date; }

    /**
     * Updates the scheduled date of the appointment.
     *
     * @param appointment_date the new appointment date
     */
    public void setAppointment_date(LocalDate appointment_date) { this.appointment_date = appointment_date; }

    /**
     * Returns the current status of the appointment.
     *
     * @return the status string (e.g., "ACTIVE", "CANCELLED", "COMPLETED")
     */
    public String getStatus() { return status; }

    /**
     * Updates the status of the appointment.
     *
     * @param status the new status (e.g., "CANCELLED", "COMPLETED")
     */
    public void setStatus(String status) { this.status = status; }

    /**
     * Returns the reserved time slot for this appointment.
     *
     * @return the time slot string (e.g., "09:00-09:30")
     */
    public String getTimeSlot() { return timeSlot; }

    /**
     * Updates the time slot for this appointment.
     *
     * @param timeSlot the new time slot
     */
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }
}