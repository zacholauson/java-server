package main.Routing.Routes;

import main.Response.IResponse;

public class PostFormData implements IRoute {
    public PostFormData() {}

    public IResponse buildResponse(IResponse response) {
        response.setHeaders("HTTP/1.1 200 OK\r\n\r\n");
        return response;
    }
}
