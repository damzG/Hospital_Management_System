package ui;

import dao.DoctorDAO;
import dao.PatientDAO;
import model.Doctor;
import model.Patient;
import model.Receptionist;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;

public class DoctorRegistrationScreen extends JFrame {

    /** Kept so it can be passed back to MainMenuScreen on cancel. */
    private final Receptionist receptionist;

    private final  JTextField doctorNameField;
    private final JTextField specialization;


    public DoctorRegistrationScreen(Receptionist receptionist){

        this.receptionist = receptionist; //store it

        setTitle("BioSpark Doctor Registration");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        //        Layout form
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Doctor Registration", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        //       Registration form font
        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);

        //       Doctor Name
        JLabel doctorLabel = new JLabel("Doctor Name: ");
        doctorLabel.setFont(labelFont);
        doctorLabel.setHorizontalAlignment(SwingConstants.LEFT);

        doctorNameField = new JTextField();
        doctorNameField.setFont(labelFont);
        doctorNameField.setPreferredSize(new Dimension(100, 10));

        //Doctor specialization
        JLabel specLabel = new JLabel("Doctor Specialization: ");
        specLabel.setFont(labelFont);
        specLabel.setHorizontalAlignment(SwingConstants.LEFT);

        specialization = new JTextField();
        specialization.setFont(labelFont);
        specialization.setPreferredSize(new Dimension(100, 10));

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

        formPanel.add(doctorLabel);
        formPanel.add(doctorNameField);
        formPanel.add(specLabel);
        formPanel.add(specialization);
        buttonPanel.add(registerBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(cancelBtn);
        formPanel.add(new JLabel()); // empty cell

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    private void handleRegistration(){
        String name = doctorNameField.getText().trim();
        String spec = specialization.getText().trim();


        if(name.isEmpty() || spec.isEmpty()){
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
                "Confirm Doctor registration?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (choice != JOptionPane.YES_OPTION){
            return;
        }


//        Success feedback (backend)
        try{
            Doctor doctor = new Doctor(name, spec);
            DoctorDAO.addDoctor(doctor);

            JOptionPane.showMessageDialog(
                    this,
                    "Doctor registered successfully!",
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
        doctorNameField.setText("");
        specialization.setText("");
    }

    private void handleCancel(){
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Cancel doctor registration & return to the main menu",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION){
            dispose(); //close current window
            new MainMenuScreen(receptionist).setVisible(true);
        }
    }

}