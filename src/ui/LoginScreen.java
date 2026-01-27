package ui;
import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame{

    public LoginScreen(){
        setTitle("BioSpark - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

//        Layout form
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));


//       Registration form
        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(labelFont);

        JTextField usernameField = new JTextField();
        usernameField.setFont(labelFont);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(labelFont);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(labelFont);


        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginBtn.setBackground(new Color(70, 130, 180)); // calm blue
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);

        formPanel.add(userLabel);
        formPanel.add(usernameField);
        formPanel.add(passLabel);
        formPanel.add(passwordField);
        formPanel.add(loginBtn);
        formPanel.add(new JLabel()); // empty cell

        add(formPanel);

    }
}
