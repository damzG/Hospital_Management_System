//Oyindamola Olaosun C00313475 OOSD Project
package ui;

import model.Receptionist;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


/**
 * Displays educational medical reference images in a scrollable grid.
 * Each card shows an image, a title, and a short description.
 */

public class ExtrasScreen extends JFrame {

    private final Receptionist receptionist;

    /** Each entry: { title, description, imagePath } */
    private static final String[][] CONTENT = {
            {
                    "The Human Heart",
                    "The heart is a muscular organ that pumps blood through the\n" +
                            "circulatory system. It has four chambers: left & right atria\n" +
                            "and left & right ventricles.",
                    "/images/extras/heart.png"
            },
            {
                    "The Skeletal System",
                    "The adult human body contains 206 bones. The skeleton\n" +
                            "provides structure, protects organs, and works with\n" +
                            "muscles to enable movement.",
                    "/images/extras/skeleton.png"
            },
            {
                    "The Respiratory System",
                    "The lungs exchange oxygen and carbon dioxide with the\n" +
                            "blood. An adult takes around 20,000 breaths per day\n" +
                            "moving air through the trachea and bronchi.",
                    "/images/extras/lungs.png"
            },
            {
                    "The Nervous System",
                    "The brain and spinal cord form the central nervous system.\n" +
                            "Neurons transmit electrical signals at speeds of up to\n" +
                            "120 metres per second.",
                    "/images/extras/nervous.png"
            },
            {
                    "The Digestive System",
                    "Food travels through roughly 9 metres of digestive tract.\n" +
                            "The small intestine absorbs nutrients while the large\n" +
                            "intestine absorbs water.",
                    "/images/extras/digestive.png"
            },
            {
                    "Blood Cell Types",
                    "Red blood cells carry oxygen, white blood cells fight\n" +
                            "infection, and platelets help clotting. An adult has\n" +
                            "roughly 5 litres of blood.",
                    "/images/extras/blood.png"
            }
    };

    /**
     * ExtrasScreen() - the screen constructor
     * @param receptionist - the receptionist who is responsible for logging into the system
     * **/
    public ExtrasScreen(Receptionist receptionist) {
        this.receptionist = receptionist;

        setTitle("BioSpark — Medical Reference");
        setSize(800, 600);
        setMinimumSize(new Dimension(600, 400));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeader(),      BorderLayout.NORTH);
        add(createScrollPane(),  BorderLayout.CENTER);
        add(createBackButton(),  BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * createHeader() - the header for the
     * **/

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(30, 144, 255));
        header.setBorder(new EmptyBorder(12, 16, 12, 16));

        JLabel title = new JLabel("Medical Educational Reference");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitle = new JLabel("Basic anatomy for quick reference");
        subtitle.setFont(new Font("SansSerif", Font.ITALIC, 12));
        subtitle.setForeground(new Color(220, 240, 255));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBorder(new EmptyBorder(4, 0, 2, 0));

        header.add(title,    BorderLayout.CENTER);
        header.add(subtitle, BorderLayout.SOUTH);

        return header;
    }

    /**
     * Builds a scrollable panel containing all image cards.
     * Cards reflow automatically when the window is resized.
     */
    private JScrollPane createScrollPane() {
        JPanel cardGrid = new JPanel(new GridLayout(0, 2, 16, 16));
        cardGrid.setBorder(new EmptyBorder(20, 20, 20, 20));
        cardGrid.setBackground(new Color(245, 247, 250));

        for (String[] item : CONTENT) {
            cardGrid.add(createCard(item[0], item[1], item[2]));
        }

        JScrollPane scroll = new JScrollPane(cardGrid);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(16); // smooth scroll
        scroll.setBorder(null);

        return scroll;
    }

    /**
     * Builds a single educational card with an image, title, and description.
     *
     * @param title       the anatomy topic title
     * @param description a short plain-text description
     * @param imagePath   path to the image inside the resources folder
     * @return a styled card panel
     */
    private JPanel createCard(String title, String description, String imagePath) {
        JPanel card = new JPanel(new BorderLayout(0, 8));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 235), 1),
                new EmptyBorder(12, 12, 12, 12)
        ));

        // Image
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(0, 160));

        java.net.URL imgUrl = getClass().getResource(imagePath);
        if (imgUrl != null) {
            ImageIcon icon = new ImageIcon(imgUrl);
            Image scaled = icon.getImage().getScaledInstance(220, 155, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaled));
        } else {
            // Placeholder if image not found
            imageLabel.setText("[ image not found ]");
            imageLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
            imageLabel.setForeground(Color.GRAY);
            imageLabel.setOpaque(true);
            imageLabel.setBackground(new Color(240, 242, 245));
        }

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        titleLabel.setForeground(new Color(30, 60, 100));

        // Description
        JTextArea descArea = new JTextArea(description);
        descArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        descArea.setForeground(new Color(70, 70, 70));
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);
        descArea.setOpaque(false);
        descArea.setFocusable(false);

        // Text section
        JPanel textPanel = new JPanel(new BorderLayout(0, 6));
        textPanel.setOpaque(false);
        textPanel.add(titleLabel, BorderLayout.NORTH);
        textPanel.add(descArea,   BorderLayout.CENTER);

        card.add(imageLabel, BorderLayout.NORTH);
        card.add(textPanel,  BorderLayout.CENTER);

        return card;
    }

    /**
     * Back Button - to go to the previous screen
     * **/

    private JPanel createBackButton() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 10));

        JButton backBtn = new JButton("← Back to Menu");
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
        backBtn.setBackground(new Color(30, 144, 255));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> {
            dispose();
            new MainMenuScreen(receptionist).setVisible(true);
        });

        panel.add(backBtn);
        return panel;
    }

}
