package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;

public class PostFormDataRoute implements IRoute {
    public PostFormDataRoute() {}

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setHeaders("HTTP/1.1 200 OK\r\n\r\n");
        return response;
    }
}
