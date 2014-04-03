package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;
import main.Response.ResponseCodes;

public class TextRoute implements IRoute {
    private String textBody;

    public TextRoute(String text) {
        textBody = text;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setHeaders(ResponseCodes.codeString(200) + "\r\n");
        response.setBody(("<!DOCTYPE HTML><html><body>" + textBody + "</body></html>").getBytes());
        return response;
    }
}
