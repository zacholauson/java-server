package main.Routing.Routes;

import main.Authorization.IAuthorize;
import main.Requests.IRequest;
import main.Response.IResponse;
import main.Response.ResponseCodes;
import main.Server;

public class LogsRoute implements IRoute {
    private IAuthorize auth;

    public LogsRoute(IAuthorize _auth) {
        auth = _auth;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        if (auth.authorized(request)) {
            response.setHeaders(ResponseCodes.codeString(200) + "\r\n");
            response.setBody(("<!DOCTYPE html><html><body>" + Server.LOGGER.getEntries() + "</body></html>").getBytes());
        } else {
            response.setHeaders(ResponseCodes.codeString(401) + "\r\n");
            response.setBody("<!DOCTYPE html><html><body>Authentication required to access this route</body></html>".getBytes());
        }
        return response;
    }
}
