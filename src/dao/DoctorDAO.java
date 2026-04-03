package dao;

import model.Doctor;
import util.DBconnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

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
