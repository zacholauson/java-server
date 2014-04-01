package main;

import main.Requests.HTTPRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

public class ConnectionWrapper implements Runnable {
    private Socket socket;
    private ICallable appHandler;
    private HashMap<String, IController> routesMap;

    public ConnectionWrapper(Socket _socket, ICallable _appHandler, HashMap<String, IController> _routesMap) {
        this.socket = _socket;
        this.appHandler = _appHandler;
        this.routesMap = _routesMap;
    }

    public void run() {
        try {
            IRequest request  = new HTTPRequest(socketInputStream());
            Response response = appHandler.call(request, routesMap);
            Responder.respond(response, socketOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch ( Exception exception ) {
                exception.printStackTrace();
            }
        }
    }

    private InputStream socketInputStream() throws IOException {
        return socket.getInputStream();
    }

    private OutputStream socketOutputStream() throws IOException {
        return socket.getOutputStream();
    }
}
