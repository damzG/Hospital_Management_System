package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class PatientRegistrationScreen extends JFrame {

    private JTextField usernameField;
    private JTextField phoneField;
    private JTextField addressField;
    private JSpinner dobSpinner;

    public PatientRegistrationScreen(){
        setTitle("BioSpark Patient Registration ");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //        Layout form
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        setLayout(new BorderLayout());



//       Registration form
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
        Date dobDate = (Date) dobSpinner.getValue();
        LocalDate dob = dobDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

//        TEMP success feedback (backend)
        JOptionPane.showMessageDialog(
                this,
                "Patient registered successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        clearForm(); //optional: reset after success
    }

    private void clearForm(){
        usernameField.setText("");
        phoneField.setText("");
        addressField.setText("");
        dobSpinner.setValue(new java.util.Date());
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
            new MainMenuScreen().setVisible(true);
        }
    }

}
