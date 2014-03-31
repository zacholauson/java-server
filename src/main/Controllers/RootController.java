package main.Controllers;

import main.IController;
import main.IRequest;
import main.Response;

public class RootController implements IController {
    private IRequest request;

    public RootController() {}

    public IController sendRequest(IRequest _request) {
        request = _request;
        return this;
    }

    public Response getResponse() {
        Response response = new Response();
        response.setHeaders("HTTP/1.1 200 OK");
        response.setBody("<html><body><h1>Hello World</h1></body></html>");
        return response;
    }
}
