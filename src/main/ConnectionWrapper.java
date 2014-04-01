package main;

import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

public class ConnectionWrapper implements Runnable {
    private Socket socket;
    private ICallable appHandler;
    private HashMap<String, IController> routesMap;
    private IRequest request;
    private OutputStream socketOutputStream;

    public ConnectionWrapper(Socket _socket,
                             ICallable _appHandler,
                             HashMap<String, IController> _routesMap,
                             IRequest _request,
                             OutputStream _socketOutputStream) {

        socket     = _socket;
        appHandler = _appHandler;
        routesMap  = _routesMap;
        request    = _request;
        socketOutputStream = _socketOutputStream;
    }

    public void run() {
        try {
            Response response = appHandler.call(request, routesMap);
            Responder.respond(response, socketOutputStream);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch ( Exception exception ) {
                exception.printStackTrace();
            }
        }
    }
}
