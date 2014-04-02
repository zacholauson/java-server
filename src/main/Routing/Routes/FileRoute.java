package main.Routing.Routes;

import main.Response.IResponse;
import main.Server;

import java.io.File;

public class FileRoute implements IRoute {
    private String   filePath;

    public FileRoute(String _filePath) {
        filePath = _filePath;
    }

    public IResponse buildResponse(IResponse response) {
        response.setHeaders("HTTP/1.1 200 OK\r\n\r\n");
        response.setFile(new File(Server.getDirectory() + filePath).getAbsoluteFile());
        return response;
    }
}
