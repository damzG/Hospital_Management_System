//Oyindamola Olaosun C00313475 OOSD Project

package dao;

import model.PatientHistory;
import model.Prescription;
import util.DBconnect;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientHistoryDAO {

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

    public static List<Prescription> getHistoryByPatientId(int patientId) throws SQLException {

        List<Prescription> list = new ArrayList<>();

        String sql = """
            SELECT p.entry_date,
                   d.name AS doctor_name,
                   p.diagnosis,
                   p.notes
            FROM prescription p
            INNER JOIN doctor d 
                ON p.doctor_id = d.doctor_id
            WHERE p.patient_id = ?
            ORDER BY p.entry_date DESC
            """;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                            Prescription pres = new Prescription(
                                    patientId,
                                    0,
                                    rs.getDate("entry_date").toLocalDate(),
                                    rs.getString("diagnosis"),
                                    rs.getString("notes")
                            );
                            list.add(pres);
                }
            }
            return list;
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
            throw e;
        }
    }


    public static void deactivateHistory(int historyId) {

        String sql = "UPDATE patient_history SET status = 'INACTIVE' WHERE history_id = ?";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, historyId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



