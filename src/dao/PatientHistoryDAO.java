package dao;


import model.PatientHistory;
import util.DBconnect;

import java.sql.*;
import java.time.LocalDate;public class PatientHistoryDAO {

    public static void addHistory(PatientHistory history) {

        String sql = """
            
                INSERT INTO patient_history (patient_id, visit_date, diagnosis, notes)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, history.getPatient());
            stmt.setDate(2, Date.valueOf(history.getVisitDate()));
            stmt.setString(3, history.getDiagnosis());
            stmt.setString(4, history.getNotes());

            int rows = stmt.executeUpdate();
            System.out.println(rows + " history record added.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getHistoryByPatientId(int patientId) {

        String sql = """
            SELECT * FROM patient_history
            WHERE patient_id = ?
            ORDER BY visit_date DESC
            """;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    System.out.println(
                            rs.getInt("history_id") + " | " +
                                    rs.getDate("visit_date").toLocalDate() + " | " +
                                    rs.getString("diagnosis") + " | " +
                                    rs.getString("notes")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void deleteHistory(int historyId) {

        String sql = "DELETE FROM patient_history WHERE history_id = ?";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, historyId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



