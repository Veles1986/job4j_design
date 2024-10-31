package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Engine {

    @XmlAttribute
    private int numberOfCylinders;
    @XmlAttribute
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

    public Engine() {
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
