package main.Routing.Routes;

import main.Response.IResponse;

public class OptionsRoute implements IRoute {
    String options;

    public OptionsRoute(String _options) {
        options = _options;
    }

    public IResponse buildResponse(IResponse response) {
        response.setHeaders("HTTP/1.1 200 OK\r\nAllow: " + options + "\r\n\r\n");
        return response;
    }
}
