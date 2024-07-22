package dev.mirrex.logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для логирования действий кофемашины.
 */
public class Logger {
    private static List<String> logs = new ArrayList<>();

    /**
     * Логирование сообщения.
     *
     * @param message Сообщение для логирования
     */
    public static void log(String message) {
        logs.add(message);
        System.out.println("LOG: " + message);
    }

    /**
     * Вывод всех логов.
     */
    public static void printLogs() {
        System.out.println("Logs:");
        for (String log : logs) {
            System.out.println(log);
        }
    }
}
