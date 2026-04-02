package ui;

import dao.PatientDAO;
import model.Patient;
import model.Receptionist;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class PatientRegistrationScreen extends JFrame {

    /** Kept so it can be passed back to MainMenuScreen on cancel. */
    private final Receptionist receptionist;


    private final JTextField usernameField;
    private final JTextField phoneField;
    private final JTextField addressField;
    private final JSpinner dobSpinner;
    private final JComboBox<String> genderCombo;

    public PatientRegistrationScreen(Receptionist receptionist){
        this.receptionist = receptionist; // store it

        setTitle("BioSpark Patient Registration ");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //        Layout form
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Patient Registration", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

//      Registration form font
        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);

//        Patient Name
        JLabel patientLabel = new JLabel("Patient Name: ");
        patientLabel.setFont(labelFont);
        patientLabel.setHorizontalAlignment(SwingConstants.LEFT);

        usernameField = new JTextField();
        usernameField.setFont(labelFont);
        usernameField.setPreferredSize(new Dimension(200, 28));

//        Patient DOB
        JLabel patientDOB = new JLabel("Date of Birth: ");
        patientDOB.setFont(labelFont);
        patientDOB.setHorizontalAlignment(SwingConstants.LEFT);


        SpinnerModel dateModel = new SpinnerDateModel();
        dobSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dobSpinner, "dd-MM-yyyy");
        dobSpinner.setEditor(editor);
        dobSpinner.setPreferredSize(new Dimension(200, 28));


//        Patient Phone Number
        JLabel patientPhone = new JLabel("Phone Number: ");
        patientPhone.setFont(labelFont);
        patientPhone.setHorizontalAlignment(SwingConstants.LEFT);

        phoneField = new JTextField();
        phoneField.setFont(labelFont);
        phoneField.setPreferredSize(new Dimension(200, 28));

//        Patient Home Address
        JLabel patientAddress = new JLabel("Home Address: ");
        patientAddress.setFont(labelFont);
        patientAddress.setHorizontalAlignment(SwingConstants.LEFT);

        addressField = new JTextField();
        addressField.setFont(labelFont);
        addressField.setPreferredSize(new Dimension(200, 28));

//        Patient Gender
        JLabel genderLabel = new JLabel("Gender: ");
        genderLabel.setFont(labelFont);

        genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderCombo.setFont(labelFont);


//        Register, Clear and Cancel Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton registerBtn = new JButton("Register");
        JButton clearBtn = new JButton("Clear");
        JButton cancelBtn = new JButton("Cancel");

        registerBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        registerBtn.setBackground(new Color(56,127,117)); // calm green
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);

//        Action Listener Event
        registerBtn.addActionListener(e -> handleRegistration());

        clearBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        clearBtn.setBackground(new Color(84,108,204)); // calm blue
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFocusPainted(false);

        clearBtn.addActionListener(e -> clearForm());


        cancelBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        cancelBtn.setBackground(new Color(252,52,104)); // calm red
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);

        cancelBtn.addActionListener(e -> handleCancel());

//        Arrangement of the components into the layout
        formPanel.add(patientLabel);
        formPanel.add(usernameField);
        formPanel.add(patientDOB);
        formPanel.add(dobSpinner);
        formPanel.add(patientPhone);
        formPanel.add(phoneField);
        formPanel.add(patientAddress);
        formPanel.add(addressField);
        formPanel.add(genderLabel);
        formPanel.add(genderCombo);
        buttonPanel.add(registerBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(cancelBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        formPanel.add(new JLabel()); // empty cell

        add(formPanel);


    }

    private void handleRegistration(){
        String name = usernameField.getText().trim();
        String phone = phoneField.getText().trim();
        String address = addressField.getText().trim();
        String gender = (String) genderCombo.getSelectedItem();

        if(name.isEmpty() || phone.isEmpty() || address.isEmpty()){
            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all required fields",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Confirm patient registration?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (choice != JOptionPane.YES_OPTION){
            return;
        }

//        Extract DOB Properly
        java.util.Date dobDate = (java.util.Date) dobSpinner.getValue();
        LocalDate dob = dobDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

//        Success feedback (backend)
        try{
            Patient patient = new Patient(name, dob, phone, address, gender);
            PatientDAO.addPatient(patient);

            JOptionPane.showMessageDialog(
                    this,
                    "Patient registered successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearForm(); //reset after success
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(
                    this,
                    "Error saving patient",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }

    private void clearForm(){
        usernameField.setText("");
        phoneField.setText("");
        addressField.setText("");
        dobSpinner.setValue(new java.util.Date());
        genderCombo.setSelectedIndex(0);
    }

    private void handleCancel(){
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Cancel patient registration & return to the main menu",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION){
            dispose(); //close current window
            new MainMenuScreen(receptionist).setVisible(true);
        }
    }

}
