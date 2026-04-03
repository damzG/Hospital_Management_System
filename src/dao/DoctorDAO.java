//Oyindamola Olaosun C00313475 OOSD Project

package dao;

import model.Doctor;
import util.DBconnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for the {@code doctor} table.
 * Handles all CRUD operations for Doctor records.
 * Doctors are never hard-deleted — they are deactivated (soft delete)
 * so appointment and prescription history is preserved.
 */


public class DoctorDAO {

    /**
     * Inserts a new doctor record into the database.
     * The {@code doctor_id} is assigned automatically by the database.
     *
     * @param doctor the {@link Doctor} object containing name and specialization
     * @throws RuntimeException if a database error occurs during insert
     */

    //Create Doctor data/details
    public static void addDoctor(Doctor doctor) throws SQLException {
            String sql = """
                   INSERT INTO doctor(name, specialization)
                   VALUES(?, ?);
                    """;


        try(Connection conn = DBconnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialization());

            int rows = stmt.executeUpdate();
            System.out.println(rows + " doctor inserted successfully.");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Retrieves all doctors with a status of {@code ACTIVE} from the database.
     * Uses {@code while(rs.next())} to ensure all rows are collected,
     * not just the first.
     *
     * @return a {@link List} of active {@link Doctor} objects,
     *         or an empty list if none are found
     * @throws RuntimeException if a database error occurs during retrieval
     */

    //Read or retrieve doctor details
    public static List<Doctor> getAllActiveDoctors() throws SQLException {
        List<Doctor> doctorList = new ArrayList<>();
        String sql = "SELECT * FROM doctor WHERE status = 'ACTIVE'";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            try(ResultSet rs = stmt.executeQuery();){
                while (rs.next()) {
                    doctorList.add(new Doctor(
                            rs.getInt("doctor_id"),
                            rs.getString("name"),
                            rs.getString("specialization")
                    ));
                }
                if (doctorList.isEmpty()){
                    System.out.println("No active doctors found.");
                }
            }
            return doctorList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the medical specialization of an existing doctor.
     *
     * @param id      the {@code doctor_id} of the doctor to update
     * @param newSpec the new specialization string (e.g. "Neurology")
     * @throws RuntimeException if a database error occurs during update
     */

    //Update Doctor specialization
    public static void updateDoctorSpecialization(int id, String newSpec){

        String sql = "UPDATE doctor SET specialization = ? WHERE doctor_id = ?";

        try(
                Connection conn = DBconnect.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1, newSpec);
            stmt.setInt(2, id);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deactivates a doctor by setting their status to {@code INACTIVE}.
     * This is a soft delete — the doctor's record and all associated
     * appointments and prescriptions remain in the database for historical
     * reference. The doctor will no longer appear in active listings.
     *
     * @param id the {@code doctor_id} of the doctor to deactivate
     * @throws RuntimeException if a database error occurs during the update
     */

    //Deactivate Doctor -> Doctor leaves of gets fired but history is still left in the DB
    public static void removeDoctor(int id){
        String sql = "UPDATE doctor SET status = 'INACTIVE' WHERE doctor_id = ?";

        try(Connection conn = DBconnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " patient deleted successfully. (Inactive)");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
