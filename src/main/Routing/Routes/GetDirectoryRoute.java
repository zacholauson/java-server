package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;

import java.io.File;

public class GetDirectoryRoute implements IRoute {
    String directoryPath;
    String baseDirectory;

    public GetDirectoryRoute(String _baseDirectory, String _directoryPath) {
        baseDirectory = _baseDirectory;
        directoryPath = _directoryPath;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setHeaders("HTTP/1.1 200 OK\r\n\r\n");
        response.setBody(buildDirectory());
        return response;
    }

    private String buildDirectory() {
        StringBuilder body = new StringBuilder();
        File directory = new File(baseDirectory + directoryPath);
        File[] listOfFiles = directory.listFiles();

        for(File file : listOfFiles) {
            if (file.isFile() || file.isDirectory()) {
                String relativeFilePath = buildRelativePath(directory.toString(), file.toString());
                body.append("<html><body>");
                body.append(buildATag(relativeFilePath));
                body.append("<br>");
                body.append("</body></html>");
            }
        }

        return body.toString();
    }

    private String buildRelativePath(String directory, String file) {
        return file.substring(directory.length() + 1);
    }

    private String buildATag(String path) {
        return "<a href=" + path + ">" + path + "</a>";
    }
}
