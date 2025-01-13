package view;

import java.util.List;
import model.Country;
import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame{

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
        JLabel imageLabel = new JLabel(scaledImageIcon);
        imageLabel.setLayout(null);

        // Country buttons
        for (Country country : countries) {
            JButton countryButton = createCountryButton(country);
            imageLabel.add(countryButton);
        }

        panel.add(imageLabel, BorderLayout.CENTER);
        add(panel);

        setContentPane(panel);
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

        button.addActionListener(e -> JOptionPane.showMessageDialog(this, "Clicked: " + country.getName()));
        return button;
    }
}
