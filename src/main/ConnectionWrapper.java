package main;

import main.requests.IRequest;
import main.response.IResponse;
import main.response.Responder;
import main.routing.Router;
import main.routing.routes.IRoute;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionWrapper implements Runnable {
    private Socket       socket;
    private IRequest     request;
    private OutputStream socketOutputStream;
    private IResponse    response;

    public ConnectionWrapper(Socket socket, IRequest request, OutputStream socketOutputStream, IResponse response) {
        this.socket             = socket;
        this.request            = request;
        this.socketOutputStream = socketOutputStream;
        this.response           = response;
    }

    public void run() {
        Server.LOGGER.addEntry(topHeaderString(request));

        try {
            IRoute route = Router.route(request);
            route.buildResponse(request, response);
            Responder.respond(response, socketOutputStream);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch ( IOException exception ) {
                exception.printStackTrace();
            }
        }
    }

    private String topHeaderString(IRequest request) {
        return request.getHeaderString().split("\r\n")[0];
    }
}
