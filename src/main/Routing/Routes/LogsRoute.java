package main.routing.routes;

import main.authorization.IAuthorize;
import main.requests.IRequest;
import main.response.IResponse;
import main.Server;

public class LogsRoute implements IRoute {
    private IAuthorize auth;

    public LogsRoute(IAuthorize _auth) {
        auth = _auth;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        if (auth.authorized(request)) {
            response.setStatus(200);
            response.setBody(("<!DOCTYPE html><html><body>" + Server.LOGGER.getEntries() + "</body></html>").getBytes());
        } else {
            response.setStatus(401);
            response.setBody("<!DOCTYPE html><html><body>Authentication required to access this route</body></html>".getBytes());
        }
        return response;
    }
}
