package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileRoute implements IRoute {
    private String filePath;
    private String basePath;

    public FileRoute(String _basePath, String _filePath) {
        filePath = _filePath;
        basePath = _basePath;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        if (request.getRange().isEmpty()) {
            response = getNormalResponse(response);
        } else {
            response = getPartialResponse(response, request);
        }
        return response;
    }

    private IResponse getPartialResponse(IResponse response, IRequest request) {
        int begin = request.getRange().get("Begin");
        int end   = request.getRange().get("End");
        response.setStatus(206);
        response.setBody(Arrays.copyOfRange(fileToByteArray(), begin, end));
        return response;
    }

    private IResponse getNormalResponse(IResponse response) {
        response.setStatus(200);
        response.setBody(fileToByteArray());
        return response;
    }

    private byte[] fileToByteArray() {
        Path path = Paths.get(new File(basePath + filePath).getAbsolutePath());

        byte[] bodyBytes = null;
        try {
            bodyBytes = Files.readAllBytes(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bodyBytes;
    }
}
