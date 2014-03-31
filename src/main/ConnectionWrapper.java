package main;

import main.Requests.HTTPRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionWrapper implements Runnable {
    private Socket socket;
    private ICallable appHandler;
    private IRequest requestType;

    public ConnectionWrapper(Socket _socket, ICallable _appHandler) {
        this.socket = _socket;
        this.appHandler = _appHandler;
    }

    public void run() {
        try {
            IRequest request = new HTTPRequest(socketInputStream());
            Response response = appHandler.call(request);
            Responder.respond(response, socketOutputStream());

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private InputStream socketInputStream() throws IOException {
        return socket.getInputStream();
    }

    private OutputStream socketOutputStream() throws IOException {
        return socket.getOutputStream();
    }
}
