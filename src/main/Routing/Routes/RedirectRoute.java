package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;

public class RedirectRoute implements IRoute {
    String redirectTo;

    public RedirectRoute(String _redirectTo) {
        redirectTo = _redirectTo;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(301);
        response.addHeader("Location", redirectTo);
        response.setBody("<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>".getBytes());
        return response;
    }
}
