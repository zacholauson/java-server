package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;

public class MethodNotAllowedRoute implements IRoute {
    public MethodNotAllowedRoute() {}

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setHeaders("HTTP/1.1 405 Method Not Allowed\r\n\r\n");
        return response;
    }
}
