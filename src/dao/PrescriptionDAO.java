package dao;

import util.DBconnect;

import java.sql.*;
import java.time.LocalDate;

public class PrescriptionDAO {
    //CRUD

    //Create Presciption Data
    public static void addPrescription(
            LocalDate dateValue,
            int patientId,
            int doctorId
    ) {

        String sql = """
        INSERT INTO appointment
        ( patient_id, doctor_id, entryDate, notes)
        VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientId);
            stmt.setInt(2, doctorId);
            stmt.setTimestamp(3, Timestamp.valueOf(dateValue.atStartOfDay()));
            stmt.setString(4, "Antibiotics is ready for use");

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void retrievePrescription(int prescription_id){
        String sql = """
        SELECT pr.prescription_id,
               pr.entryDate,
               pr.notes,
               p.name AS patient_name,
               d.name AS doctor_name
        FROM prescription pr
        JOIN patient p ON pr.patient_id = p.patient_id
        JOIN doctor d ON pr.doctor_id = d.doctor_id
        WHERE pr.prescription_id = ?
        """;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prescription_id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(
                            rs.getInt("prescription_id") + " | " +
                                    rs.getString("patient_name") + " | " +
                                    rs.getString("doctor_name") + " | " +
                                    rs.getTimestamp("Entry Date: ") + " | " +
                                    rs.getString("Prescription Notes")
                    );
                } else {
                    System.out.println("Prescription not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePrescriptionNotes(
            int prescriptionId,
            String newNotes
    ) {

        String sql = """
        UPDATE prescription
        SET notes = ?
        WHERE prescription_id = ?
        """;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newNotes);
            stmt.setInt(2, prescriptionId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void removePrescription(int prescription_id){

            String sql = "DELETE FROM prescription WHERE prescription_id = ?";

            try (Connection conn = DBconnect.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, prescription_id);

                int rows = stmt.executeUpdate();

                if (rows > 0) {
                    System.out.println("Prescription data deleted successfully.");
                } else {
                    System.out.println("Prescription data not found.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }


}
