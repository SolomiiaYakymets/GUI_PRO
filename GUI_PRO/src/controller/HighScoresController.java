package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HighScoresController implements Serializable {
    private static final String FILE_PATH = "data/scores.ser";
    private static final long serialVersionUID = 1L;

    public static void saveScore(String name, int score) {
        List<String> scores = readScores();
        scores.add(name + "," + score);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(scores);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readScores() {
        List<String> scores = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists() || file.length() == 0) {
            return scores;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            scores = (List<String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return scores;
    }

    public static List<String> getSortedScores() {
        List<String> scores = readScores();

        Set<String> uniqueSet = new HashSet<>(scores);
        List<String> uniqueList = new ArrayList<>(uniqueSet);

        uniqueList.sort((score1, score2) -> {
            int value1 = Integer.parseInt(score1.split(",")[1]);
            int value2 = Integer.parseInt(score2.split(",")[1]);
            return Integer.compare(value2, value1);
        });

        return uniqueList;
    }

}
