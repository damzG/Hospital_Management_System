//Oyindamola Olaosun C00313475 OOSD Project

package dao;

import model.Prescription;
import util.DBconnect;

import java.sql.*;

/**
 * Data Access Object for the {@code prescription} table.
 * Handles all CRUD operations for Prescription records.
 * Prescriptions are soft-deleted by setting status to {@code INACTIVE}.
 */

public class PrescriptionDAO {
    //CRUD
    //Create Prescription Data

    /**
     * Inserts a new prescription record into the database.
     * The {@code prescription_id} is assigned automatically by the database.
     *
     * @param p the {@link Prescription} object containing patient ID, doctor ID,
     *          date, diagnosis, and notes
     * @throws SQLException if a database error occurs during insert
     */

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

    /**
     * Retrieves and prints a single prescription by its ID.
     * Joins the {@code patient} and {@code doctor} tables to display
     * names instead of raw foreign key IDs.
     *
     * @param prescription_id the ID of the prescription to retrieve
     */

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

    /**
     * Updates the clinical notes on an existing prescription.
     *
     *
     * @param prescriptionId the ID of the prescription to update
     * @param newNotes       the revised notes to store
     */
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

    /**
     * Deactivates a prescription by setting its status to {@code INACTIVE}.
     * This is a soft delete — the record remains in the database for
     * audit and medical history purposes.
     *
     * @param prescription_id the ID of the prescription to deactivate
     */
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
