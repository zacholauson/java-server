package main.routing.routes;

import main.requests.IRequest;
import main.response.IResponse;

public class OptionsRoute implements IRoute {
    String options;

    public OptionsRoute(String options) {
        this.options = options;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(200);
        response.addHeader("Allow", options);
        return response;
    }
}
