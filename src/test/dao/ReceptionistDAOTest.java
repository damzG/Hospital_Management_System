//Oyindamola Olaosun C00313475 OOSD Project

package test.dao;

import dao.ReceptionistDAO;
import model.Receptionist;
import org.junit.jupiter.api.*;
import util.DBconnect;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for ReceptionistDAO.
 */
class ReceptionistDAOTest {

    private Connection conn;

    @BeforeEach
    void setUp() throws SQLException {
        conn = DBconnect.getConnection();
        conn.setAutoCommit(false);
    }

    @AfterEach
    void tearDown() throws SQLException {
        conn.rollback();
        conn.close();
    }

    @Test
    @DisplayName("login returns receptionist for valid credentials")
    void testLoginSuccess() {
        // Uses the receptionist you inserted in MySQL Workbench
        Receptionist rec = ReceptionistDAO.login("rgb", "cryptonite");
        assertNotNull(rec);
        assertEquals("rgb", rec.getUsername());
    }

    @Test
    @DisplayName("login returns null for invalid credentials")
    void testLoginFail() {
        Receptionist rec = ReceptionistDAO.login("wronguser", "wrongpass");
        assertNull(rec);
    }

    @Test
    @DisplayName("login returns null for wrong password")
    void testLoginWrongPassword() {
        Receptionist rec = ReceptionistDAO.login("rgb", "wrongpassword");
        assertNull(rec);
    }


}
