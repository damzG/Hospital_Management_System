//Oyindamola Olaosun C00313475 OOSD Project

package ui;

import dao.ReceptionistDAO;
import model.Receptionist;

import javax.swing.*;
import java.awt.*;


/**
 * Login screen for receptionist access.
 * Locks the form after 3 failed attempts.
 */

public class LoginScreen extends JFrame{

//    No of attempts counter
    private static final int MAX_ATTEMPTS = 3;
    private int attemptCount = 0;

//    GUI Components
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JLabel feedbackLabel;
    private final JButton loginButton;

//    Constructor
    /**
     * Login screen() - COnstructor
     */
    public LoginScreen(){
        setTitle("BioSpark — Login");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);
        add(createForm(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);

        usernameField = tempUsername;
        passwordField = tempPassword;
        feedbackLabel = tempFeedback;
        loginButton = tempLoginBtn;
    }

    /**
     * The Header with the title
     */
    private JPanel createHeader(){
        JPanel header = new JPanel();
        header.setBackground(new Color(30, 144, 255));
        header.setBorder(BorderFactory.createEmptyBorder(14, 10, 14, 10));

        JLabel title = new JLabel("BioSpark Hospital Management System");
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        title.setForeground(Color.WHITE);
        header.add(title);

        return header;
    }

//    Temporary references for final field assignment
    private JTextField     tempUsername;
    private JPasswordField tempPassword;
    private JLabel         tempFeedback;
    private JButton        tempLoginBtn;

    /**
     * Builds the login form using GridLayout.
     * Each row holds a label and its matching input field.
     */
    private JPanel createForm(){
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 50));

//        4 rows: username, password, feedback, button
        JPanel grid = new JPanel(new GridLayout(4, 2, 10, 12));

        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 14);

//        Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(labelFont);
        tempUsername = new JTextField();
        tempUsername.setFont(fieldFont);
        grid.add(userLabel);
        grid.add(tempUsername);

//        Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(labelFont);
        tempPassword = new JPasswordField();
        tempPassword.setFont(fieldFont);
        tempPassword.addActionListener(e -> attemptLogin()); // Enter key submits
        grid.add(passLabel);
        grid.add(tempPassword);

//        Feedback
        grid.add(new JLabel()); // empty left cell
        tempFeedback = new JLabel(" ");
        tempFeedback.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tempFeedback.setForeground(Color.RED);
        grid.add(tempFeedback);

//        Login button
        grid.add(new JLabel()); // empty left cell
        tempLoginBtn = new JButton("Log in");
        tempLoginBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        tempLoginBtn.setBackground(new Color(46, 139, 87));
        tempLoginBtn.setForeground(Color.WHITE);
        tempLoginBtn.setFocusPainted(false);
        tempLoginBtn.addActionListener(e -> attemptLogin());
        grid.add(tempLoginBtn);

        wrapper.add(grid, BorderLayout.NORTH);
        return wrapper;
    }

    /**
     * The footer
     */
    private JPanel createFooter(){
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel note = new JLabel("Authorized personnel only");
        note.setFont(new Font("SansSerif", Font.ITALIC, 11));
        note.setForeground(Color.GRAY);
        footer.add(note);
        return footer;
    }

    /**
     * Checks credentials via {@link ReceptionistDAO#login}.
     * Locks the form after {@value #MAX_ATTEMPTS} failed attempts.
     */

    private void attemptLogin(){
        if (attemptCount >= MAX_ATTEMPTS) return;

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            feedbackLabel.setText("Please enter both fields.");
            return;
        }

//        Now login into the system
        Receptionist rec = ReceptionistDAO.login(username, password);

        if (rec != null) {
            dispose();
            new MainMenuScreen(rec); //pass the receptionist through
        }
        else{
            attemptCount++;
            int remaining = MAX_ATTEMPTS - attemptCount;

            if (remaining == 0) {
                feedbackLabel.setText("Access locked — too many attempts.");
                loginButton.setEnabled(false);
                usernameField.setEnabled(false);
                passwordField.setEnabled(false);
            }else{
                feedbackLabel.setText("Invalid credentials. " + remaining + " attempt(s) left.");
            }
        }
    }
}
