//Oyindamola Olaosun C00313475 OOSD Project

package dao;

import model.Receptionist;

import java.sql.*;

import util.DBconnect;

/**
 * Data Access Object for the {@code receptionist} table.
 * Handles all CRUD operations for Receptionist records.
 */

public class ReceptionistDAO {

    /**
     * Validates receptionist login credentials against the database.
     * Returns the matching Receptionist object on success, or null if
     * the username/password combination is not found.
     *
     * @param username the entered username
     * @param password the entered password
     * @return the authenticated {@link Receptionist}, or {@code null} if invalid
     */

    public static Receptionist login(String username, String password) {
        String sql = "SELECT * FROM receptionist WHERE username = ? AND password = ?";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Receptionist(
                            rs.getInt("receptionist_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("username"),
                            rs.getString("password")
                    );
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Login error: " + e.getMessage(), e);
        }

        return null; // credentials not found
    }
}
