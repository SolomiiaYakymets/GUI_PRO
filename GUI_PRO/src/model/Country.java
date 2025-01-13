package model;

import java.util.List;

public class Country {
    private String name;
    private int population;
    private int infectedCount;
    private List<TransportRoute> routes;
    private boolean isLockedDown;
    private double infectionRate;
    private int x;
    private int y;

    public Country(String name, int population, List<TransportRoute> routes, int x, int y) {
        this.name = name;
        this.population = population;
        this.infectedCount = 0;
        this.routes = routes;
        this.isLockedDown = false;
        this.infectionRate = 0.1;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getInfectedCount() {
        return infectedCount;
    }

    public void setInfectedCount(int infectedCount) {
        this.infectedCount = infectedCount;
    }

    public List<TransportRoute> getRoutes() { return routes; }

    public void setRoutes(List<TransportRoute> routes) { this.routes = routes; }

    public boolean isLockedDown() { return isLockedDown; }

    public void setLockedDown(boolean lockedDown) { isLockedDown = lockedDown; }

    public double getInfectionRate() { return infectionRate; }

    public void setInfectionRate(double infectionRate) { this.infectionRate = infectionRate; }

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) {
        this.y = y;
    }

    public void infect(int infections) {
        if (!isLockedDown) {
            infectedCount += infections;

            if (infectedCount > population) {
                infectedCount = population;
            }
        }
    }

    public void updateInfection() {
        if (!isLockedDown) {
            int newInfections = (int) (infectedCount * infectionRate);
            infectedCount += newInfections;

            if (infectedCount > population) {
                infectedCount = population;
            }
        }
    }


}
