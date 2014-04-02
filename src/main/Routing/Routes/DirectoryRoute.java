package main.Routing.Routes;

import main.Response.IResponse;

public class DirectoryRoute implements IRoute {
    private String directoryPath;

    public DirectoryRoute(String _directoryPath) {
        directoryPath = _directoryPath;
    }

    public IResponse buildResponse(IResponse response) {
        response.setHeaders("HTTP/1.1 200 OK\r\n\r\n");
        return response;
    }
}
