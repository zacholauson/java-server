package main;

import main.logging.ILogger;

public class ShutdownWrapper {
    public static void wrap(final ILogger systemLogger) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                FileOutput.write(System.getProperty("user.dir"), "/logs.txt", systemLogger.getEntries(), true);
            }
        }));
    }
}
