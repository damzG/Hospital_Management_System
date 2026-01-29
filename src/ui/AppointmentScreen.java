package ui;

import javax.swing.*;
import java.awt.*;

public class AppointmentScreen extends JFrame{

    private JComboBox<String> patientBox;
    private JComboBox<String> doctorBox;
    private JComboBox<String> timeSlotBox;
    private JSpinner dateSpinner;

    public AppointmentScreen(){
        setTitle("BioSpark Appointment Booking");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createForm(), BorderLayout.CENTER);
        add(createButtons(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createForm(){
        JPanel panel = new JPanel(new GridLayout(4,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panel.add(new JLabel("Patient: "));
        patientBox = new JComboBox<>();
        panel.add(patientBox);

        panel.add(new JLabel("Doctor: "));
        doctorBox = new JComboBox<>();
        panel.add(doctorBox);

        panel.add(new JLabel("Date: "));
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd-MM-yyyy"));
        panel.add(dateSpinner);

        panel.add(new JLabel("Time Slot: "));
        timeSlotBox = new JComboBox<>();
        panel.add(timeSlotBox);

        return panel;
    }

    private JPanel createButtons(){
        JPanel panel = new JPanel(new FlowLayout());

        JButton bookBtn = new JButton("Book Appointment");
        JButton cancelBtn = new JButton("Cancel");

        bookBtn.addActionListener(e -> handleBooking());
        cancelBtn.addActionListener(e -> handleCancel());

        panel.add(bookBtn);
        panel.add(cancelBtn);

        return panel;
    }

    private void handleBooking(){
        if(patientBox.getSelectedItem() == null ||
           doctorBox.getSelectedItem() == null ||
            timeSlotBox.getSelectedItem() == null){

            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all fields",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Confirm appointment bookings?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if(confirm != JOptionPane.YES_OPTION){
            return;
        }

        //TEMP success (backend later)
        JOptionPane.showMessageDialog(
                this,
                "Appointment booked successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void handleCancel(){
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Cancel appointment booking?",
                "Confirm Cancel",
                JOptionPane.YES_NO_OPTION
        );

        if(choice == JOptionPane.YES_OPTION){
            dispose();
            new MainMenuScreen().setVisible(true);
        }
    }
}
