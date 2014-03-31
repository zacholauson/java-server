package main;

import main.Requests.HTTPRequest;
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
            InputStream  input  = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            IRequest request = new HTTPRequest(input);
            System.out.println(request.headerString());






//            iResponse response = new Controller( new main.RequestsPRequest(in), socket ).response();
//            Responder.respond( response, socket );
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
