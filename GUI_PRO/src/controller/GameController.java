package controller;

import model.Country;
import model.GameState;
import view.DifficultySelectionView;
import view.GameView;
import view.MainMenuView;
import java.util.Arrays;
import java.util.List;

public class GameController {
    private MainMenuView mainMenu;
    private GameView gameView;
    private GameState gameState;

    private List<Country> countries;

    public GameController() {
        this.mainMenu = new MainMenuView();
        this.countries = initializeCountries();

        mainMenu.addNewGameListener(e -> startNewGame(mainMenu));
        mainMenu.addHighScoresListener(e -> showHighScores());
        mainMenu.addExitListener(e -> System.exit(0));

        mainMenu.setVisible(true);
    }

    private void startNewGame(MainMenuView view) {
        DifficultySelectionView difficultyDialog = new DifficultySelectionView(view);
        difficultyDialog.setVisible(true);

        String difficulty = difficultyDialog.getSelectedDifficulty();

        if (difficulty != null) {
            view.setVisible(false);
            this.gameState = new GameState(countries, difficulty);
            GameView gameView = new GameView(countries);
            gameView.setVisible(true);

            startTimer(gameView);
            startVirusBehavior(gameView);
        }
    }

    private void showHighScores() {
        System.out.println("Displaying high scores...");
    }

    private List<Country> initializeCountries() {
        // Create countries
        Country spain = new Country("Spain", 47910526, null, 80, 385);
        Country portugal = new Country("Portugal", 10196709, null, 0, 360);
        Country france = new Country("France", 66548530, null, 218, 245);
        Country germany = new Country("Germany", 84552242, null, 355, 140);
        Country italy = new Country("Italy", 59342867, null, 405,335);
        Country austria = new Country("Austria", 9027999, null, 424, 232);
        Country czechRepublic = new Country("Czech Republic", 10493986, null, 445, 175);
        Country poland = new Country("Poland", 38539201, null, 505, 115);
        Country hungary = new Country("Hungary", 9570860, null, 525, 230);
        Country slovakia = new Country("Slovakia", 5379455, null, 530,190);
        Country romania = new Country("Romania", 19015088, null, 635,245);

        // Add neighbours
        spain.setNeighbours(Arrays.asList(portugal, france));
        portugal.setNeighbours(List.of(spain));
        france.setNeighbours(Arrays.asList(spain, italy, germany));
        germany.setNeighbours(Arrays.asList(france, poland, czechRepublic, austria));
        italy.setNeighbours(Arrays.asList(france, austria));
        austria.setNeighbours(Arrays.asList(italy, germany, czechRepublic, slovakia, hungary));
        czechRepublic.setNeighbours(Arrays.asList(germany, austria, hungary, slovakia, poland));
        poland.setNeighbours(Arrays.asList(germany, czechRepublic, slovakia));
        hungary.setNeighbours(Arrays.asList(austria, slovakia, romania));
        slovakia.setNeighbours(Arrays.asList(poland, czechRepublic, austria, czechRepublic));
        romania.setNeighbours(List.of(hungary));

        countries = Arrays.asList(spain, portugal, france, germany, italy, austria, czechRepublic, poland, hungary, slovakia, romania);

        return countries;
    }

    private void startVirusBehavior(GameView gameView) {
        VirusController virusController = new VirusController(gameState.getVirus());

        new Thread(() -> {
            while (!gameState.isGameOver()) {
                virusController.spreadInfectionWithinCountries(gameState.getCountries());
                virusController.spreadInfectionBetweenCountries(gameState.getCountries());

                gameView.updateView(gameState.getCountries());

                if (Math.random() < 0.1) {
                    virusController.mutateVirus();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            gameView.showGameOver();
            endGame();
        }).start();
    }

    private void startTimer(GameView gameView) {
        new Thread(() -> {
            int timeElapsed = 0;
            while (!gameState.isGameOver()) {
                try {
                    Thread.sleep(1000);
                    timeElapsed++;
                    gameView.updateTimer(timeElapsed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Game Over! Final time: " + timeElapsed + " seconds.");
        }).start();
    }



    private void endGame() {
        gameView.setVisible(false);
        mainMenu.setVisible(true);
    }

}
