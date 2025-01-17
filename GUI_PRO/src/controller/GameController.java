package controller;

import model.Country;
import model.GameState;
import view.GameSelectionView;
import view.GameView;
import view.HighScoresView;
import view.MainMenuView;
import java.util.List;

public class GameController {
    private final MainMenuView mainMenu;
    private GameView gameView;
    private GameState gameState;
    private int totalTime;
    private final List<Country> countries;

    public GameController() {
        this.mainMenu = new MainMenuView();
        CountryController country = new CountryController();
        this.countries = country.getCountries();

        mainMenu.addNewGameListener(e -> startNewGame(mainMenu));
        mainMenu.addHighScoresListener(e -> showHighScores());
        mainMenu.addExitListener(e -> System.exit(0));

        mainMenu.setVisible(true);
    }

    private void startNewGame(MainMenuView view) {
        GameSelectionView selectionDialog = new GameSelectionView(view);
        selectionDialog.setVisible(true);

        String difficulty = selectionDialog.getSelectedDifficulty();
        String startCountry = selectionDialog.getSelectedCountry();

        if (difficulty != null) {
            for (Country country : countries) {
                country.resetState();
            }
            view.setVisible(false);
            this.gameState = new GameState(countries, difficulty, startCountry);
            this.gameView = new GameView(countries, gameState);
            gameView.setVisible(true);

            startTimer(gameView);
            startVirusBehavior(gameView);
        }
    }

    private void showHighScores() {
        HighScoresView highScores = new HighScoresView();
        highScores.setVisible(true);
    }

    private void startVirusBehavior(GameView gameView) {
        VirusController virusController = new VirusController(gameState.getVirus());

        new Thread(() -> {
            while (!gameState.isGameOver()) {
                virusController.spreadInfectionWithinCountries(gameState.getCountries());
                virusController.spreadInfectionBetweenCountries(gameState.getCountries());

                gameView.updateView();

                if (Math.random() < 0.1) {
                    virusController.mutateVirus();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            gameView.showGameOver(totalTime, gameState.getDifficulty(), gameState.isWin());
            endGame();
        }).start();
    }

    private void startTimer(GameView gameView) {
        new Thread(() -> {
            int timeElapsed = 0;
            while (!gameState.isGameOver()) {
                try {
                    Thread.sleep(1000);
                    gameState.addPoints();
                    timeElapsed++;
                    gameView.updatePoints(gameState.getPoints());
                    gameView.updateTimer(timeElapsed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            totalTime = timeElapsed;
        }).start();
    }


    private void endGame() {
        gameView.setVisible(false);
        gameView = null;
        gameState = null;
        mainMenu.setVisible(true);
    }

}
