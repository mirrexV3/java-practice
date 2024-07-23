package dev.mirrex.logger;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private static List<String> logs = new ArrayList<>();

    public static void log(String message) {
        logs.add(message);
        System.out.println("LOG: " + message);
    }

    public static void printLogs() {
        System.out.println("Logs:");
        for (String log : logs) {
            System.out.println(log);
        }
    }

    public static List<String> getLogs() {
        return logs;
    }
}
