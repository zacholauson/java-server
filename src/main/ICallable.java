package main;

import java.util.HashMap;

public interface ICallable {
    public Response call(IRequest request, HashMap<String, IController> routesMap);
}
