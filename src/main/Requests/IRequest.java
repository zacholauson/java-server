package main.Requests;

import java.util.HashMap;

public interface IRequest {
    public String                  getHeaderString();
    public HashMap<String, String> getHeaders();
    public String                  getBody();
    public String                  getMethod();
    public String                  getRoute();
    public HashMap<String, String> getParams();
}
