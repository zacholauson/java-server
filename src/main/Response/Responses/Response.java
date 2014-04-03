package main.Response.Responses;

import main.Response.IResponse;

import java.io.File;

public class Response implements IResponse {
    private String headers = null;
    private File file      = null;
    private byte[] body    = null;

    public Response() {}

    public void setHeaders(String headers) { this.headers = headers; }
    public void setFile(File file) { this.file = file; }
    public void setBody(byte[] body) { this.body = body; }

    public String getHeaders() { return this.headers; }
    public File   getFile() { return this.file; }
    public byte[] getBody() { return this.body; }
}