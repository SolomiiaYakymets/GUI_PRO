package view;

import javax.swing.*;
import java.awt.*;

public class DifficultySelectionView extends JDialog {
    private String selectedDifficulty;

    public DifficultySelectionView(JFrame parent) {
        super(parent, "AntiPlague Game", true);
        setSize(240, 200);
        setResizable(false);
        setLocationRelativeTo(parent);

        // Text 1
        JLabel messageLabel = new JLabel("Choose a difficulty level:");
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));

        // Drop-down menu with difficulties
        String[] difficulties = {"Easy", "Medium", "Hard"};
        JComboBox<String> difficultyBox = new JComboBox<>(difficulties);
        difficultyBox.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        difficultyBox.setPreferredSize(new Dimension(166, 42));

        // Text 2
        JLabel countryLabel = new JLabel("Choose a starting country:");
        countryLabel.setForeground(Color.WHITE);
        countryLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));

        // Drop-down with countries
        String[] countries = {"Random", "Spain", "Portugal", "France", "Germany", "Italy", "Austria", "Czech Republic", "Poland", "Hungary", "Slovakia", "Romania"};
        JComboBox<String> countryBox = new JComboBox<>(countries);
        countryBox.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        countryBox.setPreferredSize(new Dimension(166, 42));

        // OK button
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(160, 36));
        okButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));

        // Add action listener to the OK button
        okButton.addActionListener(e -> {
            selectedDifficulty = (String) difficultyBox.getSelectedItem();
            dispose();
        });

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;

        // Text 1
        gc.gridy = 0;
        panel.add(messageLabel, gc);

        // Drop-down menu with difficulties
        gc.gridy = 1;
        panel.add(difficultyBox, gc);

        // Text 2
        gc.gridy = 2;
        panel.add(countryLabel, gc);

        // Drop-down menu with countries
        gc.gridy = 3;
        panel.add(countryBox, gc);

        // Ok button
        gc.gridy = 4;
        panel.add(okButton, gc);

        add(panel);
    }

    public String getSelectedDifficulty() {
        return selectedDifficulty;
    }
}
