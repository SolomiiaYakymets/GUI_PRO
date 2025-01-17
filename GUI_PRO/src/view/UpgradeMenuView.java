package view;

import controller.UpgradeController;
import model.GameState;
import model.Upgrade;

import javax.swing.*;
import java.awt.*;

public class UpgradeMenuView extends JFrame {
    UpgradeController upgradeController;

    public UpgradeMenuView(JFrame parent, GameState game) {
        this.upgradeController = new UpgradeController();

        setTitle("AntiPlague Game");
        setSize(900, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton button;

        for (Upgrade upgrade : upgradeController.getAvailableUpgrades()) {
            ImageIcon icon = new ImageIcon("resources/images/info_icon.png");
            button = new JButton(upgrade.getName() + " - Description: " + upgrade.getEffect() + " - Cost: " + upgrade.getCost());
            button.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
            button.addActionListener(e -> {
                boolean success;
                if (!upgrade.isHasParameter()) {
                    success = game.purchaseUpgrade(upgrade, null);
                } else {
                    String parameter = JOptionPane.showInputDialog(this, upgrade.getParameterInfo(), "Parameter", JOptionPane.PLAIN_MESSAGE);
                    if (parameter != null && !parameter.trim().isEmpty()) {
                        success = game.purchaseUpgrade(upgrade, parameter);
                    } else {
                        success = false;
                    }
                }

                if (!success) {
                    JOptionPane.showMessageDialog(this, "You don't have enough points to purchase this upgrade.", "Purchase Failed", JOptionPane.ERROR_MESSAGE, icon);
                } else {
                    dispose();
                }
            });

            panel.add(button);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        setContentPane(scrollPane);
    }
}
