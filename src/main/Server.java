package main;

import main.Logging.ILogger;
import main.Logging.Loggers.Logger;
import main.Requests.Requests.HTTPRequest;
import main.Requests.IRequest;

import main.Response.IResponse;
import main.Response.Responses.Response;
import main.Routing.RouteInitializers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
            Socket socket = null;

            try {
                socket = serverSocket.accept();
                executor.execute(newThread(socket, newRequest(socket), socketOutputStream(socket), newResponse()));
            } catch (Exception exception) {
                if (socket != null) {
                    socket.close();
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

    private static ConnectionWrapper wrapConnection(Socket socket, IRequest request, OutputStream socketOutputStream, IResponse response) throws Exception {
        return new ConnectionWrapper(socket, request, socketOutputStream, response);
    }

    private static Runnable newThread(Socket socket, IRequest request, OutputStream socketOutputStream, IResponse response) throws Exception {
        return new Thread(wrapConnection(socket, request, socketOutputStream, response));
    }

    private static ServerSocket newServerSocket(int port) throws Exception {
        return new ServerSocket(port);
    }

    private static InputStream socketInputStream(Socket socket) throws IOException {
        return socket.getInputStream();
    }

    private static OutputStream socketOutputStream(Socket socket) throws IOException {
        return socket.getOutputStream();
    }

    private static IRequest newRequest(Socket socket) throws Exception {
        return new HTTPRequest(socketInputStream(socket));
    }

    private static IResponse newResponse() {
        return new Response();
    }

    private static ILogger newLogger() {
        return new Logger();
    }
}
