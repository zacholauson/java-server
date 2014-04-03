package main.Response;

//import java.io.File;

public interface IResponse {
    public void setHeaders(String headers);
    public void setBody(byte[] body);

    public String getHeaders();
    public byte[] getBody();
}
