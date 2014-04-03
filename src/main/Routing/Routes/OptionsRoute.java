package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;
import main.Response.ResponseCodes;

public class OptionsRoute implements IRoute {
    String options;

    public OptionsRoute(String _options) {
        options = _options;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setHeaders(ResponseCodes.codeString(200) + "Allow: " + options + "\r\n\r\n" );
        return response;
    }
}
