package dev.mirrex;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для логирования действий кофемашины.
 */
public class Logger {
    private static final List<String> LOGS = new ArrayList<>();

    /**
     * Логирование сообщения.
     *
     * @param message Сообщение для логирования
     */
    public static void log(String message) {
        LOGS.add(message);
        System.out.println("LOG: " + message);
    }

    /**
     * Вывод всех логов.
     */
    public static void printLogs() {
        System.out.println("Logs:");
        for (String log : LOGS) {
            System.out.println(log);
        }
    }
}
