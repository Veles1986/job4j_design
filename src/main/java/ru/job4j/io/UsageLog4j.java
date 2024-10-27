package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        float height = 1.72F;
        double capital = 1000000000.00d;
        boolean isMarried = true;
        char bloodGroup = '1';
        byte numberOfChildren = 4;
        long numberOfFollowers = 12000000L;
        short totalNumberOFStudents = 10000;
        LOG.debug("User info name : {}, age : {}, height : {}, capital : {}, isMarried : {}, bloodGroup : {}, numberOfChildren : {}, numberOfFollowers : {}, totalNumberOfStudents : {}", name, age, height, capital, isMarried, bloodGroup, numberOfChildren, numberOfFollowers, totalNumberOFStudents);
    }
}