package main.Controllers;

import main.IController;
import main.IRequest;
import main.Response;
import main.Server;

import java.io.File;

public class RootController implements IController {
    private IRequest request;

    public IController sendRequest(IRequest _request) {
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
                response = null;
                break;
        }

        return response;
    }

    private Response get(Response response) {

        String indexPage = new File(Server.getDirectory() + "/index.html").getAbsolutePath();

        if (new File(indexPage).exists()) {
            response.setHeaders("HTTP/1.1 200 OK");
            response.setFile(new File(indexPage));
        } else {
            response = null;
        }

        return response;
    }

    private Response post(Response response) {
        response.setHeaders("HTTP/1.1 200 OK");
        response.setBody(request.getBody());
        return response;
    }
}
