package controller;

import view.MainMenuView;

public class MainMenuController {
    private MainMenuView mainMenu;

    public MainMenuController() {
        mainMenu = new MainMenuView();

        mainMenu.addNewGameListener(e -> startNewGame());
        mainMenu.addHighScoresListener(e -> showHighScores());
        mainMenu.addExitListener(e -> System.exit(0));
    }

    private void startNewGame() {
        System.out.println("New game started!");
    }

    private void showHighScores() {
        System.out.println("Displaying high scores...");
    }
}
