package dao;

import model.Appointment;
import util.DBconnect;

import java.sql.*;
import java.time.LocalDate;

public class AppointmentDAO {

    public static void addAppointment(
            Appointment app
    ) {

        String sql = """
        INSERT INTO appointment
        (appointment_date, status, patient_id, doctor_id, time_slot)
        VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(app.getAppointment_date()));
            stmt.setString(2, "BOOKED");
            stmt.setInt(3, app.getPatientID());
            stmt.setInt(4, app.getDoctorID());
            stmt.setString(5, app.getTimeSlot());

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
               a.time_slot,
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
                                    rs.getString("status") + " | " +
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


    public static void deactivateAppointment(int appointmentId) {

        String sql = "UPDATE appointment SET status = 'INACTIVE' WHERE appointment_id = ?";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointmentId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Appointment deleted successfully. (Inactive)");
            } else {
                System.out.println("Appointment not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isDoctorAvailable(
            int doctorId,
            LocalDate date,
            String timeSlot
    ){
        String sql = """
                SELECT COUNT(*) FROM appointment
                WHERE doctor_id = ?
                AND appointment_date = ?
                AND time_slot = ?
                AND status = 'ACTIVE'
                """;

        try(Connection conn = DBconnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, doctorId);
            stmt.setDate(2, Date.valueOf(date));
            stmt.setString(3, timeSlot);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) == 0;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }



}
