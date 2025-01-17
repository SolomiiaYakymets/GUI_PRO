package view;

import controller.HighScoresController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HighScoresView extends JFrame {

    public HighScoresView(){
        setTitle("AntiPlague Game");
        setSize(260, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        List<String> items = HighScoresController.getSortedScores();

        JPanel panel = getjPanel(items);
        add(panel);
    }

    private static JPanel getjPanel(List<String> items) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);

        // Text
        JLabel messageLabel = new JLabel("Highest Scores");
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));

        // List of the scores
        DefaultListModel<String> listModel = new DefaultListModel<>();
        if (!items.isEmpty()){
            for (String item : items) {
                String[] parts = item.split(",");
                int number = items.indexOf(item) + 1;
                listModel.addElement(number + ". " + parts[0] + " " + parts[1]);
            }
        } else {
            listModel.addElement("No scores yet.");
        }

        JList<String> jList = new JList<>(listModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setPreferredSize(new Dimension(150, 160));
        jList.setVisibleRowCount(10);
        jList.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(jList);

        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;

        // Text
        gc.gridy = 0;
        panel.add(messageLabel, gc);

        // List of the scores
        gc.gridy = 1;
        panel.add(scrollPane, gc);

        return panel;
    }
}
