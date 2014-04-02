package main.Routing.Routes;

import main.Response.IResponse;

public class FourOhFourRoute implements IRoute {
    public FourOhFourRoute() {}

    public IResponse buildResponse(IResponse response) {
        response.setHeaders("HTTP/1.1 404 Not Found\r\n\r\n");
        response.setBody("<!DOCTYPE HTML><html><body><h1>Not Found</h1></body></html>");
        return response;
    }
}
