package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] numbers = text.toString().split(System.lineSeparator());
        for (String number : numbers) {
            if (Integer.parseInt(number) % 2 == 0) {
                System.out.println(number);
            }
        }
    }
}