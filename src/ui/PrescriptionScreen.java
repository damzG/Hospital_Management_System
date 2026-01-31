package ui;

import javax.swing.*;
import java.awt.*;

public class PrescriptionScreen extends JFrame{

    //        Attributes
    private JComboBox<String> patientBox;
    private JTextField diagnosisField;
    private JTextArea notesArea;

    public PrescriptionScreen(){
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

    private JPanel createForm(){
        JPanel panel = new JPanel(new GridLayout(3,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,30,20, 30));

        panel.add(new JLabel("Select Patient: "));
        patientBox = new JComboBox<>();
        patientBox.addItem("John Doe");
        patientBox.addItem("Jane Smith");
        panel.add(patientBox);

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

        clearForm();

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
            new MainMenuScreen().setVisible(true);
        }
    }
}
