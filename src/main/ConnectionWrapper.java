package main;

import main.requests.IRequest;
import main.response.IResponse;
import main.response.Responder;
import main.routing.Router;
import main.routing.routes.IRoute;
import main.socket.ISocket;

public class ConnectionWrapper implements Runnable {
    private ISocket      socketWrapper;
    private IRequest     request;
    private IResponse    response;

    public ConnectionWrapper(ISocket socket, IRequest request, IResponse response) {
        this.socketWrapper      = socket;
        this.request            = request;
        this.response           = response;
    }

    public void run() {
        Server.LOGGER.addEntry(topHeaderString(request));

        try {
            IRoute route = Router.route(request);
            route.buildResponse(request, response);
            Responder.respond(response, socketWrapper.socketOutputStream());
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            socketWrapper.close();
        }
    }

    private String topHeaderString(IRequest request) {
        return request.getHeaderString().split("\r\n")[0];
    }
}
