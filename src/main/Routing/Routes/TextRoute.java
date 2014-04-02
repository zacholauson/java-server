package main.Routing.Routes;

import main.Response.IResponse;

public class TextRoute implements IRoute {
    private String textBody;

    public TextRoute(String text) {
        textBody = text;
    }

    public IResponse buildResponse(IResponse response) {
        response.setHeaders("HTTP/1.1 200 OK\r\n\r\n");
        response.setBody("<!DOCTYPE HTML><html><body>" + textBody + "</body></html>");
        return response;
    }
}
