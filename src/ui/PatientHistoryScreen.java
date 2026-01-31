package ui;

import javax.swing.*;
import java.awt.*;

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

    private JScrollPane createTablePanel(){
        String[] columns = {"Visit Date", "Diagnosis", "Notes"};

        Object[][] data = {
                {"2024-03-12", "Flu", "Prescribed rest and fluids"},
                {"2024-06-01", "Check-up", "All vitals normal"}
        };

        historyTable = new JTable(data, columns);
        historyTable.setEnabled(false); //read-only

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
