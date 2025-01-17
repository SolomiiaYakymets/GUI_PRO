package model.transport;

import model.Country;

import javax.swing.*;

public class Car implements TransportMethod{
    private static double speed = 1;

    @Override
    public String getName() {
        return "Car";
    }
    @Override
    public int getSpeedModifier() {
        return (int) speed;
    }
    public static void applySpeedUpgrade(double multiplier) {
        speed *= multiplier;
    }
}
