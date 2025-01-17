package model;

import model.transport.TransportMethod;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String name;
    private int population;
    private int infectedCount;
    private List<TransportRoute> routes;
    private boolean isLockedDown;
    private List<Country> neighbours;
    private int x;
    private int y;

    public Country(String name, int population, int x, int y) {
        this.name = name;
        this.population = population;
        this.infectedCount = 0;
        this.routes = new ArrayList<>();
        this.isLockedDown = false;
        this.neighbours = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getInfectedCount() {
        return infectedCount;
    }

    public List<TransportRoute> getRoutes() { return routes; }

    public void addRoute(Country to, TransportMethod method) {
        routes.add(new TransportRoute(this, to, method));
    }

    public void setNeighbours(List<Country> neighbours) {
        this.neighbours = neighbours;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void resetState() {
        this.infectedCount = 0;
        isLockedDown = false;
    }


    public void infect(int infections) {
        if (!isLockedDown) {
            infectedCount += infections;

            if (infectedCount > population) {
                infectedCount = population;
            }
        }
    }

    public void curePeople(int numberOfPeople) {
        if (numberOfPeople < infectedCount){
            infectedCount -= numberOfPeople;
        } else {
            infectedCount = 0;
        }
    }

    public boolean isFullyInfected() {
        return infectedCount >= population;
    }
}
