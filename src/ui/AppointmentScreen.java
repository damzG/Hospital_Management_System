//Oyindamola Olaosun C00313475 OOSD Project

package ui;

import dao.AppointmentDAO;
import dao.DoctorDAO;
import dao.PatientDAO;
import model.Appointment;
import model.Doctor;
import model.Patient;
import model.Receptionist;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

public class AppointmentScreen extends JFrame{

    /** Kept so it can be passed back to MainMenuScreen on cancel. */
    private final Receptionist receptionist;

    private JComboBox<Patient> patientBox;
    private JComboBox<Doctor> doctorBox;
    private JComboBox<String> timeSlotBox;
    private JSpinner dateSpinner;

    public AppointmentScreen(Receptionist receptionist) throws SQLException {

        this.receptionist = receptionist; //store it

        setTitle("BioSpark Appointment Booking");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createForm(), BorderLayout.CENTER);
        add(createButtons(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createForm() throws SQLException {
        JPanel panel = new JPanel(new GridLayout(4,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panel.add(new JLabel("Patient: "));
        patientBox = new JComboBox<>();
        for (Patient p : PatientDAO.getAllActivePatients()){
            patientBox.addItem(p);
        }
        panel.add(patientBox);

        panel.add(new JLabel("Doctor: "));
        doctorBox = new JComboBox<>();
        for(Doctor d : DoctorDAO.getAllActiveDoctors()){
            doctorBox.addItem(d);
        }
        panel.add(doctorBox);

        panel.add(new JLabel("Date: "));
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd-MM-yyyy"));
        panel.add(dateSpinner);

        panel.add(new JLabel("Time Slot: "));
        String[] timeSlots = {"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
        "15:30", "16:00", "16:30", "17:00"};
        timeSlotBox = new JComboBox<>(timeSlots);
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

        //Success connection to backend
        try{
            int patientId = ((Patient) patientBox.getSelectedItem()).getId();
            int doctorId = ((Doctor) doctorBox.getSelectedItem()).getId();
            java.util.Date appDate = (java.util.Date) dateSpinner.getValue();
            LocalDate finalappDate = appDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            LocalDate date = finalappDate;
            String timeSlot = timeSlotBox.getSelectedItem().toString();

            if(!AppointmentDAO.isDoctorAvailable(doctorId, date, timeSlot)){
                JOptionPane.showMessageDialog(
                        this,
                        "Doctor is already booked",
                        "Booking Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Appointment appt = new Appointment(patientId, doctorId, finalappDate, timeSlot, receptionist.getReceptionistId());
            AppointmentDAO.addAppointment(appt);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            new MainMenuScreen(receptionist).setVisible(true);
        }
    }
}
