package main;

import main.logging.ILogger;
import main.logging.loggers.Logger;
import main.requests.requests.HTTPRequest;
import main.requests.IRequest;

import main.response.IResponse;
import main.response.responses.Response;
import main.routing.RouteInitializers;
import main.socket.ISocket;
import main.socket.socketWrappers.SocketWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public  static final ILogger LOGGER = newLogger();
    private static       String  directory;
    public  static       ServerSocket serverSocket;
    public  static       String  getDirectory() { return directory; }

    public static void start(int port, String _directory) {
        directory = _directory;
        serverSocket = newServerSocket(port);

        RouteInitializers.cobSpecRoutes();
        ExecutorService executor = Executors.newFixedThreadPool(16);
        while(!serverSocket.isClosed()) {
            ISocket socketWrapper = wrapClientSocket(serverSocket);
            executor.execute(newThread(socketWrapper, newRequest(socketWrapper), newResponse()));
        }
    }

    private static IResponse newResponse() {
        return new Response();
    }

    private static ILogger newLogger() {
        return new Logger();
    }

    private static ConnectionWrapper wrapConnection(ISocket socketWrapper, IRequest request, IResponse response) {
        return new ConnectionWrapper(socketWrapper, request, response);
    }

    private static Runnable newThread(ISocket socketWrapper, IRequest request, IResponse response) {
        return new Thread(wrapConnection(socketWrapper, request, response));
    }

    private static ServerSocket newServerSocket(int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            new Exception("Failed to start server socket").printStackTrace();
            e.printStackTrace();
        }
        return serverSocket;
    }

    private static ISocket wrapClientSocket(ServerSocket serverSocket) {
        ISocket socketWrapper = null;
        try {
            socketWrapper = new SocketWrapper(serverSocket.accept());
        } catch (IOException e) {
            new Exception("Failed to create client connection").printStackTrace();
            e.printStackTrace();
        }
        return socketWrapper;
    }

    private static IRequest newRequest(ISocket socketWrapper) {
        IRequest request = null;
        try {
            request = new HTTPRequest(socketWrapper.socketInputStream());
        } catch (IOException e) {
            new Exception("Failed to parse request").printStackTrace();
            e.printStackTrace();
        }
        return request;
    }
}
