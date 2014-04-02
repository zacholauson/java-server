package main;

import main.Requests.IRequest;
import main.Response.IResponse;
import main.Response.Responder;
import main.Routing.Router;

import java.io.OutputStream;
import java.net.Socket;

public class ConnectionWrapper implements Runnable {
    private Socket socket;
    private IRequest request;
    private OutputStream socketOutputStream;
    private IResponse response;

    public ConnectionWrapper(Socket _socket, IRequest _request, OutputStream _socketOutputStream, IResponse _response) {
        socket     = _socket;
        request    = _request;
        socketOutputStream = _socketOutputStream;
        response = _response;
    }

    public void run() {
        try {
            Responder.respond(Router.route(request, response), socketOutputStream);
        } catch (Exception exception) {
            System.out.println(exception);
        } finally {
            try {
                socket.close();
            } catch ( Exception exception ) {
                exception.printStackTrace();
            }
        }
    }
}
