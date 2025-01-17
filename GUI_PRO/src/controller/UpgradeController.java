package controller;

import model.Upgrade;

import java.util.ArrayList;
import java.util.List;

public class UpgradeController {
    private List<Upgrade> upgrades;

    public UpgradeController() {
        upgrades  = new ArrayList<>();
        upgrades.add(new Upgrade("Slower all transport", 600,
                "Slows down the speed of transport. Virus spreads more slowly between countries.",
                false, null
        ));
        upgrades.add(new Upgrade("Slower virus spread", 400,
                "Slows down the speed of virus spread between people.", false, null
        ));
        upgrades.add(new Upgrade("Block one type of transport", 300,
                "Blocks one type of transport between countries ( airplane, car or bus ).", true,
                "Choose a type of transport to block: "
        ));
        upgrades.add(new Upgrade("Cure all people in one country", 350,
                "Cures all people in one country. ", true,
                "Chooses a country to cure all people in: "
        ));
        upgrades.add(new Upgrade("Quarantine a country", 1000,
                "Closes one country for a quarantine.", true,
                "Choose a country to Quarantine: "
        ));
        upgrades.add(new Upgrade("Cure 1000 people", 100,
                "Cures 1000 people in one country.", true,
                "Choose a country to cure 1000 people in: "
        ));
        upgrades.add(new Upgrade("Slower one type of transport", 200,
                "Slows down one type of transport ( airplane, car or bus ).", true,
                "Choose a type of transport to slower: "
        ));
        upgrades.add(new Upgrade("Increase resistance to mutation", 500,
                "Increases resistance of the virus to mutation", false,
                null
        ));
        upgrades.add(new Upgrade("Cure random number of people", 600,
                "Cures random number of people in each country.", false,
                null
        ));
    }

    public List<Upgrade> getAvailableUpgrades() {
        return upgrades;
    }
}
