package main;

import main.Controllers.RootController;
import main.Controllers.SleepController;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static Integer                      port;
    private static String                       directory;
    private static ServerSocket                 serverSocket;
    private static ICallable                    appHandler;
    private static ExecutorService              executor;
    private static HashMap<String, IController> routesMap = new HashMap<>();

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
        routesMap    = buildRoutes();

        while(!serverSocket.isClosed()) {
            Socket socket = new Socket();
            try {
                socket = serverSocket.accept();
                executor.execute(newThread(socket, appHandler, routesMap));
            } catch (Exception exception) {
                exception.printStackTrace();
                socket.close();
            }
        }
    }

    private static ExecutorService newThreadPool() {
        return Executors.newFixedThreadPool(8);
    }

    private static ConnectionWrapper wrapConnection(Socket socket,
                                                    ICallable appHandler,
                                                    HashMap<String, IController> routesMap) throws Exception {

        return new ConnectionWrapper(socket, appHandler, routesMap);
    }

    private static Runnable newThread(Socket socket,
                                      ICallable appHandler,
                                      HashMap<String, IController> routesMap) throws Exception {

        return new Thread(wrapConnection(socket, appHandler, routesMap));
    }

    private static ServerSocket newServerSocket(int port) throws Exception {
        return new ServerSocket(port);
    }

    private static HashMap<String, IController> buildRoutes(){
        routesMap.put("/",      new RootController());
        routesMap.put("/sleep", new SleepController());
        return routesMap;
    }
}
