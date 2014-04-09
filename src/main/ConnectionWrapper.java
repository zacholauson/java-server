package main;

import main.logging.ILogger;
import main.requests.IRequest;
import main.response.IResponse;
import main.response.Responder;
import main.routing.IRouter;
import main.routing.routes.IRoute;
import main.socket.ISocket;

public class ConnectionWrapper implements Runnable {
    private ISocket   socketWrapper;
    private IRequest  request;
    private IResponse response;
    private ILogger   logger;
    private IRouter   router;

    public ConnectionWrapper(ISocket socket, IRequest request, IResponse response, ILogger logger, IRouter router) {
        this.socketWrapper = socket;
        this.request       = request;
        this.response      = response;
        this.logger        = logger;
        this.router        = router;
    }

    public void run() {
        logger.addEntry(topHeaderString(request));
        IRoute route = router.route(request);
        route.buildResponse(request, response);
        Responder.respond(response, socketWrapper.socketOutputStream());
        socketWrapper.close();
    }

    private String topHeaderString(IRequest request) {
        return request.getHeaderString().split("\r\n")[0];
    }
}
