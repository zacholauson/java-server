package main.Response;

import java.io.File;

public interface IResponse {
    public void setHeaders(String headers);
    public void setFile(File file);
    public void setBody(String body);

    public String getHeaders();
    public File   getFile();
    public String getBody();
}
