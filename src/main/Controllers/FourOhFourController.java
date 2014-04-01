package main.Controllers;

import main.IController;
import main.IRequest;
import main.Response;

public class FourOhFourController implements IController {
    private IRequest request;

    public FourOhFourController() {}

    public IController sendRequest (IRequest _request) {
        request = _request;
        return this;
    }

    public Response getResponse() {
        Response response = new Response();

        switch(request.getMethod()) {
            case "GET":
                response = get(response);
                break;
            case "POST":
                response = post(response);
                break;
            default:
                response = defaultRoute(response);
                break;
        }

        return response;
    }

    private Response get(Response response) {
        response.setHeaders("HTTP/1.1 404 Not Found\r\n" +
                            "Content-Type: text/html\r\n\r\n");
        response.setBody("<!DOCTYPE html><html><body><h1>404 Not Found</h1></body></html>");
        return response;
    }

    private Response post(Response response) {
        response.setHeaders("HTTP/1.1 404 Not Found\r\n\r\n");
        response.setBody("Not Found");
        return response;
    }

    private Response defaultRoute(Response response) {
        response.setHeaders("HTTP/1.1 404 Not Found\r\n\r\n");
        response.setBody("Not Found");
        return response;
    }
}
