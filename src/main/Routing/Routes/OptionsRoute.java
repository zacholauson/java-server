package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;

public class OptionsRoute implements IRoute {
    String options;

    public OptionsRoute(String _options) {
        options = _options;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(200);
        response.addHeader("Allow", options);
        return response;
    }
}
