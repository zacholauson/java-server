package main;

import java.io.File;

public class Response {
    private String headers = null;
    private File file      = null;
    private String body    = null;

    public Response() {
    }

    public void setHeaders(String headers) { this.headers = headers; }
    public void setFile(File file) { this.file = file; }
    public void setBody(String body) { this.body = body; }

    public String getHeaders() { return this.headers; }
    public File   getFile() { return this.file; }
    public String getBody() { return this.body; }
}