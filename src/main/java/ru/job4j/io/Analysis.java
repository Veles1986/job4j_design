package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        String statusCheck = "";
        String dateFirst = "";
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)
                     ))
        ) {
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                String[] lineSplit = line.split(" ");
                statusCheck = statusCheck.isEmpty() ? lineSplit[0] : statusCheck;
                if (("200".equals(statusCheck) || "300".equals(statusCheck)
                        && ("400".equals(lineSplit[0]) || "500".equals(lineSplit[0])))) {
                    statusCheck = lineSplit[0];
                    dateFirst = lineSplit[1];
                }
                if (("400".equals(statusCheck) || "500".equals(statusCheck)
                        && ("200".equals(lineSplit[0]) || "300".equals(lineSplit[0])))) {
                    output.printf("%s;%s%n", dateFirst, lineSplit[1]);
                    statusCheck = lineSplit[0];
                    dateFirst = lineSplit[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}