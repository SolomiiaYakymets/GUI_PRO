package view;

import java.util.List;
import model.Country;
import model.GameState;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame{
    private JLabel timerLabel;
    private JLabel imageLabel;

    public GameView(List<Country> countries) {

        setTitle("AntiPlague Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Map image
        ImageIcon imageIcon = new ImageIcon("resources/images/map.jpeg");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        imageLabel = new JLabel(scaledImageIcon);
        imageLabel.setLayout(null);

        // Timer
        timerLabel = new JLabel("Time: 0 m 0 s");
        timerLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        timerLabel.setForeground(Color.DARK_GRAY);
        timerLabel.setBounds(10, 10, 150, 30);
        panel.add(timerLabel);

        // Country buttons
        for (Country country : countries) {
            JButton countryButton = createCountryButton(country);
            imageLabel.add(countryButton);
        }

        panel.add(imageLabel, BorderLayout.CENTER);
        add(panel);

        setContentPane(panel);
    }

    public void updateTimer(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        SwingUtilities.invokeLater(() -> timerLabel.setText("Time: " + minutes + " m " + remainingSeconds + " s"));
    }

    private JButton createCountryButton(Country country) {
        JButton button = new JButton(country.getName());
        button.setBounds(country.getX(), country.getY(), 168, 40);
        button.setFont(new java.awt.Font("Helvetica Neue", Font.BOLD, 16));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFocusable(false);

        ImageIcon icon = new ImageIcon("resources/images/info_icon.png");

        button.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "Country: " + country.getName()
                        + "\nPopulation: " + country.getPopulation()
                        + "\nInfected count: " + country.getInfectedCount(),
                "Country Information",
                JOptionPane.INFORMATION_MESSAGE,
                icon
        ));
        return button;
    }

    public void updateView(List<Country> countries) {
        for (Country country : countries) {
            for (Component component : imageLabel.getComponents()) {
                if (component instanceof JButton button) {
                    if (button.getText().equals(country.getName())) {
                        updateButtonAppearance(button, country);
                    }
                }
            }
        }
        imageLabel.repaint();
        imageLabel.revalidate();
    }

    private void updateButtonAppearance(JButton button, Country country) {
        if (country.isFullyInfected()) {
            button.setForeground(new Color(153, 0, 0));
        } else if (country.getInfectedCount() > 0) {
            button.setForeground(new Color(255, 128, 0));
        } else {
            button.setForeground(new Color(0, 100, 0));
        }
    }


    public void showGameOver() {
    }
}
