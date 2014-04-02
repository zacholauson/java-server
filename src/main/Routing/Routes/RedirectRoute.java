package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;

public class RedirectRoute implements IRoute {
    String redirectTo;

    public RedirectRoute(String _redirectTo) {
        redirectTo = _redirectTo;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setHeaders("HTTP/1.1 301 Moved Permanently\r\n" +
                            "Location: " + redirectTo + "\r\n\r\n");
        response.setBody("<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>");
        return response;
    }
}
