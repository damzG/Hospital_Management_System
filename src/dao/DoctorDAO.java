package dao;

import model.Doctor;
import util.DBconnect;

import java.sql.*;

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
            System.out.println(rows + " patient inserted successfully.");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Read or retrieve doctor details
    public static void getDoctorById(int id) throws SQLException {

        String sql = "SELECT * FROM doctor WHERE doctor_id = ?";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery();){
                if (rs.next()) {
                    System.out.println(
                            rs.getInt("doctor_id") + " | " +
                                    rs.getString("name") + " | " +
                                    rs.getString("specialization") );
                }
                else {
                    System.out.println("Doctor not found.");
                }
            }

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

    //Remove Doctor -> Doctor leaves of gets fired
    public static void removeDoctor(int id){
        String sql = "DELETE FROM doctor WHERE doctor_id = ?";

        try(Connection conn = DBconnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " patient deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
