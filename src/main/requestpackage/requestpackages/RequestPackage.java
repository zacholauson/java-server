package main.requestpackage.requestpackages;

import main.requestpackage.IRequestPackage;
import main.logging.ILogger;
import main.requests.IRequest;
import main.routing.IRouter;
import main.routing.routes.IRoute;

public class RequestPackage implements IRequestPackage {
    private final IRouter  ROUTER;
    private final ILogger  LOGGER;
    private final IRequest REQUEST;

    public RequestPackage(IRouter router, ILogger logger, IRequest request) {
        ROUTER  = router;
        LOGGER  = logger;
        REQUEST = request;
    }

    public IRequest request() {
        return REQUEST;
    }

    public IRoute routeRequest() {
        return ROUTER.route(REQUEST);
    }

    public void logRequest() {
        LOGGER.addEntry(requestFirstLineOfHeaderString());
    }

    private String requestFirstLineOfHeaderString() {
        return REQUEST.getHeaderString().split("\r\n")[0];
    }
}
