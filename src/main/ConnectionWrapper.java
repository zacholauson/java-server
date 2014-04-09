package main;

import main.logging.ILogger;
import main.requests.IRequest;
import main.respond.IRespond;
import main.response.IResponse;
import main.routing.IRouter;
import main.routing.routes.IRoute;
import main.socket.ISocket;

public class ConnectionWrapper implements Runnable {
    private ISocket   socketWrapper;
    private IRequest  request;
    private IResponse response;
    private ILogger   logger;
    private IRouter   router;
    private IRespond  responder;

    public ConnectionWrapper(ISocket socket, IRequest request, IResponse response, ILogger logger, IRouter router, IRespond responder) {
        this.socketWrapper = socket;
        this.request       = request;
        this.response      = response;
        this.logger        = logger;
        this.router        = router;
        this.responder     = responder;
    }

    public void run() {
        logger.addEntry(topHeaderString(request));
        IRoute route = router.route(request);
        route.buildResponse(request, response);
        responder.respond(response);
        socketWrapper.close();
    }

    private String topHeaderString(IRequest request) {
        return request.getHeaderString().split("\r\n")[0];
    }
}
