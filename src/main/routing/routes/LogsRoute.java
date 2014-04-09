package main.routing.routes;

import main.authorization.IAuthorize;
import main.logging.ILogger;
import main.requests.IRequest;
import main.response.IResponse;

public class LogsRoute implements IRoute {
    private IAuthorize auth;
    private ILogger    logger;

    public LogsRoute(IAuthorize auth, ILogger logger) {
        this.auth = auth;
        this.logger = logger;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        if (auth.authorized(request)) {
            response.setStatus(200);
            response.setBody(("<!DOCTYPE html><html><body>" + logger.getEntries() + "</body></html>").getBytes());
        } else {
            response.setStatus(401);
            response.setBody("<!DOCTYPE html><html><body>Authentication required to access this route</body></html>".getBytes());
        }
        return response;
    }
}
