package main;

import main.parsers.ArgumentsParser;

public class Main {
    public static void main(String [] args) {
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        announceServerInitialization(argumentsParser.getPort(), argumentsParser.getDirectory());

        Server.start(argumentsParser.getPort(), argumentsParser.getDirectory());
    }

    private static void announceServerInitialization(int port, String directory) {
        System.out.println("Server starting on port: " + port + "\n" +
                           "Serving: " + directory);
    }
}
