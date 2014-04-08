package main.routing.routes;

import main.requests.IRequest;
import main.response.IResponse;

import java.io.File;

public class GetDirectoryRoute implements IRoute {
    String directoryPath;
    String baseDirectory;

    public GetDirectoryRoute(String _baseDirectory, String _directoryPath) {
        baseDirectory = _baseDirectory;
        directoryPath = _directoryPath;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(200);
        response.setBody(buildDirectoryMarkup());
        return response;
    }

    private byte[] buildDirectoryMarkup() {
        StringBuilder body = new StringBuilder();
        File directory = new File(baseDirectory + directoryPath);
        File[] listOfFiles = directory.listFiles();
        if (listOfFiles != null) {
            body.append("<!DOCTYPE HTML><html><body>");

            for(File file : listOfFiles) {
                if (file.isFile() || file.isDirectory()) {
                    String relativeFilePath = buildRelativePath(directory.toString(), file.toString());
                    body.append(buildATag(relativeFilePath));
                }
            }

            body.append("</body></html>");
        }
        return body.toString().getBytes();
    }

    private String buildRelativePath(String directory, String file) {
        return file.substring(directory.length() + 1);
    }

    private String buildATag(String path) {
        return "<a href=" + path + ">" + path + "</a><br>";
    }
}
