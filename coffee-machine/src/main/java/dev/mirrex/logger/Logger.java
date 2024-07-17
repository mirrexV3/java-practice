package dev.mirrex.logger;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private final List<String> log;

    public Logger() {
        this.log = new ArrayList<>();
    }

    public void addLog(String operation) {
        log.add(operation);
    }

    public List<String> getLog() {
        return new ArrayList<>(log);
    }
}
