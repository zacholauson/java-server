package main.requests;

import java.util.HashMap;

public interface IRequest {
    public String                   getHeaderString();
    public HashMap<String, String>  getHeaders();
    public String                   getBody();
    public String                   getMethod();
    public String                   getRoute();
    public HashMap<String, String>  getParams();
    public HashMap<String, Integer> getRange();
    public HashMap<String, String>  getAuthorization();
}
