package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;
import main.Response.ResponseCodes;

import java.io.File;

public class GetDirectoryRoute implements IRoute {
    String directoryPath;
    String baseDirectory;

    public GetDirectoryRoute(String _baseDirectory, String _directoryPath) {
        baseDirectory = _baseDirectory;
        directoryPath = _directoryPath;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setHeaders(ResponseCodes.codeString(200) + "\r\n");
        response.setBody(buildDirectoryMarkup());
        return response;
    }

    private byte[] buildDirectoryMarkup() {
        StringBuilder body = new StringBuilder();
        File directory = new File(baseDirectory + directoryPath);

        File[] listOfFiles = directory.listFiles();

        for(File file : listOfFiles) {
            if (file.isFile() || file.isDirectory()) {
                String relativeFilePath = buildRelativePath(directory.toString(), file.toString());
                body.append("<html><body>");
                body.append(buildATag(relativeFilePath));
                body.append("</body></html>");
            }
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
