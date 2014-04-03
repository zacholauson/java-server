package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;
import main.Response.ResponseCodes;

public class FourOhFourRoute implements IRoute {
    public FourOhFourRoute() {}

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setHeaders(ResponseCodes.codeString(404) + "\r\n");
        response.setBody("<!DOCTYPE HTML><html><body><h1>Not Found</h1></body></html>");
        return response;
    }
}
