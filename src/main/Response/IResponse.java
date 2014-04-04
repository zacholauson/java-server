package main.Response;

//import java.io.File;

public interface IResponse {
    public void setBody(byte[] body);
    public void setStatus(int statusCode);
    public void addHeader(String key, String value);

    public String getHeaders();
    public byte[] getBody();
}
