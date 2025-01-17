package model.transport;

import model.Country;

import javax.swing.*;

public class Airplane implements TransportMethod{
    private static double speed = 3;
    @Override
    public String getName() {
        return "Airplane";
    }
    @Override
    public int getSpeedModifier() {
        return (int) speed;
    }
    public static void applySpeedUpgrade(double multiplier) {
        speed *= multiplier;
    }
}
