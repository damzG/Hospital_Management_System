package dao;

import model.Prescription;
import util.DBconnect;

import java.sql.*;
import java.time.LocalDate;

public class PrescriptionDAO {
    //CRUD

    //Create Prescription Data

    public static void addPrescription(Prescription p) throws SQLException {

        String sql = """
            INSERT INTO prescription
            (patient_id, doctor_id, entry_date, diagnosis, notes)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getPatientId());
            stmt.setInt(2, p.getDoctorId());
            stmt.setDate(3, java.sql.Date.valueOf(p.getDate()));
            stmt.setString(4, p.getDiagnosis());
            stmt.setString(5, p.getNotes());

            stmt.executeUpdate();
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


    public static void deactivatePrescription(int prescription_id){

            String sql = "UPDATE prescription SET status = 'INACTIVE' WHERE prescription_id = ?";

            try (Connection conn = DBconnect.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, prescription_id);

                int rows = stmt.executeUpdate();

                if (rows > 0) {
                    System.out.println("Prescription data deleted successfully. (Inactive)");
                } else {
                    System.out.println("Prescription data not found.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
