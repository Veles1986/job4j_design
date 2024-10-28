package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[] {"Worker", "Married"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
        final Car car = new Car(
                "Porsche 911",
                2020,
                false,
                new Engine(6, 525F),
                new String[]{"James", "Cameron"}
        );
        final Gson gsonNew = new GsonBuilder().create();
        System.out.println(gsonNew.toJson(car));
        final String carJson =
                        "{"
                        + "\"model\":\"Porsche 911\","
                        + "\"yearOfManufacture\":2020,"
                        + "\"forSale\":false,"
                        + "\"engine\":"
                        + "{"
                        + "\"numberOfCylinders\":6,"
                        + "\"horsePower\":525.0"
                        + "},"
                        + "\"owners\":"
                        + "[\"James\", \"Cameron\"]"
                        + "}";
        final Car carMod = gsonNew.fromJson(carJson, Car.class);
        System.out.println(carMod.toString());
    }
}
