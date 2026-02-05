package dao;

import model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBconnect;


import static util.DBconnect.*;


public class PatientDAO {
    //CRUD Template - Create, Read, Update Delete

    //Create patient data
    public static void addPatient(Patient patient) throws SQLException {

        String sql = """
        INSERT INTO patient (name, dob, phone, address, gender)
        VALUES (?, ?, ?, ?, ?)
        """;


       try(Connection conn = DBconnect.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);){
           stmt.setString(1, patient.getName());
           stmt.setDate(2, Date.valueOf(patient.getDob()));
           stmt.setString(3, patient.getPhone());
           stmt.setString(4, patient.getAddress());
           stmt.setString(5, patient.getGender());

           int rows = stmt.executeUpdate();
           System.out.println(rows + " patient inserted successfully.");


       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    //Read or retrieve patient details
    public static void getPatientById(int id) throws SQLException {

        String sql = "SELECT * FROM patient WHERE patient_id = ? AND status = 'ACTIVE'";

        try (Connection conn = DBconnect.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
            ){

            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery();){
                if (rs.next()) {
                    System.out.println(
                                    rs.getInt("patient_id") + " | " +
                                    rs.getString("name") + " | " +
                                    rs.getDate("dob").toLocalDate() + " | " +
                                    rs.getString("phone") + " | " +
                                    rs.getString("address") );
                }
                else {
                    System.out.println("Patient not found.");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    Patient List
    public static List<Patient> getAllActivePatients()throws  SQLException{


        List<Patient> patientList = new ArrayList<>();
        String sql = "SELECT * FROM patient WHERE status = 'ACTIVE'";

        try(Connection conn = DBconnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()){
        while(rs.next()){
            patientList.add(new Patient(
                    rs.getInt("patient_id"),
                    rs.getString("name"),
                    rs.getDate("dob").toLocalDate(),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("gender")
            ));
        }
            return patientList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //update patient details e.g Patient address

    public static void updatePatientAddress(int id, String newAddress){

        String sql = "UPDATE patient SET address = ? WHERE patient_id = ?";

        try(
                Connection conn = DBconnect.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
                ){
            stmt.setString(1, newAddress);
            stmt.setInt(2, id);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Instead of delete the patient, set another column in the table as Status - active/inactive
    public static void deactivatePatient(int id){
        String sql = "UPDATE patient SET status='INACTIVE' WHERE patient_id=?";

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
