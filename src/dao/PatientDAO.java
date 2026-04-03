//Oyindamola Olaosun C00313475 OOSD Project

package dao;

import model.Patient;
import util.DBconnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    /**
     * Inserts a new patient into the database.
     * receptionist_id is stored as a FK from the logged-in receptionist.
     *
     * @param patient the patient to insert
     * @throws SQLException if a database error occurs
     */
    public static void addPatient(Patient patient) throws SQLException {
        String sql = """
            INSERT INTO patient (name, dob, phone, address, gender, receptionist_id)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getName());
            stmt.setDate(2,   Date.valueOf(patient.getDob()));
            stmt.setString(3, patient.getPhone());
            stmt.setString(4, patient.getAddress());
            stmt.setString(5, patient.getGender());
            stmt.setInt(6,    patient.getReceptionistId()); // int — no object needed

            int rows = stmt.executeUpdate();
            System.out.println(rows + " patient inserted successfully.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves and prints a single active patient by ID.
     *
     * @param id the patient ID to look up
     * @throws SQLException if a database error occurs
     */
    public static void getPatientById(int id) throws SQLException {
        String sql = "SELECT * FROM patient WHERE patient_id = ? AND status = 'ACTIVE'";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.printf("%d | %s | %s | %s | %s%n",
                            rs.getInt("patient_id"),
                            rs.getString("name"),
                            rs.getDate("dob").toLocalDate(),
                            rs.getString("phone"),
                            rs.getString("address")
                    );
                } else {
                    System.out.println("Patient not found.");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves all active patients from the database.
     *
     * @return a list of active patients
     * @throws SQLException if a database error occurs
     */
    public static List<Patient> getAllActivePatients() throws SQLException {
        List<Patient> patientList = new ArrayList<>();
        String sql = "SELECT * FROM patient WHERE status = 'ACTIVE'";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                patientList.add(new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("gender")// now matches the int constructor
                ));
            }

            return patientList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates a patient's home address.
     *
     * @param id         the patient ID to update
     * @param newAddress the new address
     */
    public static void updatePatientAddress(int id, String newAddress) {
        String sql = "UPDATE patient SET address = ? WHERE patient_id = ?";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newAddress);
            stmt.setInt(2, id);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " patient updated.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deactivates a patient (soft delete).
     * Sets status to INACTIVE rather than removing the record.
     *
     * @param id the patient ID to deactivate
     */
    public static void deactivatePatient(int id) {
        String sql = "UPDATE patient SET status = 'INACTIVE' WHERE patient_id = ?";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " patient deactivated.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}