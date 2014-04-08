package main.routing.routes;

import main.requests.IRequest;
import main.response.IResponse;

public class TextRoute implements IRoute {
    private String textBody;

    public TextRoute(String text) {
        textBody = text;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(200);
        response.setBody(("<!DOCTYPE HTML><html><body>" + textBody + "</body></html>").getBytes());
        return response;
    }
}
