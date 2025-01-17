package view;

import java.util.List;

import controller.HighScoresController;
import model.Country;
import model.GameState;
import model.TransportRoute;
import model.transport.Airplane;
import model.transport.Car;
import model.transport.Train;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame{
    private final JLabel timerLabel;
    private final JLabel pointsLabel;
    private final JPanel panel;
    private final List<Country> countries;
    private final MainMenuView mainMenu;

    public GameView(List<Country> countries, GameState game) {
        this.countries = countries;
        this.mainMenu = new MainMenuView();

        setTitle("AntiPlague Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Map image with routes
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graph) {
                super.paintComponent(graph);

                ImageIcon image = new ImageIcon("resources/images/map.jpeg");
                graph.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);

                createRoutes(graph);
                visualizeTransport(graph);
            }
        };
        panel.setLayout(null);

        // Country buttons
        for (Country country : countries) {
            JButton countryButton = createCountryButton(country);
            panel.add(countryButton);
        }

        // Timer
        timerLabel = new JLabel("Time: 0 m 0 s");
        timerLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        timerLabel.setBounds(10, 10, 150, 30);
        panel.add(timerLabel);

        // Points
        pointsLabel = new JLabel("Points: 0");
        pointsLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        pointsLabel.setBounds(10, 30, 150, 30);
        panel.add(pointsLabel);

        // Button to look at upgrade menu
        JButton upgradeButton = new JButton("Upgrade Menu");
        upgradeButton.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
        upgradeButton.setBounds(650, 10, 130, 40);
        upgradeButton.addActionListener(e -> openUpgradeMenu(game));
        panel.add(upgradeButton);

        setContentPane(panel);
    }

    public void updateTimer(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        SwingUtilities.invokeLater(() -> timerLabel.setText("Time: " + minutes + " m " + remainingSeconds + " s"));
    }

    public void updatePoints(int points) {
        SwingUtilities.invokeLater(() -> pointsLabel.setText("Points: " + points));
    }

    private JButton createCountryButton(Country country) {
        JButton button = new JButton(country.getName());
        button.setBounds(country.getX(), country.getY(), 168, 40);
        button.setFont(new java.awt.Font("Helvetica Neue", Font.BOLD, 16));
        button.setForeground(Color.DARK_GRAY);
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

    private void createRoutes(Graphics graph) {
        for (Country country : countries) {
            for (TransportRoute route : country.getRoutes()) {
                int x1 = route.getStartCountry().getX() + 84;
                int y1 = route.getStartCountry().getY() + 20;
                int x2 = route.getEndCountry().getX() + 84;
                int y2 = route.getEndCountry().getY() + 20;

                graph.setColor(Color.BLACK);
                graph.drawLine(x1, y1, x2, y2);
            }
        }

    }

   private void visualizeTransport(Graphics graph) {
        ImageIcon carIcon = new ImageIcon("resources/images/car.png");
        ImageIcon trainIcon = new ImageIcon("resources/images/train.png");
        ImageIcon airplaneIcon = new ImageIcon("resources/images/airplane.png");

        for (Country country : countries) {
            for (TransportRoute route : country.getRoutes()) {
                int x1 = route.getStartCountry().getX() + 84;
                int y1 = route.getStartCountry().getY() + 20;
                int x2 = route.getEndCountry().getX() + 84;
                int y2 = route.getEndCountry().getY() + 20;

                if (route.getMethod() instanceof Car) {
                    graph.drawImage(carIcon.getImage(), (x1 + x2) / 2 - 10, (y1 + y2) / 2 - 10, 20, 20, null);
                } else if (route.getMethod() instanceof Train) {
                    graph.drawImage(trainIcon.getImage(), (x1 + x2) / 2 - 10, (y1 + y2) / 2 - 10, 20, 20, null);
                } else if (route.getMethod() instanceof Airplane) {
                    graph.drawImage(airplaneIcon.getImage(), (x1 + x2) / 2 - 10, (y1 + y2) / 2 - 10, 20, 20, null);
                }
            }
        }
    }

    private void openUpgradeMenu(GameState game) {
        UpgradeMenuView upgradeMenu = new UpgradeMenuView(this, game);

        upgradeMenu.setVisible(true);
    }


    public void updateView() {
        for (Country country : countries) {
            for (Component component : panel.getComponents()) {
                if (component instanceof JButton button) {
                    if (button.getText().equals(country.getName())) {
                        updateButtonAppearance(button, country);
                    }
                }
            }
        }
        panel.revalidate();
        panel.repaint();
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


    public void showGameOver(int time, String difficulty, boolean isWin) {
        ImageIcon icon = new ImageIcon("resources/images/info_icon.png");

        if (isWin) {
            JOptionPane.showMessageDialog(this, "You have cured the world of the infection!", "Game Over", JOptionPane.INFORMATION_MESSAGE, icon);
            String name = JOptionPane.showInputDialog(this, "Enter your name:", "Game Over", JOptionPane.PLAIN_MESSAGE);

            if (name != null && !name.trim().isEmpty()) {
                int score = 300 - time;
                switch (difficulty) {
                    case "Easy" -> score *= 2;
                    case "Medium" -> score *= 3;
                    case "Hard" -> score *= 4;
                }

                HighScoresController.saveScore(name, score);
                JOptionPane.showMessageDialog(this, "Your score was saved!", "Score Saved", JOptionPane.INFORMATION_MESSAGE, icon);
            } else {
                JOptionPane.showMessageDialog(this, "Your score was not saved.", "Score Not Saved", JOptionPane.WARNING_MESSAGE, icon);
            }
        } else {
            JOptionPane.showMessageDialog(this, "The infection has spread to the entire world.", "Game Over", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }
}
