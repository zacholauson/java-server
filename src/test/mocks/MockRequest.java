package test.mocks;

import main.requests.IRequest;

import java.util.HashMap;

public class MockRequest implements IRequest {
    private String route;
    private String method;

    public MockRequest(String method, String route){
        this.route = route;
        this.method = method;
    }

    public String getHeaderString()                   { return ""; }
    public HashMap<String, String> getHeaders()       { return new HashMap<>(); }
    public String getBody()                           { return ""; }
    public String getMethod()                         { return method; }
    public String getRoute()                          { return route; }
    public HashMap<String, String> getParams()        { return buildMockParams(); }
    public HashMap<String, Integer> getRange()        { return new HashMap<>(); }
    public HashMap<String, String> getAuthorization() { return buildMockAuth(); }

    private HashMap<String, String> buildMockParams() {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("Hello", "World");
        return paramsMap;
    }

    private HashMap<String, String> buildMockAuth() {
        HashMap<String, String> authMap = new HashMap<>();
        authMap.put("Basic", "YWRtaW46aHVudGVyMg==");
        return authMap;
    }
}
