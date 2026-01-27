package service;

import dao.PatientDAO;
import model.Patient;
import ui.WelcomeScreen;

import java.sql.SQLException;
import java.time.LocalDate;

public class TrialApp {
    public static void main(String[] args) throws SQLException {
        WelcomeScreen first = new WelcomeScreen();
        first.setVisible(true);
    }
}
