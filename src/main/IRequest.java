package main;

import java.util.HashMap;

public interface IRequest {
    public String                  headerString();
    public HashMap<String, String> headers();
    public String                  body();
    public String                  method();
    public String                  route();
}
