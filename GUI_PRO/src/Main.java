import model.Country;
import view.MainMenuView;
import view.GameView;
import view.DifficultySelectionView;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Spain", 47910526, null, 75, 385));
        countries.add(new Country("Portugal", 10196709, null, 0, 375));
        countries.add(new Country("France", 66548530, null, 218, 245));
        countries.add(new Country("Germany", 84552242, null, 355, 150));
        countries.add(new Country("Italy", 59342867, null, 405,335));
        countries.add(new Country("Austria", 9027999, null, 440, 230));
        countries.add(new Country("Czech Republic", 10493986, null, 445, 180));
        countries.add(new Country("Poland", 38539201, null, 505, 115));
        countries.add(new Country("Hungary", 9570860, null, 520, 235));
        countries.add(new Country("Slovakia", 5379455, null, 530,190));
        countries.add(new Country("Romania", 19015088, null, 635,245));

        MainMenuView mainMenu = new MainMenuView();

        mainMenu.addNewGameListener(e -> startNewGame(mainMenu, countries));
        mainMenu.addHighScoresListener(e -> showHighScores());
        mainMenu.addExitListener(e -> System.exit(0));

        mainMenu.setVisible(true);

    }
    private static void startNewGame(MainMenuView view, List<Country> countries) {
        DifficultySelectionView difficultyDialog = new DifficultySelectionView(view);
        difficultyDialog.setVisible(true);

        String selectedDifficulty = difficultyDialog.getSelectedDifficulty();

        if (selectedDifficulty != null) {
            GameView gameView = new GameView(countries);
            gameView.setVisible(true);
        }
    }

    private static void showHighScores() {
        System.out.println("Displaying high scores...");
    }
}
