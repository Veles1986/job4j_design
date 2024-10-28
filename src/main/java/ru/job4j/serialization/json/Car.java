package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private String model;
    private int yearOfManufacture;
    private boolean forSale;
    private Engine engine;
    private String[] owners;

    public Car(String model, int yearOfManufacture, boolean forSale, Engine engine, String[] owners) {
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.forSale = forSale;
        this.engine = engine;
        this.owners = owners;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    @Override
    public String toString() {
        return "Car{"
                + "model='" + model + '\''
                + ", yearOfManufacture=" + yearOfManufacture
                + ", forSale=" + forSale
                + ", engine=" + engine
                + ", owners=" + Arrays.toString(owners)
                + '}';
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String[] getOwners() {
        return owners;
    }

    public void setOwners(String[] owners) {
        this.owners = owners;
    }
}
