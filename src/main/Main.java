package main;

public class Main {
    public static void main(String [] args) throws Exception {
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        announceServerInitialization(argumentsParser.getPort(), argumentsParser.getDirectory());

        Server.start(argumentsParser.getPort(), argumentsParser.getDirectory(), newAppHandler());
    }

    private static void announceServerInitialization(int _port, String _directory) {
        System.out.println("Server starting on port: " + _port +
                           "Serving: " + _directory);
    }

    private static ICallable newAppHandler() {
        return new RequestHandler();
    }
}
