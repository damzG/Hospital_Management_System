package ui;

import javax.swing.*;
import java.awt.*;

public class MainMenuScreen extends JFrame {
    public MainMenuScreen(){
        setTitle("BioSpark HMS Menu");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);
        add(createMenu(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setBackground(new Color(30, 144, 255)); //medical blue
        header.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));

        JLabel title = new JLabel("BioSpark Hospital Management System");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setForeground(Color.WHITE);

        header.add(title);
        return header;
    }

    private JPanel createMenu() {
        JPanel menu = new JPanel(new GridLayout(3,2,15,15));
        menu.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));

        menu.add(createMenuButton("Patients"));
        menu.add(createMenuButton("Appointments"));
        menu.add(createMenuButton("Medical Records"));
        menu.add(createMenuButton("Prescriptions"));
        menu.add(createMenuButton("Logout"));

        return menu;
    }

    private JButton createMenuButton(String text){
        JButton btn = new JButton(text);

        btn.setFont(new Font("SanSerif", Font.BOLD,14));
        btn.setBackground(new Color(46, 139, 87)); //hospital green
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        return btn;
    }
}
