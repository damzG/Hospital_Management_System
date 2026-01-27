package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class WelcomeScreen extends JFrame {
    public WelcomeScreen(){
        setTitle("BioSpark");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

//        Layout Manager
        setLayout(new BorderLayout());

        Font titleFont = new Font("Serif", Font.BOLD, 36);
        Font taglineFont = new Font("SansSerif", Font.ITALIC, 14);

        JLabel titleLabel = new JLabel("BioSpark", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);

//        System Title
        JLabel title = new JLabel(
                "<html><center>" +
                        "<p>Saving lives through innovative software solutions and ideas.<br/>Every life counts.</p></center></html>",
                SwingConstants.CENTER
        );

        add(title, BorderLayout.NORTH);

        //Center: Image
        ImageIcon icon = new ImageIcon(
                getClass().getResource("/images/hmsLogo.png")
        );

        Image scaledImage = icon.getImage().getScaledInstance(
                200, 200, Image.SCALE_SMOOTH
        );

        JLabel imageLabel = new JLabel((new ImageIcon(scaledImage)));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        //Bottom: Button
        JButton continueBtn = new JButton("Continue");
        continueBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        continueBtn.setBackground(new Color(34, 139, 34)); // calm green
        continueBtn.setForeground(Color.WHITE);
        continueBtn.setFocusPainted(false);
        continueBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        continueBtn.addActionListener(e -> {
            new LoginScreen().setVisible(true);
            dispose(); // closes WelcomeScreen
        });

        add(continueBtn, BorderLayout.SOUTH);
    }
}
