package main.routing.routes;

import main.requests.IRequest;
import main.response.IResponse;

public class MethodNotAllowedRoute implements IRoute {
    public MethodNotAllowedRoute() {}

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(405);
        return response;
    }
}
