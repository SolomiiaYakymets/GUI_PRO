package model;

import java.util.List;

public class GameState {
    private List<Country> countries;
    private int points;
    private String difficulty;
    private boolean gameOver;
    private Virus virus;

    public GameState(List<Country> countries, String difficulty) {
        this.countries = countries;
        this.difficulty = difficulty;
        this.points = 0;
        this.gameOver = false;
        this.virus = new Virus(0.05, 0.1, 1);
        configureDifficulty(difficulty);
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
    
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Virus getVirus() {
        return virus;
    }

    public void setVirus(Virus virus) {
        this.virus = virus;
    }
    

    private void configureDifficulty(String difficulty) {
        switch (difficulty) {
            case "Easy":
                virus.setSpreadRate(0.02);
                break;
            case "Medium":
                virus.setSpreadRate(0.05);
                break;
            case "Hard":
                virus.setSpreadRate(0.1);
                break;
        }
    }

    public void updateInfections() {
        for (Country country : countries) {
            country.updateInfection();
        }

        //for (Country origin : countries) {
        //    virus.spreadToNeighbors(origin, countries);
        //}
    }

    public boolean isGameOver() {
        boolean allInfected = true;
        for (Country country : countries) {
            if (!country.isFullyInfected()) {
                allInfected = false;
                break;
            }
        }

        this.gameOver = allInfected;
        return allInfected;
    }

    public void update() {
    }

    /*
    public boolean applyUpgrade(Country country, Upgrade upgrade) {
        if (points >= upgrade.getCost()) {
            points -= upgrade.getCost();
            upgrade.apply(country);
            return true; // Upgrade applied successfully
        }
        return false; // Not enough points
    }
     */
}
