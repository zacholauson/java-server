package main.Controllers;

import main.IController;
import main.IRequest;
import main.Response;

public class SleepController implements IController {
    public IRequest request;

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
            default:
                response = defaultRoute(response);
                break;
        }

        return response;
    }

    private Response get(Response response) {
        sleep(1000);
        response.setHeaders("HTTP/1.1 200 OK\r\n\r\n");
        response.setBody("<!DOCTYPE html><html><body><h1>Just Slept</h1></body></html>");
        return response;
    }

    private Response defaultRoute(Response response) {
        sleep(1000);
        response.setHeaders("HTTP/1.1 200 OK\r\n\r\n");
        response.setBody("Just Slept");
        return response;
    }

    private void sleep(int nano) {
        try {
            Thread.sleep(nano);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
