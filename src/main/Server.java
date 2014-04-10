package main;

import main.requestpackage.IRequestPackage;
import main.requestpackage.requestpackages.RequestPackage;
import main.responsepackage.IResponsePackage;
import main.responsepackage.responsepackages.ResponsePackage;
import main.logging.ILogger;
import main.logging.loggers.Logger;
import main.requests.requests.HTTPRequest;

import main.respond.responders.Responder;
import main.response.responses.Response;
import main.routing.IRouter;
import main.routing.RouteInitializer;
import main.routing.routers.Router;
import main.socket.ISocket;
import main.socket.socketWrappers.SocketWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private  static final IRouter         ROUTER   = new Router();
    private  static final ILogger         LOGGER   = new Logger();
    public   static       ExecutorService executor = Executors.newFixedThreadPool(16);
    public   static       ServerSocket    serverSocket;

    public static void init(int port, String baseDirectory) {
        new RouteInitializer(ROUTER, LOGGER, baseDirectory).cobSpecRoutes();
        serverSocket = newServerSocket(port);
    }

    public static void start() {
        while(!serverSocket.isClosed()) {
            ISocket socketWrapper            = wrapClientSocket(serverSocket);
            IRequestPackage requestPackage   = new RequestPackage(ROUTER, LOGGER,  new HTTPRequest(socketWrapper.socketInputStream()));
            IResponsePackage responsePackage = new ResponsePackage(new Response(), new Responder(socketWrapper.socketOutputStream()));
            executor.execute(wrapConnectionInNewThread(socketWrapper, requestPackage, responsePackage));
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

    private static Runnable wrapConnectionInNewThread(ISocket socketWrapper, IRequestPackage requestPackage, IResponsePackage responsePackage) {
        return new Thread(new ConnectionWrapper(socketWrapper, requestPackage, responsePackage));
    }
}
