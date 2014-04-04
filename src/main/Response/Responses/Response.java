package main.Response.Responses;

import main.Response.IResponse;
import main.Response.ResponseCodes;

public class Response implements IResponse {
    private static final String NEWLINE = "\r\n";

    private byte[] body;
    private String statusLine;
    private StringBuilder headerBuilder = new StringBuilder();

    public Response() {}

    public void setStatus(int statusCode) {
        statusLine = ResponseCodes.codeString(statusCode) + NEWLINE;
    }

    public void addHeader(String key, String value) {
        String headerLine = key + ": " + value + NEWLINE;
        headerBuilder.append(headerLine);
    }

    public String getHeaders() {
        return statusLine + headerBuilder.append(NEWLINE).toString();
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public byte[] getBody() {
        return this.body;
    }
}