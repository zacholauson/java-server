package main;

import main.Controllers.FourOhFourController;

import java.util.HashMap;

public class RequestHandler implements ICallable {
    IRequest request;
    HashMap<String, IController> routesMap;

    public Response call(IRequest _request, HashMap<String, IController> _routesMap) {
        request = _request;
        routesMap = _routesMap;
        Response response;

        try {
            response = routesMap.get(request.getRoute()).sendRequest(request).getResponse();
            if (response == null) { throw new Exception(); }
        } catch (Exception e) {
            response = new FourOhFourController().sendRequest(request).getResponse();
        }

        return response;
    }
}
