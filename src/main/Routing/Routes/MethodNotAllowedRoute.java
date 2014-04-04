package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;

public class MethodNotAllowedRoute implements IRoute {
    public MethodNotAllowedRoute() {}

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(405);
        return response;
    }
}
