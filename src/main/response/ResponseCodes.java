package main.response;

import java.util.HashMap;

public class ResponseCodes {
    private static HashMap<Integer, String> codeMap = buildCodes();

    public static String codeString(int statusCode) {
        return "HTTP/1.1 " + codeMap.get(statusCode);
    }

    private static HashMap<Integer, String> buildCodes() {
        HashMap<Integer, String> codes = new HashMap<>();
        codes.put(200, "200 OK");
        codes.put(201, "201 Created");
        codes.put(204, "204 No Content");
        codes.put(206, "206 Partial Content");
        codes.put(301, "301 Moved Permanently");
        codes.put(401, "401 Unauthorized");
        codes.put(403, "403 Forbidden");
        codes.put(404, "404 Not Found");
        codes.put(405, "405 Method Not Allowed");
        return codes;
    }
}