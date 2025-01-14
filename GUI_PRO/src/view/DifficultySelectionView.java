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

        // Text
        JLabel messageLabel = new JLabel("Choose a difficulty level:");
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));

        // Button 1
        JButton easyButton = new JButton("Easy");
        easyButton.setPreferredSize(new Dimension(160, 36));
        easyButton.setFont(new java.awt.Font("Helvetica Neue", Font.PLAIN, 14));

        // Button 2
        JButton mediumButton = new JButton("Medium");
        mediumButton.setPreferredSize(new Dimension(160, 36));
        mediumButton.setFont(new java.awt.Font("Helvetica Neue", Font.PLAIN, 14));

        // Button 3
        JButton hardButton = new JButton("Hard");
        hardButton.setPreferredSize(new Dimension(160, 36));
        hardButton.setFont(new java.awt.Font("Helvetica Neue", Font.PLAIN, 14));

        easyButton.addActionListener(e -> selectDifficulty("Easy"));
        mediumButton.addActionListener(e -> selectDifficulty("Medium"));
        hardButton.addActionListener(e -> selectDifficulty("Hard"));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;

        // Text
        gc.gridy = 0;
        panel.add(messageLabel, gc);

        // Button 1
        gc.gridy = 1;
        panel.add(easyButton, gc);

        // Button 2
        gc.gridy = 2;
        panel.add(mediumButton, gc);

        // Button 3
        gc.gridy = 3;
        panel.add(hardButton, gc);

        add(panel);
    }

    private void selectDifficulty(String difficulty) {
        selectedDifficulty = difficulty;
        dispose();
    }

    public String getSelectedDifficulty() {
        return selectedDifficulty;
    }
}
