package ui;

import dao.DoctorDAO;
import dao.PatientDAO;
import dao.PrescriptionDAO;
import model.Doctor;
import model.Patient;
import model.Prescription;
import model.Receptionist;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PrescriptionScreen extends JFrame{

    /** Kept so it can be passed back to MainMenuScreen on cancel. */
    private final Receptionist receptionist;

    //        Attributes
    private JComboBox<Patient> patientBox;
    private JComboBox<Doctor> doctorBox;
    private JTextField diagnosisField;
    private JTextArea notesArea;
    private JSpinner dateSpinner;

    public PrescriptionScreen(Receptionist receptionist) throws SQLException {

        this.receptionist = receptionist; // store it

        setTitle("BioSpark Prescription Entry");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);
        add(createForm(), BorderLayout.CENTER);
        add(createButtons(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createHeader(){
        JPanel panel = new JPanel();
        JLabel title = new JLabel("Patient Prescription");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));

        panel.add(title);
        return panel;
    }

    private JPanel createForm() throws SQLException {
        JPanel panel = new JPanel(new GridLayout(0,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,30,20, 30));

        panel.add(new JLabel("Select Patient: "));
        patientBox = new JComboBox<>();
        for (Patient p : PatientDAO.getAllActivePatients()){
            patientBox.addItem(p);
        }
        panel.add(patientBox);

        panel.add(new JLabel("Select Doctor: "));
        doctorBox = new JComboBox<>();
        for (Doctor d : DoctorDAO.getAllActiveDoctors()){
            doctorBox.addItem(d);
        }
        panel.add(doctorBox);

        panel.add(new JLabel("Entry Date: "));
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd-MM-yyyy"));
        panel.add(dateSpinner);

        panel.add(new JLabel("Diagnosis: "));
        diagnosisField = new JTextField();
        panel.add(diagnosisField);

        panel.add(new JLabel("Prescription Notes: "));
        notesArea = new JTextArea(5, 20);
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(notesArea);
        panel.add(scrollPane);

        return panel;
    }

    private JPanel createButtons(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton saveBtn = new JButton("Save Prescription");
        JButton clearBtn = new JButton("Clear");
        JButton cancelBtn = new JButton("Cancel");

        saveBtn.addActionListener(e -> handleSave());
        clearBtn.addActionListener(e -> clearForm());
        cancelBtn.addActionListener(e -> handleCancel());

        panel.add(saveBtn);
        panel.add(clearBtn);
        panel.add(cancelBtn);

        return panel;
    }

    private void handleSave(){
        if(patientBox.getSelectedItem() == null ||
                doctorBox.getSelectedItem() == null ||
                diagnosisField.getText().trim().isEmpty() ||
                notesArea.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(
                    this,
                    "All fields are required",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Confirm prescription save?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if(confirm != JOptionPane.YES_OPTION){
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Prescription saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        try{
            Patient patient = (Patient) patientBox.getSelectedItem();
            Doctor doctor = (Doctor) doctorBox.getSelectedItem();

            java.util.Date utilDate = (java.util.Date) dateSpinner.getValue();
            java.time.LocalDate entryDate = utilDate
                    .toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();

            Prescription prescription = new Prescription(
                    patient.getId(),
                    doctor.getId(),
                    entryDate,
                    diagnosisField.getText().trim(),
                    notesArea.getText().trim()
            );

            PrescriptionDAO.addPrescription(prescription);

            JOptionPane.showMessageDialog(
                    this,
                    "Prescription saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearForm(); //reset after success
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(
                    this,
                    "Error saving prescription",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clearForm(){
        diagnosisField.setText("");
        notesArea.setText("");
    }

    private void handleCancel(){
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Cancel prescription and return to Main Menu?",
                "Confirm Cancel",
                JOptionPane.YES_NO_OPTION
        );

        if(choice == JOptionPane.YES_OPTION){
            dispose();

            new MainMenuScreen(receptionist).setVisible(true);
        }
    }
}
