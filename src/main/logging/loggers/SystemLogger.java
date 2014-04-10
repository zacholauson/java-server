package main.logging.loggers;

import main.logging.ILogger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SystemLogger implements ILogger {
    public ArrayList<String> logs = new ArrayList<>();

    public void addEntry(String entry) {
        logs.add(currentDateTime() + ": " + entry);
    }

    public String getEntries() {
        StringBuilder entryString = new StringBuilder();
        for(String entry : logs) {
            entryString.append(entry);
            entryString.append("\r\n");
        }
        return entryString.toString();
    }

    private String currentDateTime() {
        return new SimpleDateFormat("MM/dd/yy hh:mm:ss").format(new Date());
    }
}
