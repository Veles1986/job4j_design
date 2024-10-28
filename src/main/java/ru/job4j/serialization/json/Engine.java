package ru.job4j.serialization.json;

public class Engine {

    private int numberOfCylinders;
    private float horsePower;

    @Override
    public String toString() {
        return "Engine{"
                + "numberOfCylinders=" + numberOfCylinders
                + ", horsePower=" + horsePower
                + '}';
    }

    public Engine(int numberOfCylinders, float horsePower) {
        this.numberOfCylinders = numberOfCylinders;
        this.horsePower = horsePower;
    }

    public int getNumberOfCylinders() {
        return numberOfCylinders;
    }

    public void setNumberOfCylinders(int numberOfCylinders) {
        this.numberOfCylinders = numberOfCylinders;
    }

    public float getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(float horsePower) {
        this.horsePower = horsePower;
    }
}
