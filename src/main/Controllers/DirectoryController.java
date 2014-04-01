package main.Controllers;

import main.IController;
import main.IRequest;
import main.Response;

public class DirectoryController implements IController{
    private IRequest request;
    private String   body;

    public DirectoryController(String _body) {
        body = _body;
    }

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
            default:
                response = null;
                break;
        }

        return response;
    }

    private Response get(Response response) {
        response.setHeaders("HTTP/1.1 200 OK\r\n\r\n");
        response.setBody(body);
        return response;
    }
}
