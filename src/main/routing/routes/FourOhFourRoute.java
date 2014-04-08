package main.routing.routes;

import main.requests.IRequest;
import main.response.IResponse;

public class FourOhFourRoute implements IRoute {
    public FourOhFourRoute() {}

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(404);
        response.setBody("<!DOCTYPE HTML><html><body><h1>Not Found</h1></body></html>".getBytes());
        return response;
    }
}
