package model;

import model.transport.Airplane;
import model.transport.Car;
import model.transport.Train;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GameState {
    private final List<Country> countries;
    private int points;
    private final String difficulty;
    private boolean gameOver;
    private Virus virus;

    public GameState(List<Country> countries, String difficulty, String startCountry) {
        this.countries = countries;
        this.points = 200;
        this.difficulty = difficulty;
        this.gameOver = false;
        this.virus = new Virus(0, 0.1);
        Country country = findStartCountry(countries, startCountry);

        if (country != null) {
            country.infect(10);
        }

        configureDifficulty(difficulty);
    }

    public List<Country> getCountries() {
        return countries;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Virus getVirus() {
        return virus;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints() {
        this.points++;
    }

    private Country findStartCountry(List<Country> countries, String startCountry) {
        if (Objects.equals(startCountry, "Random")) {
            Random rand = new Random();
            return countries.get(rand.nextInt(countries.size()));
        } else {
            for (Country country : countries) {
                if (Objects.equals(country.getName(), startCountry)) {
                    return country;
                }
            }
        }
        return null;
    }
    

    private void configureDifficulty(String difficulty) {
        switch (difficulty) {
            case "Easy":
                virus.setSpreadRate(0.04);
                break;
            case "Medium":
                virus.setSpreadRate(0.06);
                break;
            case "Hard":
                virus.setSpreadRate(0.8);
                break;
        }
    }

    public boolean isGameOver() {
        boolean allInfected = true;
        boolean noInfected = true;

        for (Country country : countries) {
            if (!country.isFullyInfected()) {
                allInfected = false;
            }
            if (country.getInfectedCount() > 0) {
                noInfected = false;
            }
        }

        this.gameOver = allInfected || noInfected;
        return this.gameOver;
    }

    public boolean isWin() {
        boolean noInfected = true;
        if (isGameOver()){
            for (Country country : countries) {
                if (country.getInfectedCount() > 0) {
                    noInfected = false;
                    break;
                }
            }
        }
        return noInfected;
    }

    public boolean purchaseUpgrade(Upgrade upgrade, String parameter) {
        if (points >= upgrade.getCost()) {
            points -= (int) upgrade.getCost();

            applyUpgradeEffect(upgrade, parameter);
            return true;
        } else {
            return false;
        }
    }


    private void applyUpgradeEffect(Upgrade upgrade, String parameter) {
        switch (upgrade.getName()) {
            case "Slower all transport":
                Airplane.applySpeedUpgrade(0.7);
                Train.applySpeedUpgrade(0.7);
                Car.applySpeedUpgrade(0.7);
                break;
            case "Slower virus spread":
                virus.upgradeSpreadRate(0.5);
                break;
            case "Block one type of transport":
                for (Country country : countries) {
                    for (TransportRoute route : country.getRoutes()) {
                        if (route.getMethod().getName().equalsIgnoreCase(parameter)) {
                            route.blockRoute();
                        }
                    }
                }
                break;
            case "Cure all people in one country":
                for (Country country : countries) {
                    if (country.getName().equalsIgnoreCase(parameter)) {
                        country.curePeople(country.getPopulation());
                        return;
                    }
                }
                break;
            case "Quarantine a country":
                for (Country country : countries) {
                    if (country.getName().equalsIgnoreCase(parameter)) {
                        for (TransportRoute route : country.getRoutes()) {
                            if (route.getStartCountry().equals(country) || route.getEndCountry().equals(country)) {
                                route.blockRoute();
                            }
                        }
                        return;
                    }
                }
                break;
            case "Cure 1000 people":
                for (Country country : countries) {
                    if (country.getName().equalsIgnoreCase(parameter)) {
                        country.curePeople(1000);
                        return;
                    }
                }
                break;
            case "Slower one type of transport":
                switch (parameter.toLowerCase()) {
                    case "airplane":
                        Airplane.applySpeedUpgrade(0.7);
                        break;
                    case "train":
                        Train.applySpeedUpgrade(0.7);
                        break;
                    case "car":
                        Car.applySpeedUpgrade(0.7);
                        break;
                    default:
                        break;
                }
                break;
            case "Increase resistance to mutation":
                virus.setMutationRate(virus.getMutationRate() * 0.5);
                break;
            case "Cure random number of people":
                Random rand = new Random();

                for (Country country : getCountries()) {
                    int randomNumOfPeople = rand.nextInt(country.getPopulation()) + 1;
                    country.curePeople(randomNumOfPeople);
                }
                break;
            default:
                break;
        }
    }
}
