package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;
import main.Response.ResponseCodes;

public class MethodNotAllowedRoute implements IRoute {
    public MethodNotAllowedRoute() {}

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setHeaders(ResponseCodes.codeString(405) + "\r\n");
        return response;
    }
}
