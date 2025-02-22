package ru.job4j.serialization.json;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.*;

import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private String model;
    @XmlAttribute
    private int yearOfManufacture;
    @XmlAttribute
    private boolean forSale;
    private Engine engine;
    @XmlElementWrapper(name = "owners")
    @XmlElement(name = "owners")
    private String[] owners;

    public Car(String model, int yearOfManufacture, boolean forSale, Engine engine, String[] owners) {
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.forSale = forSale;
        this.engine = engine;
        this.owners = owners;
    }

    public Car() {
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

    public static void main(String[] args) throws JAXBException {
        final Car car = new Car(
                "Porsche 911",
                2020,
                false,
                new Engine(6, 525F),
                new String[]{"James", "Cameron"}
        );

        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter stringWriter = new StringWriter()) {
            marshaller.marshal(car, stringWriter);
            String result = stringWriter.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
