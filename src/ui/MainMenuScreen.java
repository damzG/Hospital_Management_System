package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainMenuScreen extends JFrame {
    public MainMenuScreen(){
        setTitle("BioSpark HMS Menu");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

//        Set the layout of the entire frame
        setLayout(new BorderLayout());

//        Efficient use of methods
        add(createHeader(), BorderLayout.NORTH);
        add(createMenu(), BorderLayout.CENTER);
        add(createLogoutButton(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setBackground(new Color(30, 144, 255)); //medical blue
        header.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));

        JLabel title = new JLabel("BioSpark Hospital Management System");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setForeground(Color.WHITE);


        header.add(title);
        return header;
    }

    private JPanel createMenu() {
        JPanel menu = new JPanel(new GridLayout(3,2,15,15));
        menu.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));

        JButton patientBtn = createMenuButton("Patients");
        patientBtn.addActionListener(e -> {
            dispose();
            new PatientRegistrationScreen().setVisible(true);
        });

        JButton appBtn = createMenuButton("Appointments");
        appBtn.addActionListener(e -> {
            dispose();
            try {
                new AppointmentScreen().setVisible(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton patientHistoryBtn = createMenuButton("Medical Records");
        patientHistoryBtn.addActionListener(e -> {
            dispose();
            try {
                new PatientHistoryScreen().setVisible(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton prescribeBtn = createMenuButton("Prescriptions");
        prescribeBtn.addActionListener(e -> {
            dispose();
            try {
                new PrescriptionScreen().setVisible(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton doctorBtn = createMenuButton("Doctor");
        doctorBtn.addActionListener(e -> {
            dispose();
            new DoctorRegistrationScreen().setVisible(true);
        });


        menu.add(patientBtn);
        menu.add(appBtn);
        menu.add(patientHistoryBtn);
        menu.add(prescribeBtn);
        menu.add(createMenuButton("Extras"));
        menu.add(doctorBtn);

        return menu;
    }

    private JButton createMenuButton(String text){
        JButton btn = new JButton(text);

        btn.setFont(new Font("SanSerif", Font.BOLD,14));
        btn.setBackground(new Color(46, 139, 87)); //hospital green
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        return btn;
    }

    private JButton createLogoutButton(){
        JButton btn = new JButton("Logout");

        btn.addActionListener(e -> handleLogout());

        return btn;
    }

    private void handleLogout(){
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure  you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
        );
    }
}
