package main.Controllers;

import main.IController;
import main.IRequest;
import main.Response;

public class FourOhFourController implements IController {
    private IRequest request;
    private Response response = new Response();

    public FourOhFourController() {}

    public IController sendRequest (IRequest _request) {
        this.request = _request;
        System.out.println(request.getHeaderString());

        this.response.setHeaders("HTTP/1.1 404 Not Found");
        this.response.setBody("<html><body><h1>404 Not Found</h1></body></html>");
        return this;
    }

    public Response getResponse() {
        return response;
    }
}