package controller;

import model.Country;
import model.TransportRoute;
import model.Virus;

import java.util.List;

public record VirusController(Virus virus) {

    public void spreadInfectionWithinCountries(List<Country> countries) {
        for (Country country : countries) {
            if (!country.isFullyInfected()) {
                country.infect(calculateInfectionsToSpread(country));
            }
        }
    }

    public void spreadInfectionBetweenCountries(List<Country> countries) {
        for (Country origin : countries) {
            if (origin.getInfectedCount() > 0) {
                for (TransportRoute route : origin.getRoutes()) {
                    Country destination = route.getEndCountry();
                    if (!destination.isFullyInfected() && !route.isBlocked()) {
                        destination.infect(calculateInfectionsToSpread(origin) * route.getMethod().getSpeedModifier());
                    }
                }
            }
        }
    }

    public void mutateVirus() {
        virus.mutate();
    }


    private int calculateInfectionsToSpread(Country origin) {
        return (int) Math.round(origin.getInfectedCount() * virus.getSpreadRate());
    }
}
