package controller;

import model.Country;
import model.Virus;

import java.util.List;

public class VirusController {
    private Virus virus;

    public VirusController(Virus virus) {
        this.virus = virus;
    }

    public void spreadInfectionWithinCountries(List<Country> countries) {
        for (Country country : countries) {
            if (!country.isFullyInfected()) {
                country.updateInfection();
            }
        }
    }

    public void spreadInfectionBetweenCountries(List<Country> countries) {
        for (Country origin : countries) {
            if (origin.getInfectedCount() > 0) {
                for (Country neighbor : origin.getNeighbours()) {
                    if (!neighbor.isFullyInfected()) {
                        int infectionsToSpread = calculateInfectionsToSpread(origin);
                        neighbor.infect(infectionsToSpread);
                    }
                }
            }
        }
    }

    public void mutateVirus() {
        virus.mutate();
        System.out.println("Virus mutated! New spread rate: " + virus.getSpreadRate());
    }


    private int calculateInfectionsToSpread(Country origin) {
        return (int) (origin.getInfectedCount() * virus.getSpreadRate());
    }

    public Virus getVirus() {
        return virus;
    }
}
