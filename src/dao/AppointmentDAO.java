package dao;

import util.DBconnect;

import java.sql.*;
import java.time.LocalDate;

public class AppointmentDAO {

    public static void addAppointment(
            LocalDate dateValue,
            int patientId,
            int doctorId
    ) {

        String sql = """
        INSERT INTO appointment
        (appointment_date, status, patient_id, doctor_id)
        VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(dateValue.atStartOfDay()));
            stmt.setString(2, "BOOKED");
            stmt.setInt(3, patientId);
            stmt.setInt(4, doctorId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    Retrieve appointments
    public static void getAppointmentById(int appointmentId) {

        String sql = """
        SELECT a.appointment_id,
               a.appointment_date,
               a.status,
               p.name AS patient_name,
               d.name AS doctor_name
        FROM appointment a
        JOIN patient p ON a.patient_id = p.patient_id
        JOIN doctor d ON a.doctor_id = d.doctor_id
        WHERE a.appointment_id = ?
        """;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointmentId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(
                            rs.getInt("appointment_id") + " | " +
                                    rs.getString("patient_name") + " | " +
                                    rs.getString("doctor_name") + " | " +
                                    rs.getTimestamp("appointment_date") + " | " +
                                    rs.getString("status")
                    );
                } else {
                    System.out.println("Appointment not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Update Appointment Details for new patient
    public static void updateAppointmentPatient(
            int appointmentId,
            int newPatientId
    ) {

        String sql = """
        UPDATE appointment
        SET patient_id = ?
        WHERE appointment_id = ?
        """;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newPatientId);
            stmt.setInt(2, appointmentId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Appointment patient updated successfully.");
            } else {
                System.out.println("Appointment not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteAppointment(int appointmentId) {

        String sql = "DELETE FROM appointment WHERE appointment_id = ?";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointmentId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Appointment deleted successfully.");
            } else {
                System.out.println("Appointment not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
