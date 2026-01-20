package dao;

import model.Receptionist;
import util.DBconnect;
import util.PasswordUtil;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class ReceptionistDAO {

    //Register new receptionist
    public static void addReceptionist(Receptionist rec) throws SQLException {
        String sql = """
        INSERT INTO receptionist (name, email, phone, username, password, salt)
        VALUES (?, ?, ?, ?, ?)
        """;

        try(Connection conn = DBconnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){
            stmt.setString(1, rec.getName());
            stmt.setString(2, rec.getEmail());
            stmt.setString(3, rec.getPhone());
            stmt.setString(4, rec.getUsername());
            byte[] salt = PasswordUtil.generateSalt();
            String hashedPassword = PasswordUtil.hashPassword(
                    rec.getPassword().toCharArray(), salt
            );

            //Trial Hashing
            stmt.setString(5, hashedPassword);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " Receptionist newly registered ✓");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //retrieve receptionist
    //Read or retrieve patient details
    public static void getReceptionistById(int id) throws SQLException {

        String sql = "SELECT * FROM receptionist WHERE receptionistId= ?";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
        ){

            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery();){
                if (rs.next()) {
                    System.out.println(
                            rs.getInt("receptionist_id") + " | " +
                                    rs.getString("name") + " | " +
                                    rs.getString("email") + " | " +
                                    rs.getString("phone") + " | " +
                                    rs.getString("username") + " | " +
                                    rs.getString("password"));
                }
                else {
                    System.out.println("Patient not found.");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        //Update receptionist password incase of forgotten password
        public static void updateReceptionistPassword(int id, String newPassword){

            String sql = "UPDATE receptionist SET password = ?, salt = ? WHERE receptionist_id = ?";

            try(
                    Connection conn = DBconnect.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql)
            ){

                byte[] salt = PasswordUtil.generateSalt();
                String newHashedPassword = PasswordUtil.hashPassword(
                        newPassword.toCharArray(), salt
                );

                stmt.setString(1, newHashedPassword);
                stmt.setInt(2, id);

                int rows = stmt.executeUpdate();
                System.out.println(rows + " updated");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        //Resigned Receptionist
    public static void removeReceptionist(int id){
        String sql = "DELETE FROM receptionist WHERE receptionist_id = ?";

        try(Connection conn = DBconnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " receptionist deleted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
