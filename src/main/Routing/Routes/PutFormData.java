package main.Routing.Routes;

import main.Response.IResponse;

public class PutFormData implements IRoute {
    public PutFormData() {}

    public IResponse buildResponse(IResponse response) {
        response.setHeaders("HTTP/1.1 200 OK\r\n\r\n");
        return response;
    }
}
