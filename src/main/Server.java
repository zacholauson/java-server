package main;

import main.logging.ILogger;
import main.logging.loggers.Logger;
import main.requests.requests.HTTPRequest;
import main.requests.IRequest;

import main.response.IResponse;
import main.response.responses.Response;
import main.routing.IRouter;
import main.routing.RouteInitializers;
import main.routing.Router;
import main.socket.ISocket;
import main.socket.socketWrappers.SocketWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private  static final IRouter ROUTER = new Router();
    private  static final ILogger LOGGER = new Logger();
    public   static       ServerSocket serverSocket;

    public static void start(int port, String baseDirectory) {
        RouteInitializers.cobSpecRoutes(ROUTER, baseDirectory, LOGGER);
        ExecutorService executor = Executors.newFixedThreadPool(16);

        serverSocket = newServerSocket(port);
        while(!serverSocket.isClosed()) {
            ISocket socketWrapper = wrapClientSocket(serverSocket);
            executor.execute(newThread(socketWrapper, newRequest(socketWrapper), new Response(), LOGGER, ROUTER));
        }
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

    private static Runnable newThread(ISocket socketWrapper, IRequest request, IResponse response, ILogger logger, IRouter router) {
        return new Thread(wrapConnection(socketWrapper, request, response, logger, router));
    }

    private static ConnectionWrapper wrapConnection(ISocket socketWrapper, IRequest request, IResponse response, ILogger logger, IRouter router) {
        return new ConnectionWrapper(socketWrapper, request, response, logger, router);
    }

    private static IRequest newRequest(ISocket socketWrapper) {
        return new HTTPRequest(socketWrapper.socketInputStream());
    }
}
