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

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public  static final ILogger LOGGER = newLogger();
    private static       String  directory;
    public  static       String  getDirectory() { return directory; }

    public static void start(int port, String _directory) throws Exception {
        directory = _directory;

        RouteInitializers.cobSpecRoutes();
        ServerSocket serverSocket = newServerSocket(port);
        ExecutorService executor = newThreadPool();
        while(!serverSocket.isClosed()) {
            ISocket socketWrapper = null;

            try {
                socketWrapper = new SocketWrapper(serverSocket.accept());
                executor.execute(newThread(socketWrapper, newRequest(socketWrapper), newResponse()));
            } catch (Exception exception) {
                if (socketWrapper != null) {
                    socketWrapper.getSocket().close();
                } else {
                    serverSocket.close();
                    exception.printStackTrace();
                }
            }
        }
    }

    private static ExecutorService newThreadPool() {
        return Executors.newFixedThreadPool(16);
    }

    private static ConnectionWrapper wrapConnection(ISocket socketWrapper, IRequest request, IResponse response) throws Exception {
        return new ConnectionWrapper(socketWrapper, request, response);
    }

    private static Runnable newThread(ISocket socketWrapper, IRequest request, IResponse response) throws Exception {
        return new Thread(wrapConnection(socketWrapper, request, response));
    }

    private static ServerSocket newServerSocket(int port) throws Exception {
        return new ServerSocket(port);
    }

    private static IRequest newRequest(ISocket socketWrapper) throws Exception {
        return new HTTPRequest(socketWrapper.socketInputStream());
    }

    private static IResponse newResponse() {
        return new Response();
    }

    private static ILogger newLogger() {
        return new Logger();
    }
}
