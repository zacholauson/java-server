package main.logging.loggers;

import main.logging.ILogger;

import java.util.ArrayList;

public class Logger implements ILogger {
    public ArrayList<String> logs = new ArrayList<>();

    public void addEntry(String entry) {
        logs.add(entry);
    }

    public String getEntries() {
        StringBuilder entryString = new StringBuilder();
        for(String entry : logs) { entryString.append(entry); }
        return entryString.toString();
    }
}
