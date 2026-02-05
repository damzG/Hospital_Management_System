package ui;

import dao.PatientDAO;
import model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PatientHistoryScreen extends JFrame {

    private JComboBox<String> patientList;
    private JTable historyTable;

    public PatientHistoryScreen(){
        setTitle("BioSpark Patient History (View Only) ");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(createTopPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setVisible(true);

    }

    private JPanel createTopPanel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panel.add(new JLabel("Select Patient: "));

        patientList = new JComboBox<>();
        patientList.addItem("John Doe");
        patientList.addItem("Jane Smith");

        panel.add(patientList);

        return panel;
    }

    private JScrollPane createTablePanel() {

        String[] columns = {"ID", "Name", "DOB", "Phone", "Gender"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try {
            List<Patient> patients = PatientDAO.getAllActivePatients();

            for (Patient p : patients) {
                model.addRow(new Object[]{
                        p.getId(),
                        p.getName(),
                        p.getDob(),
                        p.getPhone(),
                        p.getGender()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading patient data",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        historyTable = new JTable(model);
        historyTable.setEnabled(false);

        return new JScrollPane(historyTable);
    }


    private JPanel createButtonPanel(){
        JPanel panel = new JPanel();
        JButton backBtn = new JButton("Back to Main Menu");

        backBtn.addActionListener(e -> {
            dispose();
            new MainMenuScreen().setVisible(true);
        });

        panel.add(backBtn);

        return panel;
    }

}
