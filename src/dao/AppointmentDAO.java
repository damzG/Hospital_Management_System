//Oyindamola Olaosun C00313475 OOSD Project

package dao;

import model.Appointment;
import util.DBconnect;

import java.sql.*;
import java.time.LocalDate;

public class AppointmentDAO {

    /**
     * Inserts a new appointment into the database.
     * Status is set to "BOOKED" on creation.
     *
     * @param app the appointment to insert
     */
    public static void addAppointment(Appointment app) {
        String sql = """
            INSERT INTO appointment
            (appointment_date, status, patient_id, doctor_id, time_slot, receptionist_id)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1,   Date.valueOf(app.getAppointment_date()));
            stmt.setString(2, "BOOKED");
            stmt.setInt(3,    app.getPatientID());
            stmt.setInt(4,    app.getDoctorID());
            stmt.setString(5, app.getTimeSlot());
            stmt.setInt(6,    app.getReceptionistId());

            int rows = stmt.executeUpdate();
            System.out.println(rows + " appointment booked ✓");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a doctor is free at a given date and time slot.
     * Checks against "BOOKED" status — not "ACTIVE" — to match addAppointment.
     *
     * @param doctorId the doctor to check
     * @param date     the appointment date
     * @param timeSlot the time slot to check
     * @return true if the doctor is available, false if already booked
     */
    public static boolean isDoctorAvailable(int doctorId, LocalDate date, String timeSlot) {
        String sql = """
            SELECT COUNT(*) FROM appointment
            WHERE doctor_id     = ?
            AND appointment_date = ?
            AND time_slot        = ?
            AND status           = 'BOOKED'
        """;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,    doctorId);
            stmt.setDate(2,   Date.valueOf(date));
            stmt.setString(3, timeSlot);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1) == 0; // 0 means no conflict — doctor is free
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves and prints a single appointment with patient and doctor names.
     *
     * @param appointmentId the appointment to look up
     */
    public static void getAppointmentById(int appointmentId) {
        String sql = """
            SELECT a.appointment_id,
                   a.appointment_date,
                   a.status,
                   a.time_slot,
                   p.name AS patient_name,
                   d.name AS doctor_name
            FROM appointment a
            JOIN patient p ON a.patient_id = p.patient_id
            JOIN doctor  d ON a.doctor_id  = d.doctor_id
            WHERE a.appointment_id = ?
        """;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointmentId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.printf("%d | %s | %s | %s | %s | %s%n",
                            rs.getInt("appointment_id"),
                            rs.getString("patient_name"),
                            rs.getString("doctor_name"),
                            rs.getDate("appointment_date"),
                            rs.getString("status"),
                            rs.getString("time_slot")
                    );
                } else {
                    System.out.println("Appointment not found.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reassigns an appointment to a different patient.
     *
     * @param appointmentId the appointment to update
     * @param newPatientId  the new patient ID
     */
    public static void updateAppointmentPatient(int appointmentId, int newPatientId) {
        String sql = "UPDATE appointment SET patient_id = ? WHERE appointment_id = ?";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newPatientId);
            stmt.setInt(2, appointmentId);

            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Appointment updated ✓" : "Appointment not found.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cancels an appointment by setting its status to CANCELLED.
     *
     * @param appointmentId the appointment to cancel
     */
    public static void deactivateAppointment(int appointmentId) {
        String sql = "UPDATE appointment SET status = 'CANCELLED' WHERE appointment_id = ?";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointmentId);

            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Appointment cancelled ✓" : "Appointment not found.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}