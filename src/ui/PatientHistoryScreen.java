//Oyindamola Olaosun C00313475 OOSD Project

package ui;

import dao.PatientDAO;
import dao.PatientHistoryDAO;
import model.Patient;
import model.Prescription;
import model.Receptionist;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PatientHistoryScreen extends JFrame {

    /** Kept so it can be passed back to MainMenuScreen on cancel. */
    private final Receptionist receptionist;


    private JComboBox<Patient> patientList;
    private JTable historyTable;
    private int patientId;

    /**
     * PatientHistoryScreen Constructor
     * **/
    public PatientHistoryScreen(Receptionist receptionist) throws SQLException {
        this.receptionist = receptionist;

        setTitle("BioSpark Patient History (View Only) ");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(createTopPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     *  Top Panel -> user selects the patient and loads history
     * **/
    private JPanel createTopPanel() throws SQLException {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panel.add(new JLabel("Select Patient: "));

//        List of patients from the database
        patientList = new JComboBox<>();
        for(Patient p : PatientDAO.getAllActivePatients()){
            patientList.addItem(p);
        }

//        Load history from patient history table
        JButton loadBtn = new JButton("Load History");
        loadBtn.addActionListener(e -> loadHistory());

        panel.add(patientList);
        panel.add(loadBtn);

        return panel;
    }

    /**
     * Loads history from Prescription Table
     * **/
    private void loadHistory(){

        Patient selected = (Patient) patientList.getSelectedItem();

        if (selected == null) return;

        try{
            List<Prescription>  history = PatientHistoryDAO.getHistoryByPatientId(selected.getId());
            DefaultTableModel model = (DefaultTableModel) historyTable.getModel();

            model.setRowCount(0); //clear old data

            for (Prescription p : history){
                model.addRow(new Object[]{
                        p.getDate(),
                        p.getDiagnosis(),
                        p.getNotes()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error loading patient history"
            );
        }
    }

    /**
     * Creates a table where the entry date, diagnosis and notes are displayed
     * **/
    private JScrollPane createTablePanel() throws SQLException {

        String[] columns = {"entry_date", "diagnosis", "notes"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        historyTable = new JTable(model);
        historyTable.setEnabled(false);

        return new JScrollPane(historyTable);
    }

    /**
     * Creates a button panel, where the back button is present and goes back to the main menu
     * **/
    private JPanel createButtonPanel(){
        JPanel panel = new JPanel();
        JButton backBtn = new JButton("Back to Main Menu");

        backBtn.addActionListener(e -> {
            dispose();
            new MainMenuScreen(receptionist).setVisible(true);
        });

        panel.add(backBtn);

        return panel;
    }

}
