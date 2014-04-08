package main.logging;

public interface ILogger {
    public void addEntry(String entry);
    public String getEntries();
}
