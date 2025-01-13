package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JFrame{
    private JButton newGameButton;
    private JButton highScoresButton;
    private JButton exitButton;

    public MainMenuView() {
        setTitle("AntiPlague Game");
        setSize(240, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Text
        JLabel mainMenuLabel = new JLabel("MAIN MENU");
        mainMenuLabel.setForeground(Color.WHITE);
        mainMenuLabel.setFont(new java.awt.Font("Helvetica Neue", Font.PLAIN, 16));

        // Button 1
        newGameButton = new JButton("New Game");
        newGameButton.setPreferredSize(new Dimension(160, 36));
        newGameButton.setFont(new java.awt.Font("Helvetica Neue", Font.PLAIN, 14));

        // Button 2
        highScoresButton = new JButton("High Scores");
        highScoresButton.setPreferredSize(new Dimension(160, 36));
        highScoresButton.setFont(new java.awt.Font("Helvetica Neue", Font.PLAIN, 14));

        // Button 3
        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(160, 36));
        exitButton.setFont(new java.awt.Font("Helvetica Neue", Font.PLAIN, 14));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;

        // Text
        gc.gridy = 0;
        panel.add(mainMenuLabel, gc);

        // Button 1
        gc.gridy = 1;
        panel.add(newGameButton, gc);

        // Button 2
        gc.gridy = 2;
        panel.add(highScoresButton, gc);

        // Button 3
        gc.gridy = 3;
        panel.add(exitButton, gc);

        add(panel);
    }

    public void addNewGameListener(ActionListener listener) {
        newGameButton.addActionListener(listener);
    }

    public void addHighScoresListener(ActionListener listener){
        highScoresButton.addActionListener(listener);
    }

    public void addExitListener(ActionListener listener){
        exitButton.addActionListener(listener);
    }
}
