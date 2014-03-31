package main;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static Integer         port;
    private static String          directory;
    private static ServerSocket    serverSocket;
    private static ICallable       appHandler;
    private static ExecutorService executor;

    public static Integer      getPort()         { return port; }
    public static String       getDirectory()    { return directory; }
    public static ServerSocket getServerSocket() { return serverSocket; }
    public static ICallable    getAppHandler()   { return appHandler; }

    public static void start(int _port, String _directory, ICallable _appHandler) throws Exception {
        port         = _port;
        directory    = _directory;
        serverSocket = newServerSocket(port);
        appHandler   = _appHandler;
        executor     = newThreadPool();

        while(!serverSocket.isClosed()) {
            Socket socket = new Socket();
            try {
                socket = serverSocket.accept();
                executor.execute(newThread(socket, appHandler));
            } catch (Exception exception) {
                System.out.println(exception);
                socket.close();
            }
        }
    }

    private static ExecutorService newThreadPool() {
        return Executors.newFixedThreadPool(8);
    }

    private static ConnectionWrapper wrapConnection(Socket socket, ICallable appHandler) throws Exception {
        return new ConnectionWrapper(socket, appHandler);
    }

    private static Runnable newThread(Socket socket, ICallable appHandler) throws Exception {
        return new Thread(wrapConnection(socket, appHandler));
    }

    private static ServerSocket newServerSocket(int port) throws Exception {
        return new ServerSocket(port);
    }
}
