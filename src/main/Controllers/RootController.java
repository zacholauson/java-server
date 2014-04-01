package main.Controllers;

import main.IController;
import main.IRequest;
import main.Response;
import main.Server;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            case "OPTIONS":
                response = options(response);
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
            response.setFile(new File(indexPage));
            response.setHeaders("HTTP/1.1 200 OK\r\n" +
                                "Content-Type: text/html\r\n\r\n");
        } else {
            response = null;
        }

        return response;
    }

    private Response post(Response response) {
        response.setHeaders("HTTP/1.1 200 OK\r\n\r\n");
        response.setBody(request.getBody());
        return response;
    }

    private Response options(Response response) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        response.setHeaders("HTTP/1.1 200 OK\r\n" +
                            "Date:" + dateFormat.format(date) + "\r\n" +
                            "Allow: GET, POST, OPTIONS\r\n" +
                            "Connection: Closed\r\n\r\n");
        return response;
    }
}
