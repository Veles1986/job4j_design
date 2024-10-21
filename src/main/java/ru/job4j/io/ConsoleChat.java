package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean checkExit = true;
        boolean checkWait = true;
        List<String> logSave = new ArrayList<>();
        List<String> listOfAnswers = readPhrases();
        while (checkExit) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            logSave.add("User: " + input + "\n");
            switch (input.toLowerCase()) {
                case OUT -> {
                    checkExit = false;
                }
                case STOP -> {
                    checkWait = false;
                }
                case CONTINUE -> {
                    checkWait = true;
                }
                default -> {
                    if (checkWait) {
                        String answer = listOfAnswers.get((int) Math.floor(Math.random() * listOfAnswers.size()));
                        logSave.add("Bot: " + answer + "\n");
                        System.out.println(answer);
                    }
                }
            }
        }
        saveLog(logSave);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            list = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/logChat.txt", "./data/botAnswers.txt");
        consoleChat.run();
    }
}