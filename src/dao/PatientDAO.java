package dao;

import model.Patient;

import java.sql.*;

import static util.DBconnect.*;


public class PatientDAO {
    private DriverManager DBConnection;
    //CRUD Template - Create, Read, Update Delete

    //Create patient data
    public void addPatient(Patient patient) throws SQLException {

        String sql = """
        INSERT INTO patient (name, dob, phone, address)
        VALUES (?, ?, ?, ?)
        """;


        Connection conn = DBConnection.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, patient.getName());
        stmt.setDate(2, Date.valueOf(patient.getDob()));
        stmt.setString(3, patient.getPhone());
        stmt.setString(4, patient.getAddress());

        stmt.executeUpdate();
    }

    //Read or retrieve patient details
    public Patient getPatientById(int id) throws SQLException {

        String sql = "SELECT * FROM patient WHERE patient_id = ?";

        Connection conn = DBConnection.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Patient p = new Patient();
            p.setPatientId(rs.getInt("patient_id"));
            p.setName(rs.getString("name"));
            p.setDob(rs.getDate("dob").toLocalDate());
            p.setPhone(rs.getString("phone"));
            p.setAddress(rs.getString("address"));
            return p;
        }

        return null;
    }

    //Next is to be able to update patient details and delete patient details

}
