package controller;

import model.Country;
import model.transport.Airplane;
import model.transport.Car;
import model.transport.Train;
import model.transport.TransportMethod;

import java.util.Arrays;
import java.util.List;

public class CountryController {
    private List<Country> countries;
    public CountryController() {
        this.countries = initializeCountries();
    }

    private List<Country> initializeCountries() {
        // Create countries
        Country spain = new Country("Spain", 47910526, 80, 385);
        Country portugal = new Country("Portugal", 10196709, 0, 360);
        Country france = new Country("France", 66548530, 218, 245);
        Country germany = new Country("Germany", 84552242, 355, 140);
        Country italy = new Country("Italy", 59342867, 405,335);
        Country austria = new Country("Austria", 9027999, 424, 232);
        Country czechRepublic = new Country("Czech Republic", 10493986, 445, 175);
        Country poland = new Country("Poland", 38539201, 505, 115);
        Country hungary = new Country("Hungary", 9570860, 525, 230);
        Country slovakia = new Country("Slovakia", 5379455, 530,190);
        Country romania = new Country("Romania", 19015088, 635,245);

        // Create transport methods
        TransportMethod airplane = new Airplane();
        TransportMethod train = new Train();
        TransportMethod car = new Car();

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

        // Add routes
        spain.addRoute(portugal, car);
        spain.addRoute(poland, airplane);
        portugal.addRoute(france, car);
        portugal.addRoute(hungary,airplane);
        france.addRoute(germany, train);
        france.addRoute(hungary, airplane);
        germany.addRoute(czechRepublic, car);
        germany.addRoute(italy, airplane);
        italy.addRoute(france, train);
        italy.addRoute(austria, car);
        austria.addRoute(czechRepublic, train);
        austria.addRoute(slovakia, car);
        czechRepublic.addRoute(spain, airplane);
        czechRepublic.addRoute(romania,train);
        poland.addRoute(germany, train);
        poland.addRoute(portugal, airplane);
        hungary.addRoute(romania, car);
        hungary.addRoute(slovakia, train);
        slovakia.addRoute(poland, car);
        slovakia.addRoute(italy, airplane);
        romania.addRoute(spain, airplane);
        romania.addRoute(austria, car);

        countries = Arrays.asList(spain, portugal, france, germany, italy, austria, czechRepublic, poland, hungary, slovakia, romania);

        return countries;
    }

    public List<Country> getCountries() {
        return countries;
    }
}
