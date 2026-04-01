package ui;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public LoginScreen(){

        setTitle("BioSpark Login Page");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Center: Image
        ImageIcon icon = new ImageIcon(
                getClass().getResource("/images/hmsLogo.png")
        );

        Image scaledImage = icon.getImage().getScaledInstance(
                100, 100, Image.SCALE_SMOOTH
        );

        JLabel imageLabel = new JLabel((new ImageIcon(scaledImage)));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        //        Layout form
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        setLayout(new BorderLayout());

        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);

        //  Username
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(labelFont);
        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        usernameField = new JTextField();
        usernameField.setFont(labelFont);
        usernameField.setPreferredSize(new Dimension(200, 28));

//        Password
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(labelFont);
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);

        passwordField = new JPasswordField();
        passwordField.setFont(labelFont);
        passwordField.setPreferredSize(new Dimension(200, 28));


    }
}
