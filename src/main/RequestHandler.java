package main;

import main.Controllers.*;

import java.util.HashMap;

public class RequestHandler implements ICallable {
    private HashMap<String, RootController> routesMap =  new HashMap<String, RootController>();
    private IRequest request;

    public Response call(IRequest _request) {
        this.request = _request;
        this.routesMap = buildRoutes();
        try {
            return routesMap.get(request.getRoute()).sendRequest(request).getResponse();
        } catch (Exception e) {
            return new FourOhFourController().sendRequest(request).getResponse();
        }
    }

    private HashMap<String, RootController> buildRoutes(){
        routesMap.put("/", new RootController());
        return routesMap;
    }
}
